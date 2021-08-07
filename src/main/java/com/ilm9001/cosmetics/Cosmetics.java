package com.ilm9001.cosmetics;

import com.ilm9001.cosmetics.commands.GiveCosmetic;
import com.ilm9001.cosmetics.listeners.RightClickEventListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class Cosmetics extends JavaPlugin {
   private static Cosmetics instance;
   
   public Cosmetics() {
   }
   
   @Override
   public void onEnable() {
      instance = this;
      this.getCommand("givecosmetic").setExecutor(new GiveCosmetic());
      getServer().getPluginManager().registerEvents(new RightClickEventListener(), this);
   }
   
   @Override
   public void onDisable() {
      // Plugin shutdown logic
   }
   
   public static JavaPlugin getInstance() {
      return instance;
   }
}
