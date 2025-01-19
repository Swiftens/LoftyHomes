package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class DelHomeExecutor extends LoftyHomesCommandExecutor {

    public DelHomeExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.delhome", true);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        String homeName = "home";
        if (sender instanceof Player player) {
            if (dataManager.deleteHome(player.getUniqueId(), homeName)) {
                sendMessage(player, messages.getHomeDeleted(), homeName);
            } else {
                sendMessage(player, messages.getHomeDeleteUnsuccessful(), homeName);
            }
        }
    }
}
