package com.ilm9001.cosmetics.listeners;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.summon.CosmeticEquipper;
import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

//when a player right clicks on a equippable cosmetic, equip it to their armor.

public class RightClickEventListener implements Listener {
   private CosmeticEquipper equipper;
   
   public RightClickEventListener() {
      equipper = new CosmeticEquipper();
   }
   
   @EventHandler
   public void onRightClick(PlayerInteractEvent e) {
      if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
         Player plr = e.getPlayer();
         ItemStack item = e.getItem();
         NamespacedKey key = new NamespacedKey(Cosmetics.getInstance(), "cosmetic-type");
         PersistentDataContainer metaContainer;
         
         if (item.hasItemMeta()) {
            metaContainer = item.getItemMeta().getPersistentDataContainer();
         } else return;
         
         if(metaContainer.get(key, PersistentDataType.BYTE) != null) { //verify that item has metadata indicating cosmetic-type
            equipper.equipCosmetic(plr,e.getHand(),item);
         }
      }
   }
}
