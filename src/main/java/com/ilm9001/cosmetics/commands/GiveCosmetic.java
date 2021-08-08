package com.ilm9001.cosmetics.commands;

import com.ilm9001.cosmetics.Cosmetics;
import com.ilm9001.cosmetics.util.Cosmetic;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

// initially a testing command to give a cosmetic to a player
public class GiveCosmetic implements CommandExecutor {
   
   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
      if(sender instanceof Player && sender.hasPermission("cosmetics.givecosmetic") && args.length == 1) {
         Cosmetic cosmetic = Cosmetics.getCosmeticFactory().getCosmeticFromName(args[0]);
         ItemStack cosmeticItem;
         
         if(cosmetic == null) {
            sender.sendMessage(ChatColor.RED + "Invalid cosmetic name");
            return true;
         }
         
         cosmeticItem = cosmetic.getCosmeticItemStack();
         ((Player) sender).getInventory().addItem(cosmeticItem);
         return true;
      }
      return false;
   }
}
