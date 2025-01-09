package me.swiftens.loftyHomes.data;

import me.swiftens.loftyHomes.utils.DataAccess;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.*;

public class YamlDataManager implements DataManager {

    private final JavaPlugin plugin;
    private final Map<UUID, DataAccess> dataMap;

    public YamlDataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.dataMap = new HashMap<>();
    }

    @Override
    public boolean setHome(UUID playerId, String name, Location location) {
        if (location.getWorld() == null) return false;
        DataAccess data = dataMap.computeIfAbsent(playerId, k -> new DataAccess(plugin, playerId));

        data.getFile().set(name+".x", location.getX());
        data.getFile().set(name+".y", location.getY());
        data.getFile().set(name+".z", location.getZ());
        data.getFile().set(name+".world", location.getWorld().getName());
        data.getFile().set(name+".yaw", location.getYaw());
        data.getFile().set(name+".pitch", location.getPitch());
        return true;
    }

    @Nullable
    @Override
    public Location retrieveHome(UUID playerId, String name) {
        DataAccess data = dataMap.computeIfAbsent(playerId, k -> new DataAccess(plugin, playerId));
        ConfigurationSection home = data.getFile().getConfigurationSection(name);
        if (home == null || home.getString("world") == null) {
            return null;
        }

        return new Location(
                Bukkit.getWorld(home.getString("world")),
                home.getDouble("x"),
                home.getDouble("y"),
                home.getDouble("z"),
                (float) home.getDouble("yaw"),
                (float) home.getDouble("pitch")
        );
    }

    @Override
    public boolean deleteHome(UUID playerId, String name) {
        DataAccess data = dataMap.computeIfAbsent(playerId, k -> new DataAccess(plugin, playerId));

        data.getFile().set(name, null);
        return true;
    }

    @Override
    public int getHomeCount(UUID playerId) {
        return listHomes(playerId).size();
    }

    @Override
    public Set<String> listHomes(UUID playerId) {
        DataAccess data = dataMap.computeIfAbsent(playerId, k -> new DataAccess(plugin, playerId));
        return data.getFile().getKeys(false);
    }

    @Override
    public void saveData(boolean close) {
        for (UUID playerId : dataMap.keySet()) {
            DataAccess access = dataMap.get(playerId);

            access.saveFile();
            access.reloadFile();
            if (close) dataMap.remove(playerId);
        }
    }

}
