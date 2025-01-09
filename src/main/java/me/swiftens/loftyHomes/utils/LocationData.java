package me.swiftens.loftyHomes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationData {
    public double x;
    public double y;
    public double z;
    public String world;
    public float yaw;
    public float pitch;

    public LocationData(double x, double y, double z, String world, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static LocationData serialize(Location location) {
        if (location.getWorld() == null) return null;
        return new LocationData(
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getWorld().getName(),
                location.getYaw(),
                location.getPitch()
        );
    }

    public static Location deserialize(LocationData data) {
        World world = Bukkit.getWorld(data.world);
        if (world == null) return null;

        return new Location(
                world,
                data.x,
                data.y,
                data.z,
                data.yaw,
                data.pitch
        );
    }

}
