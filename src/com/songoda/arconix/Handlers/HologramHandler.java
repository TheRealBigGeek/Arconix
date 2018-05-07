package com.songoda.arconix.Handlers;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.method.serialize.Serialize;
import com.songoda.arconix.packets.PacketLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songoda on 4/2/2017.
 */
public class HologramHandler {

    Arconix plugin = Arconix.getInstance();

    public HologramHandler() {

    }

    public Map<Location, List<String>> getHologramList() {
        Map<Location, List<String>> map = new HashMap<>();
        if (plugin.hologramFile.getConfig().contains("Holograms")) {
            ConfigurationSection cs = plugin.hologramFile.getConfig().getConfigurationSection("Holograms");
            for (String key : cs.getKeys(false)) {
                List<String> list = plugin.hologramFile.getConfig().getStringList("Holograms." + key + ".lines");
                Location location = plugin.serialize().unserializeLocation(plugin.hologramFile.getConfig().getString("Holograms." + key + ".location"));

                if (list.size() == 1 && list.get(0).equals("")) {
                    plugin.hologramFile.getConfig().set("Holograms." + key, null);
                    continue;
                }

                map.put(location, list);

            }
        }
        return map;
    }
    public void deleteHologram(Player p, String name) {
        if (plugin.hologramFile.getConfig().contains("Holograms")) {
            Location location = plugin.serialize().unserializeLocation(plugin.hologramFile.getConfig().getString("Holograms." + name + ".location"));
            removeHologram(location, name);
            plugin.hologramFile.getConfig().set("Holograms." + name, null);
        }
    }

    public void removeHologram(Location location, String name) {
        List<String> list = plugin.hologramFile.getConfig().getStringList("Holograms." + name + ".lines");
        double y = location.getY();
        for (int i = 0; i < (list.size() + 2); i++) {
            Location location2 = location.clone();
            location.setY(y);
            y = y  - .25;
            plugin.packetLibrary.getHologramManager().despawnHologram(location2.clone());
        }
    }

    public void createHologram(Player p, String title, List<String> lines) {
        if (plugin.hologramFile.getConfig().contains("Holograms." + title + ".location"))
            removeHologram(plugin.serialize().unserializeLocation(plugin.hologramFile.getConfig().getString("Holograms." + title + ".location")), title);
        saveHologram(p.getLocation(), title, lines);
    }

    public void createHologram(Player p, String title, String line) {
        List<String> lines = new ArrayList<>();
        lines.add(line);
        createHologram(p, title, lines);
    }

    public void editHologram(String title, List<String> lines) {
        Location location = plugin.serialize().unserializeLocation(plugin.hologramFile.getConfig().getString("Holograms." + title + ".location"));
        removeHologram(location.clone(), title);
        saveHologram(location, title, lines);
    }

    public void saveHologram(Location location, String title, List<String> list) {
        location.setX(Math.round(location.getX() * 1000.0) / 1000.0);
        location.setZ(Math.round(location.getZ() * 1000.0) / 1000.0);
        String serial = plugin.serialize().serializeLocation(location);
        plugin.hologramFile.getConfig().set("Holograms." + title + ".location", serial);
        plugin.hologramFile.getConfig().set("Holograms." + title + ".lines", list);
        plugin.packetLibrary.getHologramManager().reload();
    }
}
