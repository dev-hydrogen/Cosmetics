package com.ilm9001.cosmetics.util;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.rarity.Rarities;
import com.ilm9001.cosmetics.rarity.Rarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Main Cosmetic class.
 *
 */
public class Cosmetic {
   private final String internalCosmeticName;
   private final Component cosmeticName;
   private final Integer modelID;
   private final Material material;
   private final List<Component> lore;
   private final CosmeticType type;
   private final Rarity rarity;
   
   public Cosmetic(String internalCosmeticName, Component cosmeticName, Integer modelID, Material material, List<Component> lore, CosmeticType type, Rarity rarity) {
      this.internalCosmeticName = internalCosmeticName;
      this.cosmeticName = cosmeticName
              .decoration(TextDecoration.ITALIC,false)
              .color(rarity.getRarityColor());
      this.modelID = modelID;
      this.material = material;
      this.type = type;
      this.lore = lore;
      this.lore.add(Component.text(""));
      this.lore.add(rarity.getFormattedRarityName());
      this.rarity = rarity;
   }
   
   public @NotNull Integer getModelID() {
      return modelID;
   }
   public @NotNull Component getCosmeticName() {
      return cosmeticName;
   }
   public @NotNull Material getMaterial() {
      return material;
   }
   public @NotNull List<Component> getLore() {
      return lore;
   }
   public @NotNull CosmeticType getType() {
      return type;
   }
   public @NotNull String getInternalName() {
      return internalCosmeticName;
   }
   public @NotNull Rarity getRarity() { return rarity; }
   
   /**
    * Creates a ItemStack with the properties of the provided Cosmetic
    *
    * @return         ItemStack that was created
    */
   public @NotNull ItemStack getCosmeticItemStack() {
      ItemStack stack = new ItemStack(this.getMaterial());
      ItemMeta meta = stack.getItemMeta();
      NamespacedKey typekey = new NamespacedKey(Cosmetics.getInstance(),"cosmetic-type");
      NamespacedKey namekey = new NamespacedKey(Cosmetics.getInstance(),"cosmetic-name");
      NamespacedKey raritykey = new NamespacedKey(Cosmetics.getInstance(), "rarity");
      PersistentDataContainer metaContainer = meta.getPersistentDataContainer();
      
      meta.lore(this.getLore());
      meta.setCustomModelData(this.getModelID());
      meta.displayName(this.getCosmeticName());
      metaContainer.set(typekey,PersistentDataType.BYTE,this.getType().getID());
      metaContainer.set(namekey,PersistentDataType.STRING,this.getInternalName());
      metaContainer.set(raritykey,PersistentDataType.STRING,this.getRarity().getInternalRarityName());
      
      stack.setItemMeta(meta);
      
      return stack;
   }
   
   /**
    * Fetch a Cosmetic from a provided ItemStack
    *
    * @param itemStack    ItemStack to get Cosmetic from
    * @return             Cosmetic if ItemStack is a valid cosmetic, Null if ItemStack is not a valid cosmetic
    */
   public static @Nullable Cosmetic getCosmeticFromItemStack(@NotNull ItemStack itemStack) {
      ItemMeta meta = itemStack.getItemMeta();
      NamespacedKey typekey = new NamespacedKey(Cosmetics.getInstance(),"cosmetic-type");
      NamespacedKey namekey = new NamespacedKey(Cosmetics.getInstance(),"cosmetic-name");
      NamespacedKey raritykey = new NamespacedKey(Cosmetics.getInstance(), "rarity");
      PersistentDataContainer metaContainer = meta.getPersistentDataContainer();
      Optional<CosmeticType> type;
      String internalname;
      Rarity rarity;
      
      if(Util.isCosmetic(itemStack)) {
         type = CosmeticType.getFromID(metaContainer.get(typekey, PersistentDataType.BYTE));
         internalname = metaContainer.get(namekey,PersistentDataType.STRING);
         rarity = Rarities.valueOf(metaContainer.get(raritykey,PersistentDataType.STRING).toUpperCase()).getRarity();
      } else {
         return null;
      }
      if(itemStack.hasItemMeta() & meta.hasDisplayName() & meta.hasCustomModelData() & meta.hasLore() & type.isPresent()) {
         return new Cosmetic(internalname,meta.displayName(),meta.getCustomModelData(),itemStack.getType(),meta.lore(),type.get(),rarity);
      } else {
         return null;
      }
   }
}
