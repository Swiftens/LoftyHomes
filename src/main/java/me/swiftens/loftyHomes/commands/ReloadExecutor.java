package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import org.bukkit.command.CommandSender;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public class ReloadExecutor extends LoftyHomesCommandExecutor {
    public ReloadExecutor(LoftyHomes loftyHomes) {
        super(loftyHomes, "loftyhomes.reload", false);
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        loftyHomes.reloadConfigs();
        sendMessage(sender, messages.getCommandReloaded());
    }
}
