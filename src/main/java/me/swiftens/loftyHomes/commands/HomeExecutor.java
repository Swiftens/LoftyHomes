package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class HomeExecutor extends LoftyHomesCommandExecutor {

    public HomeExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.home", true);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            String homeName = "home";
            Location location = dataManager.retrieveHome(player.getUniqueId(), homeName);
            if (location == null) {
                sendMessage(sender, messages.getHomeTeleportUnsuccessful(), homeName);
            } else {
                player.teleport(location);
                sendMessage(sender, messages.getHomeTeleport(), homeName);
            }
        }

    }
}
