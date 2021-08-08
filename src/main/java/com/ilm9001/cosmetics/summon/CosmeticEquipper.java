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
    * @param slot     EquipmentSlot of where the item is being equipped from
    * @param item     Cosmetic ItemStack to apply to Player
    * */
   public void equipCosmetic(Player player, EquipmentSlot slot,ItemStack item) {
      PlayerInventory inv = player.getInventory();
      ItemStack[] armorSet = inv.getArmorContents();
      Cosmetic cosmetic = Cosmetic.getCosmeticFromItemStack(item);
      if(cosmetic != null) {
         switch (cosmetic.getType()) {
            case HAT:
               switchArmor(inv, armorSet[3], item, 3, slot);
               break;
            case CHESTPLATE:
               switchArmor(inv, armorSet[2], item, 2, slot);
               break;
            case LEGGINGS:
               switchArmor(inv, armorSet[1], item, 1, slot);
               break;
            case BOOTS:
               switchArmor(inv, armorSet[0], item, 0, slot);
               break;
            case INVENTORY_ITEM:
               break;
         }
      } else throw new NullPointerException();
   }
   
   //very unsafe probably
   private void switchArmor(PlayerInventory inv, ItemStack armor, ItemStack item, int slot, EquipmentSlot eslot) {
      ItemStack[] armorset = inv.getArmorContents();
      inv.setItem(eslot, armor); // if null then its null we don't care here
      armorset[slot] = item;
      inv.setArmorContents(armorset);
   }
}
