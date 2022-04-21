package com.ilm9001.cosmetics.rarity;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rarities {
    private static final List<Rarity> rarities = new ArrayList<>();

    public static void setRaritiesFromFile() {
        Cosmetics.refreshFiles();
        FileConfiguration config = Cosmetics.getRarities();

        for (String internalname : config.getConfigurationSection("Rarities").getKeys(false)) {
            Component displayName;
            TextColor color;
            Map<String,Object> valuesmap = config.getConfigurationSection("Rarities."+internalname).getValues(false);;
            List<Number> colorList;
            if(valuesmap.get("color") instanceof List && valuesmap.get("color") != null) {
                colorList = (List<Number>) valuesmap.get("color");;
            } else throw new IllegalArgumentException("Color list is not a list, or is null");
            color = TextColor.color(colorList.get(0).intValue(), colorList.get(1).intValue(), colorList.get(2).intValue());
            displayName = Component.text((String) valuesmap.get("display"))
                    .decoration(TextDecoration.ITALIC,false)
                    .color(((String) valuesmap.get("display")).length() > 1 ? color : TextColor.color(255,255,255));

            Rarity rarity = new Rarity(internalname, displayName, color);
            rarities.add(rarity);
        }
    }
    
    public static List<Rarity> getRarities() {
        return rarities;
    }
}
