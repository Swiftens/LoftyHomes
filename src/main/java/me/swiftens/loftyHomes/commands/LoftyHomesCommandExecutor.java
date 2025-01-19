package me.swiftens.loftyHomes.commands;

import me.swiftens.loftyHomes.LoftyHomes;
import me.swiftens.loftyHomes.configs.MessageConfiguration;
import me.swiftens.loftyHomes.data.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.swiftens.loftyHomes.utils.MessageUtils.sendMessage;

public abstract class LoftyHomesCommandExecutor implements CommandExecutor {

    protected final LoftyHomes loftyHomes;
    protected final DataManager dataManager;
    protected final MessageConfiguration messages;
    private final String permission;
    private final boolean playerOnly;

    public LoftyHomesCommandExecutor(LoftyHomes loftyHomes, String permission, boolean playerOnly) {
        this.loftyHomes = loftyHomes;
        this.dataManager = loftyHomes.getDataManager();
        this.messages = loftyHomes.getMessageConfiguration();
        this.permission = permission;
        this.playerOnly = playerOnly;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (playerOnly && !(commandSender instanceof Player)) {
            sendMessage(commandSender, messages.getCommandPlayerOnly());
            return true;
        }

        if (!commandSender.hasPermission(permission)) {
            sendMessage(commandSender, messages.getCommandNoPermissions());
            return true;
        }

        executeCommand(commandSender, strings);
        return true;
    }

    public abstract void executeCommand(CommandSender sender, String[] args);
}
