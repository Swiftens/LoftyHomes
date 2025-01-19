package me.swiftens.loftyHomes;

import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import lombok.Getter;
import me.swiftens.loftyHomes.commands.*;
import me.swiftens.loftyHomes.configs.MessageConfiguration;
import me.swiftens.loftyHomes.data.DataManager;
import me.swiftens.loftyHomes.data.YamlDataManager;
import me.swiftens.loftyHomes.utils.MessageUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.nio.file.Path;

public final class LoftyHomes extends JavaPlugin {

    private static final YamlConfigurationProperties PROPERTIES = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
            .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
            .build();

    private BukkitTask saveTimer;
    @Getter
    private DataManager dataManager;
    @Getter
    private MessageConfiguration messageConfiguration;

    @Override
    public void onEnable() {
        reloadConfigs();
        this.dataManager = new YamlDataManager(this);

        registerCommands();

        // Save data every 10 minutes
        saveTimer = new BukkitRunnable() {
            @Override
            public void run() {
                dataManager.saveData(false);
            }
        }.runTaskTimer(this, 12000, 12000);

        new Metrics(this, 24408);
    }

    @Override
    public void onDisable() {
        saveTimer.cancel();
        saveTimer = null;

        dataManager.saveData(true);
    }


    private void registerCommands() {
        getCommand("sethome").setExecutor(new SetHomeExecutor(this));
        getCommand("home").setExecutor(new HomeExecutor(this));
        getCommand("delhome").setExecutor(new DelHomeExecutor(this));
        getCommand("adminsethome").setExecutor(new AdminSetHomeExecutor(this));
        getCommand("adminhome").setExecutor(new AdminHomeExecutor(this));
        getCommand("admindelhome").setExecutor(new AdminDelHomeExecutor(this));
        getCommand("loftyhomesreload").setExecutor(new ReloadExecutor(this));
    }

    public void reloadConfigs() {
        Path configPath = new File(getDataFolder(), "messages.yml").toPath();
        messageConfiguration = YamlConfigurations.update(
                configPath,
                MessageConfiguration.class,
                PROPERTIES
        );
        MessageUtils.setPrefix(messageConfiguration.getPrefix());

    }

}
