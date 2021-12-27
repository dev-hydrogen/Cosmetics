package com.ilm9001.cosmetics.listeners;

import com.ilm9001.cosmetics.summon.CosmeticEquipper;

import com.ilm9001.cosmetics.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Listens to RightClick events and equips cosmetic if player is holding one.
 *
 */

public class RightClickEventListener implements Listener {
    private final CosmeticEquipper equipper;
    
    public RightClickEventListener() {
        equipper = new CosmeticEquipper();
    }
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if(Util.isCosmetic(e.getItem())) {
                equipper.equipCosmetic(e.getPlayer(),e.getItem(),e.getHand());
            }
        }
    }
}
