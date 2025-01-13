package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import me.swiftens.loftyHomes.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminDelHomeExecutor implements CommandExecutor {

    private final DataManager dataManager;

    public AdminDelHomeExecutor(LoftyHomes loftyHomes) {
        this.dataManager = loftyHomes.getDataManager();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is for players only!");
            return true;
        }

        if (!player.hasPermission("loftyhomes.others.delhome")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }

        // Deprecated method, it is best to figure out a way to get players by usernames eventually
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
        if (!offlinePlayer.hasPlayedBefore()) {
            player.sendMessage(ChatColor.RED + "This player does not exist!");
            return true;
        }

        if (dataManager.deleteHome(offlinePlayer.getUniqueId(), "home")) {
            player.sendMessage(ChatColor.YELLOW + "Home deleted for " + offlinePlayer.getName());

            if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null) {
                offlinePlayer.getPlayer().sendMessage(ChatColor.YELLOW + "Your home has been deleted by " + player.getName() + "!");
            }

        } else {
            player.sendMessage(ChatColor.RED + "Could not delete player's home, please try again!");
        }


        return true;
    }
}
