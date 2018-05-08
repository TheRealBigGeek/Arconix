package com.songoda.arconix.api;

import com.songoda.arconix.api.handlers.HologramHandler;
import com.songoda.arconix.api.methods.*;
import com.songoda.arconix.api.packets.PacketLibrary;
import com.songoda.arconix.api.utils.ConfigWrapper;
import com.songoda.arconix.api.utils.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@SuppressWarnings("WeakerAccess")
public class ArconixAPI {
    private static ArconixAPI api;

    public JavaPlugin plugin;

    public boolean v1_7 = Bukkit.getServer().getClass().getPackage().getName().contains("1_7");
    public boolean v1_8 = Bukkit.getServer().getClass().getPackage().getName().contains("1_8");

    public ArrayList<Player> inSelectionMode = new ArrayList<>();
    public HashMap<Player, Location> selectedLocationOne = new HashMap<>();
    public HashMap<Player, Location> selectedLocationTwo = new HashMap<>();

    public ConfigWrapper hologramFile;
    public ConfigWrapper regionFile;
    public PacketLibrary packetLibrary;
    private HologramHandler holo;

    private ArconixAPI() {
        hologramFile = new ConfigWrapper(plugin, "", "holograms.yml");
        regionFile = new ConfigWrapper(plugin, "", "regions.yml");

        loadHoloFile();

        holo = new HologramHandler();
    }

    public static ArconixAPI getApi() {
        if (api == null)
            api = new ArconixAPI();

        return api;
    }

    public ArconixAPI init(JavaPlugin _plugin) {
        plugin = _plugin;

        hologramFile = new ConfigWrapper(plugin, "", "holograms.yml");
        regionFile = new ConfigWrapper(plugin, "", "regions.yml");

        loadHoloFile();

        holo = new HologramHandler();

        return api;
    }

    public Serializer serialize() {
        return Serializer.getInstance();
    }

    public Formatting format() {
        return new Formatting();
    }

    public GUI getGUI() {
        return new GUI();
    }

    public HologramHandler getHolo() {
        return holo;
    }

    public APlayer getPlayer(Player player) {
        return new APlayer(player);
    }

    public Sounds sounds() {
        return new Sounds();
    }

    public Maths doMath() {
        return new Maths();
    }

    public void coneEffect(final Player player) {
        BukkitTask redstone = new BukkitRunnable() {
            double phi = 0;

            public void run() {
                phi = phi + Math.PI / 8;
                double x, y, z;

                Location location1 = new Location(player.getWorld(), -168.5, 98.0, 185.5);
                for (double t = 0; t <= 5 * Math.PI; t = t + Math.PI / 16) {
                    for (double i = 0; i <= 1; i = i + 1) {
                        x = 0.4 * (2 * Math.PI - t) * 0.5 * cos(t + phi + i * Math.PI);
                        y = 0.5 * t;
                        z = 0.4 * (2 * Math.PI - t) * 0.5 * sin(t + phi + i * Math.PI);
                        location1.add(x, y, z);
                        api.packetLibrary.getParticleManager().broadcastParticle(location1, 0, 0, 0, 0, "REDSTONE", 1);
                        location1.subtract(x, y, z);
                    }

                }

                if (phi > 10 * Math.PI) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 2);
    }

    private void loadHoloFile() {
        hologramFile.getConfig().options().copyDefaults(true);
        hologramFile.saveConfig();
    }

    public void reload() {
        hologramFile.reloadConfig();
    }
}