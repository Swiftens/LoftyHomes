package me.swiftens.loftyHomes.data;

import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DataManager {

    /**
     * Set a player's home
     *
     * @param playerId of the player setting the home.
     * @param name of the home
     * @param location of the home.
     * @return true if the home was set successfully
     */
    boolean setHome(UUID playerId, String name, Location location);

    /**
     * Retrieve a player's home
     *
     * @param playerId of the player
     * @param name of the home
     * @return the location of the player's home
     *         null if the home doesn't exist.
     */
    @Nullable
    Location retrieveHome(UUID playerId, String name);

    /**
     * Delete a player's home
     *
     * @param playerId of the player
     * @param name of the home
     * @return false if the home was not deleted
     *         this will return true even if the
     *         home did not exist beforehand
     */
    boolean deleteHome(UUID playerId, String name);

    /**
     * Retrieve the amount of homes linked to a player.
     *
     * @param playerId of the player
     * @return the amount of homes a player has set
     */
    int getHomeCount(UUID playerId);

    /**
     * Retrieve the name of the homes a player has set
     *
     * @param playerId of the player
     * @return a set of the names of the player's homes
     */
    Set<String> listHomes(UUID playerId);

    /**
     * Save the data to the memory.
     * @param close whether to close the database
     */
    void saveData(boolean close);

}
