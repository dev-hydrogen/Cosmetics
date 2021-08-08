package com.ilm9001.cosmetics.summon;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;
import com.ilm9001.cosmetics.util.CosmeticType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CosmeticFactory {
   private ArrayList<String> cosmeticNames;
   private Integer cosmeticCount;
   
   public CosmeticFactory() {
      cosmeticNames = new ArrayList<>();
      cosmeticCount = 0;
   }
   
   /**
    * Returns a non-cached List of Cosmetic's by reading the config.
    * Uses default Material.PAPER if none is specified. Returns empty ArrayList for lore if none is specified.
    *
    * @return List of non-cached Cosmetic's from config
    */
   public @NotNull List<Cosmetic> getCosmeticsFromConfig() {
      List<Cosmetic> cosmeticsList = new ArrayList<>();
      JavaPlugin plugin = Cosmetics.getInstance();
      FileConfiguration config = plugin.getConfig();
      
      for (String internalname : config.getConfigurationSection("Cosmetics").getKeys(false)) {
         cosmeticCount++;
         
         Map<String,Object> valuesmap = config.getConfigurationSection("Cosmetics."+internalname).getValues(false);
         
         String name = (String) valuesmap.get("name");
         Integer modelID = (Integer) valuesmap.get("modelID");
         Material material;
         List<String> lore;
         CosmeticType type = CosmeticType.valueOf((String)valuesmap.get("type"));
         cosmeticNames.add(internalname);
   
         if(valuesmap.get("material") != null) {
            material = Material.matchMaterial(valuesmap.get("material").toString());
         } else material = Material.PAPER;
         
         if (valuesmap.get("lore") instanceof List) {
            lore = (List<String>) valuesmap.get("lore");
         } else {
            lore = new ArrayList<>();
         }
         
         Cosmetic cosmetic = new Cosmetic(internalname,name,modelID,material,lore,type);
         cosmeticsList.add(cosmetic);
      }
      return cosmeticsList;
   }
   
   public Integer getCosmeticCount() {
      return cosmeticCount;
   }
   public List<String> getCosmeticNames() {
      return cosmeticNames;
   }
   
   /**
    * Get a Cosmetic from its InternalName
    *
    * @param name InternalName, always one "word"
    * @return     Cosmetic from InternalName, null if none found.
    */
   
   public @Nullable Cosmetic getCosmeticFromName(String name) {
      for (Cosmetic cosmetic : Cosmetics.getCachedCosmeticList()) {
         if(cosmetic.getInternalName().equals(name)) { //probably inefficient for large amounts of cosmetics?
            return cosmetic;
         }
      }
      return null;
   }
}
