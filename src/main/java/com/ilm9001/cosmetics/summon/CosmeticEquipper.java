package com.ilm9001.cosmetics.summon;

import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CosmeticEquipper {
   
   public CosmeticEquipper() {
   }
   
   /**
    * Equip a cosmetic on to the player and if an item is already present in that slot, switch them around
    *
    * @param player   Player to equip Cosmetic to
    * @param cosmetic Cosmetic to apply to Player
    * @param slot     EquipmentSlot of where the item is being equipped from
    */
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
   
   //very unsafe
   private void switchArmor(PlayerInventory inv, ItemStack armor, Cosmetic cosmetic, int slot, EquipmentSlot eslot) {
      ItemStack[] armorset = inv.getArmorContents();
      if(armor == null) {
         inv.setItem(eslot,new ItemStack(Material.AIR));
      } else {
         inv.setItem(eslot,armor);
      }
      armorset[slot] = cosmetic.getCosmeticItemStack();
      inv.setArmorContents(armorset);
   }
}
