package com.ilm9001.cosmetics.commands;

import com.ilm9001.cosmetics.Cosmetics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveCosmeticTabComplete implements TabCompleter {
   
   @Override
   public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
      if(sender.hasPermission("cosmetics.givecosmetic")) {
         return Cosmetics.getCosmeticFactory().getCosmeticNames();
      }
      return new ArrayList<>();
   }
}
