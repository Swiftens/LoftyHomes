package me.swiftens.loftyHomes.configs;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;

@Getter
@Configuration
public class MessageConfiguration {
    @Comment("Set a message to empty to disable that particular message")
    private String prefix = "&8[&eLoftyHomes&8] ";

    @Comment({"", "Some placeholders are allowed in specific chats",
            "{sender} - Returns the sender's name",
            "{home} - Returns the name of the home",
    })
    private String homeSet = "&eHome: &a\"{home}\" &eset!";
    private String homeSetUnsuccessful = "&cCould not set home &a\"{home}\"&c, please try again!";
    private String homeTeleport = "";
    private String homeTeleportUnsuccessful = "&cInvalid home! Either it doesn't exist or is in an invalid location.";
    private String homeDeleted = "&eHome: &a\"{home}\" &edeleted!";
    private String homeDeleteUnsuccessful = "&cCould not delete home &a\"{home}\"&c, please try again!";

    @Comment({"", "{player} - Returns the name of the player set in the command"})
    private String homeSetOthers = "&eHome: &a\"{home}\" &eset for &b{player}";
    private String homeSetOthersNotifyPlayer = "&eYour home: &a\"{home}\" &ehas been set by &b{sender}";
    private String homeSetOthersUnsuccessful = "&cCould not save home &a\"{home}\" &efor &b{player}&c, please try again!";
    private String homeTeleportOthers = "";
    private String homeTeleportOthersNotifyPlayer = "";
    private String homeTeleportOthersUnsuccessful = "&cCould not find home &a\"{home}\" for &b{player}&c!";
    private String homeDeletedOthers = "&eHome: &a\"{home}\" &edeleted for &b{player}";
    private String homeDeletedOthersNotifyPlayer = "&eYour home: &a\"{home}\" &ehas been deleted by &b{sender}";
    private String homeDeleteOthersUnsuccessful = "&cCould not delete home &a\"{home}\"&c, please try again!";

    @Comment("")
    private String commandReloaded = "&eConfig files have been reloaded!";
    private String commandNoPermissions = "&cYou do not have permissions to run this command!";
    private String commandPlayerDoesNotExist = "&cThis player does not exist!";
    private String commandPlayerOnly = "&cThis command is for players only!";


}
