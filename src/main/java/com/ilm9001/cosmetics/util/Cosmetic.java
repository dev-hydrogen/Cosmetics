package com.ilm9001.cosmetics.util;

import com.ilm9001.cosmetics.Cosmetics;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//main cosmetic utility class
public class Cosmetic {
   public final String cosmeticName;
   public final Integer modelID;
   public final Material material;
   public final List<Component> lore;
   public final CosmeticType type;
   
   public Cosmetic(String cosmeticName, Integer modelID, Material material, List<String> lore, CosmeticType type) {
      this.cosmeticName = cosmeticName;
      this.modelID = modelID;
      this.material = material;
      this.type = type;
      this.lore = new ArrayList<>();
      for (String string : lore) {
         Component cmp = Component.text(string)
                 .decoration(TextDecoration.ITALIC,false)
                 .color(TextColor.color(255,255,255));
         this.lore.add(cmp);
      }
   }
   
   public Cosmetic(Component cosmeticName, Integer modelID, Material material, List<Component> lore, CosmeticType type) {
      this.cosmeticName = PlainTextComponentSerializer.plainText().serialize(cosmeticName);
      this.modelID = modelID;
      this.material = material;
      this.type = type;
      this.lore = lore;
   }
   public @NotNull Integer getModelID() {
      return modelID;
   }
   public @NotNull String getCosmeticName() {
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
   
   /**
    * Creates a ItemStack with the properties of the provided Cosmetic
    *
    * @return         ItemStack that was created
    */
   public @NotNull ItemStack getCosmeticItemStack() {
      ItemStack stack = new ItemStack(this.getMaterial());
      ItemMeta meta = stack.getItemMeta();
      Component displayNameComponent = Component.text(this.getCosmeticName())
              .decoration(TextDecoration.ITALIC,false)
              .decoration(TextDecoration.BOLD,true);
      NamespacedKey key = new NamespacedKey(Cosmetics.getInstance(),"cosmetic-type");
      PersistentDataContainer metaContainer = meta.getPersistentDataContainer();
      
      meta.lore(this.getLore());
      meta.setCustomModelData(this.getModelID());
      meta.displayName(displayNameComponent);
      metaContainer.set(key,PersistentDataType.BYTE,this.getType().getID());
      
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
      NamespacedKey key = new NamespacedKey(Cosmetics.getInstance(),"cosmetic-type");
      PersistentDataContainer metaContainer = meta.getPersistentDataContainer();
      Optional<CosmeticType> type;
      
      if(metaContainer.get(key,PersistentDataType.BYTE) != null) {
         type = CosmeticType.getFromID(metaContainer.get(key, PersistentDataType.BYTE));
      } else {
         return null;
      }
      if(itemStack.hasItemMeta() & meta.hasDisplayName() & meta.hasCustomModelData() & meta.hasLore() & type.isPresent()) {
         return new Cosmetic(meta.displayName(),meta.getCustomModelData(),itemStack.getType(),meta.lore(),type.get());
      } else {
         return null;
      }
   }
}
