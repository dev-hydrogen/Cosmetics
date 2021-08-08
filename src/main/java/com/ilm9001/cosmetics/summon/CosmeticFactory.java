package com.ilm9001.cosmetics.summon;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;
import com.ilm9001.cosmetics.util.CosmeticType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CosmeticFactory {
   private ArrayList<Cosmetic> cosmetics;
   private ArrayList<String> cosmeticNames;
   private Integer cosmeticCount;
   
   public CosmeticFactory() {
      cosmetics = new ArrayList<>();
      cosmeticNames = new ArrayList<>();
      cosmeticCount = 0;
   }
   
   public void setCosmetics() {
      JavaPlugin plugin = Cosmetics.getInstance();
      FileConfiguration config = plugin.getConfig();
      
      for (String name : config.getConfigurationSection("Cosmetics").getKeys(false)) {
         cosmeticCount++;
         cosmeticNames.add(name);
         
         Map<String,Object> valuesmap = config.getConfigurationSection("Cosmetics."+name).getValues(false);
         
         Integer modelID = (Integer) valuesmap.get("modelID");
         Material material = Material.matchMaterial(valuesmap.get("material").toString());
         List<String> lore;
         CosmeticType type = CosmeticType.valueOf((String)valuesmap.get("type"));
         
         if (valuesmap.get("lore") instanceof List) {
            lore = (List<String>) valuesmap.get("lore");
         } else {
            lore = new ArrayList<>();
         }
         
         Cosmetic cosmetic = new Cosmetic(name,modelID,material,lore,type);
         cosmetics.add(cosmetic);
      }
   }
   public Integer getCosmeticCount() {
      return cosmeticCount;
   }
   public List<Cosmetic> getCosmetics() {
      return cosmetics;
   }
   public List<String> getCosmeticNames() {
      return cosmeticNames;
   }
   public Cosmetic getCosmeticFromName(String name) {
      for (Cosmetic cosmetic : cosmetics) {
         if(cosmetic.getCosmeticName().equals(name)) {
            return cosmetic;
         }
      }
      return null;
   }
}
