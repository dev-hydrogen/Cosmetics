package com.ilm9001.cosmetics.commands;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RefreshCosmeticsList implements CommandExecutor {
   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
      if(sender.hasPermission("cosmetics.refresh")) {
         List<String> oldList;
         List<String> newList;
         List<String> differences;
         Component differencesNames;
         Component differencesMessage;
         Component refreshedMessage;
         
         sender.sendMessage(ChatColor.AQUA + "Refreshing cosmetics, this may take a while depending on the amount of cosmetics!");
   
         oldList = new ArrayList<>();
         newList = new ArrayList<>();
         Cosmetics.getCachedCosmeticList().forEach((Cosmetic csm) -> oldList.add(csm.getCosmeticName()));
         Cosmetics.getCosmeticFactory().getCosmeticsFromConfig().forEach((Cosmetic csm) -> newList.add(csm.getCosmeticName()));;
         
         if(oldList.toArray().length < newList.toArray().length) {
            differences = new ArrayList<>(newList);
            differences.removeAll(oldList);
            differencesMessage = Component.text("Found new cosmetics: ")
                    .color(TextColor.color(66,133,255));
         } else {
            differencesMessage = Component.text("");
            differences = new ArrayList<>();
         }
         
         differencesNames = Component.text("").color(TextColor.color(66,133,255));
         for (String dif : differences) {
            differencesNames = differencesNames.append(Component.text(dif+", "));
         }
         
         refreshedMessage = Component.text("Cosmetics Refreshed! ")
                 .color(TextColor.color(15,187,88));
         
         sender.sendMessage(refreshedMessage.append(differencesMessage).append(differencesNames));
         return true;
      }
      return false;
   }
}
