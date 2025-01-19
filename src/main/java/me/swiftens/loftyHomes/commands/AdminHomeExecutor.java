package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class AdminHomeExecutor extends LoftyHomesCommandExecutor {

    public AdminHomeExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.others.home", true);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        // Deprecated method, it is best to figure out a way to get players by usernames eventually
        String homeName = "home";

        if (sender instanceof Player player) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            if (!offlinePlayer.hasPlayedBefore()) {
                sendMessage(player, messages.getCommandPlayerDoesNotExist());
                return;
            }

            String playerName = offlinePlayer.getName();
            Location location = dataManager.retrieveHome(offlinePlayer.getUniqueId(), homeName);
            if (location == null) {
                sendMessage(sender, messages.getHomeTeleportOthersUnsuccessful(), homeName, playerName);
            } else {
                player.teleport(location);
                sendMessage(sender, messages.getHomeTeleportOthers(), homeName, playerName);

                if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null) {
                    sendMessage(offlinePlayer.getPlayer(), messages.getHomeTeleportOthersNotifyPlayer(), homeName, playerName);
                }
            }
        }
    }
}
