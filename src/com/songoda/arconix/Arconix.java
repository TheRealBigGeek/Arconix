﻿package com.songoda.arconix;

import com.songoda.arconix.API.MCUpdate;
import com.songoda.arconix.Commands.ArconixCMD;
import com.songoda.arconix.Commands.BaseCommand;
import com.songoda.arconix.Events.CustomEventListeners;
import com.songoda.arconix.Events.PlayerListeners;
import com.songoda.arconix.Events.RegionEvents;
import com.songoda.arconix.Handlers.HologramHandler;
import com.songoda.arconix.Methods.*;
import com.songoda.arconix.Utils.ConfigWrapper;
import com.songoda.arconix.packets.PacketLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
/**
 * Created by songoda on 3/16/2017.
 */
public class Arconix extends JavaPlugin implements Listener {

    public static Map<String, Location> serializecache = new HashMap<>();
    public ArrayList<Player> inSelectionMode = new ArrayList<>();
    public HashMap<Player, Location> selectedLocationOne = new HashMap<>();
    public HashMap<Player, Location> selectedLocationTwo = new HashMap<>();

    public boolean v1_7 = Bukkit.getServer().getClass().getPackage().getName().contains("1_7");
    public boolean v1_8 = Bukkit.getServer().getClass().getPackage().getName().contains("1_8");

    public String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public PacketLibrary packetLibrary;
    public HologramHandler holo;
    public boolean hasUpdated;
    private Formatting format = new Formatting();

    public void onEnable() {
        setupConfig();
        loadHoloFile();
        holo = new HologramHandler();
        packetLibrary = new PacketLibrary();
        packetLibrary.setupPackets();

        try {
            MCUpdate update = new MCUpdate(this, true);
        } catch (IOException e) {
            Bukkit.getLogger().info("Arconix Failed initialize MCUpdate");
        }

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        getServer().getPluginManager().registerEvents(new CustomEventListeners(), this);
        getServer().getPluginManager().registerEvents(new RegionEvents(), this);

        List<BaseCommand> commands = Arrays.asList(new ArconixCMD());
        for (BaseCommand command : commands) {
            getCommand(command.getName()).setExecutor(command);
        }
    }
    public void reload() {
        hologramFile.reloadConfig();
        reloadConfig();
        saveConfig();
    }

    private void setupConfig() {

        getConfig().addDefault("settings.Countdown-format", "%d hour(s), %d min(s), %d sec(s)");
        getConfig().addDefault("settings.ECO-format", "#,###.00");

        getConfig().options().copyDefaults(true);
        saveConfig();
    }



    public void onDisable() {
        hologramFile.saveConfig();
    }

    public ConfigWrapper hologramFile = new ConfigWrapper(this, "", "holograms.yml");
    public ConfigWrapper regionFile = new ConfigWrapper(this, "", "regions.yml");

    private void loadHoloFile() {
        hologramFile.getConfig().options().copyDefaults(true);
        hologramFile.saveConfig();
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
                        packetLibrary.getParticleManager().broadcastParticle(location1, 0, 0, 0, 0, "REDSTONE", 1);
                        location1.subtract(x, y, z);
                    }

                }

                if (phi > 10 * Math.PI) {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0, 2);
    }

    public Formatting format() {
        return new Formatting();
    }

    public GUI getGUI() {
        return new GUI();
    }

    public APlayer getPlayer(Player player) {
        return new APlayer(player);
    }

    public Sounds sounds() {
        return new Sounds();
    }

    public Serializer serialize() {
        return new Serializer();
    }

    public Maths doMath() {
        return new Maths();
    }

    public static Arconix pl() {
        return (Arconix) Bukkit.getServer().getPluginManager().getPlugin("Arconix");
    }
}
