package com.ilm9001.cosmetics.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Cosmetic {
   public final String cosmeticName;
   public final Integer modelID;
   public final Material material;
   public final List<Component> componentList;
   public final CosmeticType type;
   
   public Cosmetic(String cosmeticName, Integer modelID, Material material, List<String> lore, CosmeticType type) {
      this.cosmeticName = cosmeticName;
      this.modelID = modelID;
      this.material = material;
      this.type = type;
      componentList = new ArrayList<>();
      for (String string : lore) {
         Component cmp = Component.text(string);
         componentList.add(cmp);
      }
   }
   public Integer getModelID() {
      return modelID;
   }
   public String getCosmeticName() {
      return cosmeticName;
   }
   public Material getMaterial() {
      return material;
   }
   public List<Component> getLore() {
      return componentList;
   }
   public CosmeticType getType() {
      return type;
   }
}
