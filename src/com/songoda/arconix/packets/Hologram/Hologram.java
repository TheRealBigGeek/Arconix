package com.songoda.arconix.packets.Hologram;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public interface Hologram
{

    void spawnHolograms(Location location, List<String> holograms);

    void spawnHologram(Location location, String line);

    void despawnHologram(Location location);

    void showHologram(Location location, Player p);

    void hideHolograms(Player p);

    void hideHologram(Location location, Player p);

    void unload();

    ArrayList<Location> getLocations();

}
