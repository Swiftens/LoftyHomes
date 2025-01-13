package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import me.swiftens.loftyHomes.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminHomeExecutor implements CommandExecutor {

    private final DataManager dataManager;

    public AdminHomeExecutor(LoftyHomes loftyHomes) {
        this.dataManager = loftyHomes.getDataManager();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is for players only!");
            return true;
        }

        if (!player.hasPermission("loftyhomes.others.home")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }

        // Deprecated method, it is best to figure out a way to get players by usernames eventually
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
        if (!offlinePlayer.hasPlayedBefore()) {
            player.sendMessage(ChatColor.RED + "This player does not exist!");
            return true;
        }

        Location location = dataManager.retrieveHome(offlinePlayer.getUniqueId(), "home");
        if (location == null) {
            player.sendMessage(ChatColor.RED + "Invalid home! Either it doesn't exist or it is in an invalid location.");
        } else {
            player.teleport(location);
        }

        return true;
    }
}
