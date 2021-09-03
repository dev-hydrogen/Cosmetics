package com.ilm9001.cosmetics.rarity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum Rarities {
    COMMON (new Rarity("COMMON", Component.text("\uF80A")
            .decoration(TextDecoration.ITALIC,false)
            .color(TextColor.color(255,255,255)), TextColor.color(0x9c9c9f))),
    UNCOMMON (new Rarity("UNCOMMON", Component.text("\uF80B")
            .decoration(TextDecoration.ITALIC,false)
            .color(TextColor.color(255,255,255)),TextColor.color(0x71cc1b))),
    RARE (new Rarity("RARE", Component.text("\uF80C")
            .decoration(TextDecoration.ITALIC,false)
            .color(TextColor.color(255,255,255)),TextColor.color(0x2bd0f2))),
    EPIC (new Rarity("EPIC", Component.text("\uF80D")
            .decoration(TextDecoration.ITALIC,false)
            .color(TextColor.color(255,255,255)),TextColor.color(0x6060FF))),
    LEGENDARY (new Rarity("LEGENDARY", Component.text("\uF80E")
            .decoration(TextDecoration.ITALIC,false)
            .color(TextColor.color(255,255,255)),TextColor.color(0xffb920)));
    
    private final Rarity rarity;
    
    Rarities(Rarity rarity) {
        this.rarity = rarity;
    }
    
    public Rarity getRarity() {
        return rarity;
    }
}
