package com.songoda.arconix.api;

import com.songoda.arconix.api.handlers.HologramHandler;
import com.songoda.arconix.api.methods.*;
import com.songoda.arconix.api.methods.inventory.AInventory;
import com.songoda.arconix.api.packets.PacketLibrary;
import com.songoda.arconix.api.utils.ConfigWrapper;
import com.songoda.arconix.api.utils.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

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
    }

    /**
     * Gets the current instance of the {@link ArconixAPI}
     *
     * @return The Current instance of the {@link ArconixAPI}
     */
    public static ArconixAPI getApi() {
        if (api == null)
            api = new ArconixAPI();

        return api;
    }

    /**
     * Initiates the ArconixAPI. This is Automatically called when Arconix loads and does <bold>NOT</bold> need to be called
     * @param _plugin The Arconix plugin
     * @return The instance of the {@link ArconixAPI} created.
     */
    public ArconixAPI init(JavaPlugin _plugin) {
        plugin = _plugin;

        hologramFile = new ConfigWrapper(plugin, "", "holograms.yml");
        regionFile = new ConfigWrapper(plugin, "", "regions.yml");

        loadHoloFile();

        holo = new HologramHandler();

        return api;
    }

    /**
     * Use {@link com.songoda.arconix.api.methods.serialize.Serialize}
     *
     * @return {@link Serializer}
     */
    @Deprecated
    public Serializer serialize() {
        return Serializer.getInstance();
    }

    /**
     * USe {@link com.songoda.arconix.api.methods.formatting.TextComponent}
     *
     * @return {@link Formatting}
     */
    @Deprecated
    public Formatting format() {
        return new Formatting();
    }

    /**
     * Use {@link AInventory}
     *
     * @return {@link GUI}
     */
    @Deprecated
    public GUI getGUI() {
        return new GUI();
    }

    /**
     * Gets the HologramHandler instance.
     * @return {@link HologramHandler}
     */
    public HologramHandler getHolo() {
        return holo;
    }

    /**
     * Gets an ArconixPlayer from a {@link Player} entity.
     * @param player The player to use.
     * @return An {@link APlayer} instance based on the supplied player.
     */
    public APlayer getPlayer(Player player) {
        return new APlayer(player);
    }

    /**
     * Use {@link com.songoda.arconix.api.methods.sound.ASound}
     * @return {@link Sounds}
     */
    @Deprecated
    public Sounds sounds() {
        return new Sounds();
    }

    /**
     * Use {@link com.songoda.arconix.api.methods.math.AMath}
     * @return {@link Maths}
     */
    @Deprecated
    public Maths doMath() {
        return new Maths();
    }

    /**
     * Load hologram file from disk
     */
    private void loadHoloFile() {
        hologramFile.getConfig().options().copyDefaults(true);
        hologramFile.saveConfig();
    }

    /**
     * Reloads ArconixAPI files.
     */
    public void reload() {
        hologramFile.reloadConfig();
    }
}