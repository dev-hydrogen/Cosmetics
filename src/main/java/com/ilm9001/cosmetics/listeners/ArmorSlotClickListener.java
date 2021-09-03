package com.ilm9001.cosmetics.listeners;

import com.ilm9001.cosmetics.summon.CosmeticEquipper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;


/**
 * Listens if player is trying to slot a cosmetic piece to their armor slot
 * Applies Cosmetic to the slot if of correct CosmeticType and if has Cosmetic-data
 */

public class ArmorSlotClickListener implements Listener {
   private final CosmeticEquipper equipper;
   
   public ArmorSlotClickListener() {
      equipper = new CosmeticEquipper();
   }
   
   @EventHandler
   public void onInventoryClick(InventoryClickEvent e) {
      Inventory inv = e.getClickedInventory();
      
      if(e.getSlotType() == InventoryType.SlotType.ARMOR && inv != null && inv.getType() == InventoryType.PLAYER) { //only apply to armor slots
         equipper.equipCosmeticFromSlotClick(e.getWhoClicked(), e.getClickedInventory(), e.getCurrentItem(), e.getCursor(), e.getSlot());
      }
      if(e.isShiftClick() && inv != null && inv.getType() == InventoryType.PLAYER && e.getInventory().getType() == InventoryType.CRAFTING) {
         if(e.getSlotType() != InventoryType.SlotType.ARMOR) {
            equipper.equipCosmeticFromShiftClick(e.getCurrentItem(), e.getClickedInventory(), e.getSlot());
         }
      }
   
      //https://hub.spigotmc.org/jira/browse/SPIGOT-6701
      //Inventory click event does not fire for non-armor pieces when in creative. Not possible to fix unless mojang fixes it.
   }
}
