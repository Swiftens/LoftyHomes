package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import me.swiftens.loftyHomes.data.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeExecutor implements CommandExecutor {

    private final DataManager dataManager;

    public SetHomeExecutor(LoftyHomes loftyHomes) {
        this.dataManager = loftyHomes.getDataManager();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is for players only!");
            return true;
        }

        if (!player.hasPermission("loftyhomes.sethome")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to run this command!");
            return true;
        }

        Location location = player.getLocation();
        if (dataManager.setHome(player.getUniqueId(), "home", location)) {
            player.sendMessage(ChatColor.YELLOW + "Home set!");
        } else {
            player.sendMessage(ChatColor.RED + "Could not save your home, please try again!");
        }

        return true;
    }
}
