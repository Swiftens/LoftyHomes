package me.swiftens.loftyHomes.data;

import me.swiftens.loftyHomes.utils.DataAccess;
import me.swiftens.loftyHomes.utils.LocationData;
import org.bukkit.Location;
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

        data.getFile().set(name, LocationData.serialize(location));
        return true;
    }

    @Nullable
    @Override
    public Location retrieveHome(UUID playerId, String name) {
        DataAccess data = dataMap.computeIfAbsent(playerId, k -> new DataAccess(plugin, playerId));

        LocationData locationData = data.getFile().getObject(name, LocationData.class);
        if (locationData == null) return null;

        return LocationData.deserialize(locationData);
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
