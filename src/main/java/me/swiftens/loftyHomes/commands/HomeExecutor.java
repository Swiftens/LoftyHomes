package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import me.swiftens.loftyHomes.data.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeExecutor implements CommandExecutor {

    private final DataManager dataManager;

    public HomeExecutor(LoftyHomes loftyHomes) {
        this.dataManager = loftyHomes.getDataManager();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is for players only!");
            return true;
        }

        if (!player.hasPermission("loftyhomes.home")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }

        Location location = dataManager.retrieveHome(player.getUniqueId(), "home");
        if (location == null) {
            player.sendMessage(ChatColor.RED + "This home is in an invalid world!");
        } else {
            player.teleport(location);
        }


        return true;
    }
}
