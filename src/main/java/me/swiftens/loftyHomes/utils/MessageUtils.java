package me.swiftens.loftyHomes.utils;


import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#(\\w{5}[a-fA-F0-9])");

    public static String translateHexCodes(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(builder, ChatColor.of("#" + matcher.group(1)).toString());
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(builder).toString());
    }

    @Setter
    private static String prefix = "";

    public static void sendMessage(CommandSender sender, String message, String homeName, String playerName) {
        if (message == null || message.isEmpty()) return;
        message = message.replace("{sender}", sender.getName());
        if (homeName != null) message = message.replace("{home}", homeName);
        if (playerName != null) message = message.replace("{player}", playerName);

        String formattedMessage = translateHexCodes(prefix + message).trim();

        sender.sendMessage(
                formattedMessage
        );
    }

    public static void sendMessage(CommandSender sender, String message, String homeName) {
        sendMessage(sender, message, homeName, null);
    }

    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, message, null, null);
    }


}
