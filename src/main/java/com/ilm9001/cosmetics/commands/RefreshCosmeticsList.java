package com.ilm9001.cosmetics.commands;

import com.ilm9001.cosmetics.util.Util;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RefreshCosmeticsList implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("cosmetics.refresh")) {
            sender.sendMessage(ChatColor.AQUA + "Refreshing cosmetics, this may take a while depending on the amount of cosmetics!");
            if(Util.isSpigot()) {
                sender.sendMessage(LegacyComponentSerializer.builder().build().serialize(Util.getUserFriendlyRefreshedCosmeticsList()));
            } else {
                sender.sendMessage(Util.getUserFriendlyRefreshedCosmeticsList());
            }
            return true;
        }
        return false;
    }
}
