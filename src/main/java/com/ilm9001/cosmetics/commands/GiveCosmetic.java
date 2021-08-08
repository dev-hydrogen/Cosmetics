package com.ilm9001.cosmetics.commands;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Basically a testing command to give the specified Cosmetic to the player
 *
 */
public class GiveCosmetic implements CommandExecutor {
   
   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
      if(sender.hasPermission("cosmetics.givecosmetic") && (args.length == 1 || args.length == 2)) {
         Cosmetic cosmetic = Cosmetics.getCosmeticFactory().getCosmeticFromName(args[0]);
         ItemStack cosmeticItem;
         Player toPlayer;
   
         if (args.length == 2) {
            toPlayer = Bukkit.getPlayer(args[1]);
         } else if (sender instanceof Player) {
            toPlayer = (Player) sender;
         } else {
            sender.sendMessage(ChatColor.RED + "Invalid player! Are you trying to give the console a cosmetic?");
            return true;
         }
         
         if(toPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Invalid player! Are they online and is the name correct?");
            return true;
         }
         
         if(cosmetic == null) {
            sender.sendMessage(ChatColor.RED + "Invalid cosmetic name");
            return true;
         }
         
         cosmeticItem = cosmetic.getCosmeticItemStack();
         ((Player) sender).getInventory().addItem(cosmeticItem);
         sender.sendMessage(ChatColor.GREEN + "Gave " + ChatColor.AQUA + cosmetic.getCosmeticName() + " to " + toPlayer.getName());
         return true;
      }
      return false;
   }
}
