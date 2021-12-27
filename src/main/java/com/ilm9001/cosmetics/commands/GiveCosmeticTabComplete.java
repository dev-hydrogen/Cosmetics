package com.ilm9001.cosmetics.commands;

import com.ilm9001.cosmetics.Cosmetics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveCosmeticTabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(sender.hasPermission("cosmetics.givecosmetic") && args.length <= 1) {
            return Cosmetics.getCosmeticFactory().getInternalCosmeticNames();
        } else if (sender.hasPermission("cosmetics.givecosmetic") && args.length == 2) {
            List<String> plrnames = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach((Player plr) -> plrnames.add(plr.getName()));
            return plrnames;
        }
        return new ArrayList<>();
    }
}
