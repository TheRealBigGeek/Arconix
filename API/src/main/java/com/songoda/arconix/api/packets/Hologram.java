package com.songoda.arconix.api.packets;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles NMS specific code for rendering "Holograms"
 */
public interface Hologram {

    /**
     * Spawns multiple holograms at the specified location.
     *
     * @param location  The location to spawn the holograms at.
     * @param holograms A list of Strings for the holograms.
     */
    void spawnHolograms(Location location, List<String> holograms);

    /**
     * Spawns a hologram at the specified location.
     * @param location The location to spawn the hologram at.
     * @param line The text the hologram is to display.
     */
    void spawnHologram(Location location, String line);

    /**
     * Removes/Despawns any holograms at the specified location.
     * @param location The location to despawn holograms from.
     */
    void despawnHologram(Location location);

    /**
     * Adds the specified location to the hologram list.
     * @param location The location to add.
     */
    void addHologram(Location location);

    /**
     * Gets a list of locations where holograms are present.
     * @return A list of locations where holograms are present.
     */
    ArrayList<Location> getLocations();
}
