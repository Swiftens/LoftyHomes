package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class AdminDelHomeExecutor extends LoftyHomesCommandExecutor {

    public AdminDelHomeExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.others.delhome", false);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        String homeName = "home";

        // Deprecated method, it is best to figure out a way to get players by usernames eventually
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        if (!offlinePlayer.hasPlayedBefore()) {
            sendMessage(sender, messages.getCommandPlayerDoesNotExist());
            return;
        }

        String playerName = offlinePlayer.getName();
        if (dataManager.deleteHome(offlinePlayer.getUniqueId(), homeName)) {
            sendMessage(sender, messages.getHomeDeletedOthers(), homeName, playerName);

            if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null) {
                sendMessage(sender, messages.getHomeTeleportOthersNotifyPlayer(), homeName, playerName);
            }

        } else {
            sendMessage(sender, messages.getHomeDeleteOthersUnsuccessful(), homeName, playerName);
        }
    }
}
