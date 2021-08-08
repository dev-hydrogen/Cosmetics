package com.ilm9001.cosmetics;

import com.ilm9001.cosmetics.commands.GiveCosmetic;
import com.ilm9001.cosmetics.commands.GiveCosmeticTabComplete;
import com.ilm9001.cosmetics.listeners.ArmorSlotClickListener;
import com.ilm9001.cosmetics.listeners.RightClickEventListener;
import com.ilm9001.cosmetics.summon.CosmeticFactory;
import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Cosmetics extends JavaPlugin {
   private static Cosmetics instance;
   private static CosmeticFactory cosmeticFactory;
   private static List<Cosmetic> cosmeticList;
   
   @Override
   public void onEnable() {
      instance = this;
      
      this.saveDefaultConfig();
   
      if(cosmeticFactory == null) {
         cosmeticFactory = new CosmeticFactory();
         cosmeticList = cosmeticFactory.getCosmeticsFromConfig();
      }
      
      this.getCommand("givecosmetic").setExecutor(new GiveCosmetic());
      this.getCommand("givecosmetic").setTabCompleter(new GiveCosmeticTabComplete());
      getServer().getPluginManager().registerEvents(new RightClickEventListener(), this);
      getServer().getPluginManager().registerEvents(new ArmorSlotClickListener(), this);
   }
   
   @Override
   public void onDisable() {
   }
   
   public static JavaPlugin getInstance() {
      return instance;
   }
   public static CosmeticFactory getCosmeticFactory() { return cosmeticFactory; }
   
   /**
    * Get cached List of Cosmetic's. Use CosmeticFactory#getCosmeticsFromConfig() for a non-cached list!
    * @return List of cached (read from config at load) Cosmetic's
    */
   public static List<Cosmetic> getCachedCosmeticList() { return cosmeticList; }
}
