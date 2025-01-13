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

import java.util.UUID;

public class AdminSetHomeExecutor implements CommandExecutor {

    private final DataManager dataManager;

    public AdminSetHomeExecutor(LoftyHomes loftyHomes) {
        this.dataManager = loftyHomes.getDataManager();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is for players only!");
            return true;
        }

        if (!player.hasPermission("loftyhomes.others.sethome")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }

        // Deprecated method, it is best to figure out a way to get players by usernames eventually
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(strings[0]);
        if (!offlinePlayer.hasPlayedBefore()) {
            player.sendMessage(ChatColor.RED + "This player does not exist!");
            return true;
        }

        UUID playerId = offlinePlayer.getUniqueId();
        Location location = player.getLocation();
        if (dataManager.setHome(playerId, "home", location)) {
            player.sendMessage(ChatColor.YELLOW + "Home set for " + offlinePlayer.getName());

            if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null) {
                offlinePlayer.getPlayer().sendMessage(ChatColor.YELLOW + "Your home has been set by " + player.getName() + "!");
            }

        } else {
            player.sendMessage(ChatColor.RED + "Could not save the player's home, please try again!");
        }

        return false;
    }
}
