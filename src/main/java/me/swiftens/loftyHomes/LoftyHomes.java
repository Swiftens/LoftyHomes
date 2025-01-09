package me.swiftens.loftyHomes;

import me.swiftens.loftyHomes.commands.DelHomeExecutor;
import me.swiftens.loftyHomes.commands.HomeExecutor;
import me.swiftens.loftyHomes.commands.SetHomeExecutor;
import me.swiftens.loftyHomes.data.DataManager;
import me.swiftens.loftyHomes.data.YamlDataManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public final class LoftyHomes extends JavaPlugin {

    private BukkitTask saveTimer;
    private DataManager dataManager;

    @Override
    public void onEnable() {
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

    public DataManager getDataManager() {
        return this.dataManager;
    }

    private void registerCommands() {
        getCommand("sethome").setExecutor(new SetHomeExecutor(this));
        getCommand("home").setExecutor(new HomeExecutor(this));
        getCommand("delhome").setExecutor(new DelHomeExecutor(this));
    }

}
