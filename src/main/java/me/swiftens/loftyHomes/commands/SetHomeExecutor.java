package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class SetHomeExecutor extends LoftyHomesCommandExecutor {

    public SetHomeExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.sethome", true);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            String homeName = "home";
            Location location = player.getLocation();
            if (dataManager.setHome(player.getUniqueId(), homeName, location)) {
                sendMessage(player, messages.getHomeSet(), homeName);
            } else {
                sendMessage(player, messages.getHomeSetUnsuccessful(), homeName);
            }
        }
    }
}
