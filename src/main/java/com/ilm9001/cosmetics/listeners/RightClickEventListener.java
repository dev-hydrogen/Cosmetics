package com.ilm9001.cosmetics.listeners;

import com.ilm9001.cosmetics.summoner.CosmeticEquipper;
import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickEventListener implements Listener {
   CosmeticEquipper equipper;
   
   public RightClickEventListener() {
      equipper = new CosmeticEquipper();
   }
   
   @EventHandler
   public void onRightClick(PlayerInteractEvent e) {
      Player plr = e.getPlayer();
      ItemStack item = e.getItem();
      if(e.hasItem() & !e.isBlockInHand() & item != null) {
         Cosmetic cosmetic = Cosmetic.getCosmeticFromItemStack(item);
         if(cosmetic != null) {
            equipper.equipCosmetic(plr, cosmetic, e.getHand());
         }
      }
   }
}
