package com.ilm9001.cosmetics;

import com.ilm9001.cosmetics.commands.GiveCosmetic;
import com.ilm9001.cosmetics.commands.GiveCosmeticTabComplete;
import com.ilm9001.cosmetics.listeners.ArmorSlotClickListener;
import com.ilm9001.cosmetics.listeners.RightClickEventListener;
import com.ilm9001.cosmetics.summon.CosmeticFactory;
import org.bukkit.plugin.java.JavaPlugin;

public class Cosmetics extends JavaPlugin {
   private static Cosmetics instance;
   private static CosmeticFactory cosmeticFactory;
   
   @Override
   public void onEnable() {
      instance = this;
      
      this.saveDefaultConfig();
   
      if(cosmeticFactory == null) {
         cosmeticFactory = new CosmeticFactory();
         cosmeticFactory.setCosmetics();
      }
      
      this.getCommand("givecosmetic").setExecutor(new GiveCosmetic());
      this.getCommand("givecosmetic").setTabCompleter(new GiveCosmeticTabComplete());
      getServer().getPluginManager().registerEvents(new RightClickEventListener(), this);
      getServer().getPluginManager().registerEvents(new ArmorSlotClickListener(), this);
   }
   
   @Override
   public void onDisable() {
      // Plugin shutdown logic
   }
   
   public static JavaPlugin getInstance() {
      return instance;
   }
   public static CosmeticFactory getCosmeticFactory() { return cosmeticFactory; }
}
