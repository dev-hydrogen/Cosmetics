package com.ilm9001.cosmetics.summon;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;

import com.ilm9001.cosmetics.util.CosmeticType;
import com.ilm9001.cosmetics.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class CosmeticEquipper {
   
   public CosmeticEquipper() {
   }
   
   /**
    * Equip a cosmetic on to the player and if an item isn't already present in that slot.
    *
    * @param player   Player to equip Cosmetic to
    * @param item     Cosmetic ItemStack to apply to Player
    * */
   public void equipCosmetic(Player player, ItemStack item, EquipmentSlot slot) {
      if(Util.isCosmetic(item)) {
         Cosmetic cosmetic = Cosmetic.getCosmeticFromItemStack(item);
         CosmeticType cosmeticType = cosmetic.getType();
         PlayerInventory inv = player.getInventory();
         
         if (inv.getItem(36 + cosmeticType.getID()) == null) {
            inv.setItem(36 + cosmeticType.getID(), item);
            inv.setItem(slot,null);
         }
         //Could *re-*make something that switches two cosmetics, but thats not vanilla behaviour ;)
      }
   }
   
   /**
    * Equip a cosmetic from slot click event, if an item is already present, switch items on cursor and slot
    * ONLY CALL WHEN PLAYER'S INVENTORY IS OPEN!
    *
    * @param player  HumanEntity who clicked
    * @param inv     Inventory that was clicked
    * @param inSlot    ItemStack already present in slot
    * @param cursor  ItemStack present on player cursor
    * @param slotInt Int of the slot that was clicked
    */
   
   public void equipCosmeticFromSlotClick(HumanEntity player, Inventory inv, ItemStack inSlot, ItemStack cursor, int slotInt) {
      if(Util.isCosmetic(cursor)) {
         Cosmetic cosmetic = Cosmetic.getCosmeticFromItemStack(cursor);
         CosmeticType cosmeticType = cosmetic.getType();
   
         if (slotInt == 36 + cosmeticType.getID()) {
            /*verify that the cosmetic-type matches armor slot & slot type*/
            if (inSlot == null || inSlot.equals(new ItemStack(Material.AIR))) {
               player.setItemOnCursor(null);
               Bukkit.getScheduler().runTask(Cosmetics.getInstance(), () -> inv.setItem(slotInt, cursor));
            } else { // switcher
               Bukkit.getScheduler().runTask(Cosmetics.getInstance(), () -> inv.setItem(slotInt, cursor));
               Bukkit.getScheduler().runTaskLater(Cosmetics.getInstance(), () -> player.setItemOnCursor(inSlot), 1);
            }
         }
      }
   }
   
   /**
    * Called when player shift-clicks a Cosmetic into its slot
    *
    * @param shiftClickedItem Item that was shift-clicked
    * @param inv              Inventory where Item was shift-clicked in
    * @param slot             Slot where shiftClickedItem was shift-clicked from
    */
   
   public void equipCosmeticFromShiftClick(ItemStack shiftClickedItem, Inventory inv, int slot) {
      if(Util.isCosmetic(shiftClickedItem)) {
         CosmeticType cosmeticType = Cosmetic.getCosmeticFromItemStack(shiftClickedItem).getType();
   
         ItemStack itemInSlot = inv.getItem(36 + cosmeticType.getID());
         int slotInt = 36 + cosmeticType.getID();
   
         if (itemInSlot == null || itemInSlot.equals(new ItemStack(Material.AIR))) {
            inv.setItem(slotInt, shiftClickedItem);
            inv.setItem(slot, null);
         }
      }
   }
}
