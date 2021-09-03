package com.ilm9001.cosmetics.rarity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class Rarity {
    
    private final String internalRarityName;
    private final Component formattedRarityName;
    private final TextColor rarityColor;
    
    /**
     * Rarity constructor
     *
     * @param internalRarityName  Used internally, must be one-word string
     * @param formattedRarityName Formatted name of Rarity to be used in description and name, custom font.
     * @param rarityColor         Color of rarity, used by item name.
     */
    public Rarity(String internalRarityName, Component formattedRarityName, TextColor rarityColor) {
        this.internalRarityName = internalRarityName;
        this.formattedRarityName = formattedRarityName;
        this.rarityColor = rarityColor;
    }
    
    public String getInternalRarityName() {
        return internalRarityName;
    }
    public Component getFormattedRarityName() {
        return formattedRarityName;
    }
    public TextColor getRarityColor() {
        return rarityColor;
    }
}
