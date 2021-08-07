package com.ilm9001.cosmetics.summoner;

import com.ilm9001.cosmetics.util.Cosmetic;
import com.ilm9001.cosmetics.util.CosmeticType;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CosmeticFactory {
   
   public CosmeticFactory() {
   }
   
   public ItemStack createCosmetic(Cosmetic cosmetic) {
      ItemStack stack = new ItemStack(cosmetic.getMaterial());
      ItemMeta meta = stack.getItemMeta();
      
      meta.lore(cosmetic.getLore());
      meta.setCustomModelData(cosmetic.getModelID());
      meta.displayName();
      
      stack.setItemMeta(meta);
      
      return stack;
   }
}
