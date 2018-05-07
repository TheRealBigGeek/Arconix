package com.songoda.arconix.api.packets;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public interface Hologram {

    void spawnHolograms(Location location, List<String> holograms);

    void spawnHologram(Location location, String line);

    void despawnHologram(Location location);

    void addHologram(Location location);

    ArrayList<Location> getLocations();

}