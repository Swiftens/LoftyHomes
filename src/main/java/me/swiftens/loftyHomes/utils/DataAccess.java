package me.swiftens.loftyHomes.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.UUID;

public class DataAccess {

    private final JavaPlugin plugin;
    private final String fileName;
    private final File file;
    private FileConfiguration modifyFile;

    public DataAccess(JavaPlugin plugin, UUID playerId) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin can not be null");
        }
        this.plugin = plugin;
        this.fileName = playerId + ".yml";

        File directory = new File(plugin.getDataFolder(), "data");
        if (!directory.exists()) {
            if (!directory.mkdirs())
                plugin.getLogger().severe("Failed to create data directory!");
        }

        this.file = new File(directory, fileName);
    }

    public void reloadFile() {
        modifyFile = YamlConfiguration.loadConfiguration(this.file);

        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            modifyFile.setDefaults(defConfig);
        }
    }

    public FileConfiguration getFile() {
        if (modifyFile == null) {
            this.reloadFile();
        }
        return modifyFile;
    }

    public void saveFile() {
        if (modifyFile != null && file != null) {
            try {
                getFile().options().setHeader(Arrays.asList("Do not manually modify anything! They will not be saved.", "Please use commands to change anything."));
                getFile().save(file);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save file" + fileName);
            }
        }
    }

    public void saveDefaultConfig() {
        if (!file.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }

}
