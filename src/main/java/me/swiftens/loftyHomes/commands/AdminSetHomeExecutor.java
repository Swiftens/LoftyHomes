package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class AdminSetHomeExecutor extends LoftyHomesCommandExecutor {

    public AdminSetHomeExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.others.sethome", true);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            String homeName = "home";
            // Deprecated method, it is best to figure out a way to get players by usernames eventually
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            if (!offlinePlayer.hasPlayedBefore()) {
                sendMessage(sender, messages.getCommandPlayerDoesNotExist());
                return;
            }

            UUID playerId = offlinePlayer.getUniqueId();
            String playerName = offlinePlayer.getName();
            Location location = player.getLocation();
            if (dataManager.setHome(playerId, homeName, location)) {
                sendMessage(player, messages.getHomeSetOthers(), homeName, playerName);

                if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null) {
                    sendMessage(offlinePlayer.getPlayer(), messages.getHomeSetOthersNotifyPlayer(), homeName, playerName);
                }

            } else {
                sendMessage(sender, messages.getHomeSetOthersUnsuccessful(), homeName, playerName);
            }
        }
    }
}
