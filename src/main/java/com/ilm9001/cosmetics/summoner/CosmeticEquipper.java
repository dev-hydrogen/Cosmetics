package com.ilm9001.cosmetics.summoner;

import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CosmeticEquipper {
   
   public CosmeticEquipper() {
   }
   
   public void equipCosmetic(Player player, Cosmetic cosmetic, EquipmentSlot slot) {
      PlayerInventory inv = player.getInventory();
      ItemStack[] armorSet = inv.getArmorContents();
      switch(cosmetic.getType()) {
         case HAT:
            switchArmor(inv,armorSet[3],cosmetic,3,slot);
            break;
         case CHESTPLATE:
            switchArmor(inv,armorSet[2],cosmetic,2,slot);
            break;
         case LEGGINGS:
            switchArmor(inv,armorSet[1],cosmetic,1,slot);
            break;
         case BOOTS:
            switchArmor(inv,armorSet[0],cosmetic,0,slot);
            break;
         case INVENTORY_ITEM:
            break;
      }
   }
   
   private void switchArmor(PlayerInventory inv, ItemStack armor, Cosmetic cosmetic, int slot, EquipmentSlot eslot) {
      if(armor == null) {
         setArmor(inv,cosmetic.createCosmeticItemStack(cosmetic),slot);
      } else {
         inv.setItem(eslot,armor);
         setArmor(inv,cosmetic.createCosmeticItemStack(cosmetic),slot);
      }
   }
   private void setArmor(PlayerInventory inv, ItemStack itemStack, int slot) {
      ItemStack[] armorset = inv.getArmorContents();
      armorset[slot] = itemStack;
      inv.setArmorContents(armorset);
   }
}
