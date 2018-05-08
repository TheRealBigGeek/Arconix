package com.songoda.arconix.plugin;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.events.CustomEventListeners;
import com.songoda.arconix.api.events.PlayerListeners;
import com.songoda.arconix.api.events.RegionEvents;
import com.songoda.arconix.api.handlers.HologramHandler;
import com.songoda.arconix.api.massivestats.MassiveStats;
import com.songoda.arconix.api.packets.PacketLibrary;
import com.songoda.arconix.plugin.Commands.ArconixCMD;
import com.songoda.arconix.plugin.Commands.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

/**
 * Created by songoda on 3/16/2017.
 */
public class Arconix extends JavaPlugin implements Listener {
    private String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    private ArconixAPI api;

    @Override
    public void onEnable() {
        api = ArconixAPI.getApi().init(this);

        setupConfig();
        api.packetLibrary = new PacketLibrary();
        api.packetLibrary.setupPackets(serverVersion);

        //@SuppressWarnings("unused") MCUpdate update = new MCUpdate(this, true);

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        getServer().getPluginManager().registerEvents(new CustomEventListeners(), this);
        getServer().getPluginManager().registerEvents(new RegionEvents(), this);

        List<BaseCommand> commands = Collections.singletonList(new ArconixCMD());
        for (BaseCommand command : commands) {
            getCommand(command.getName()).setExecutor(command);
        }

        new MassiveStats(this, 900);
    }

    @Override
    public void onDisable() {
        api.hologramFile.saveConfig();
    }

    public void reload() {
        api.reload();
        reloadConfig();
        saveConfig();
    }

    private void setupConfig() {

        getConfig().addDefault("settings.Countdown-format", "%d hour(s), %d min(s), %d sec(s)");
        getConfig().addDefault("settings.ECO-format", "#,###.00");

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static Arconix pl() {
        return (Arconix) Bukkit.getServer().getPluginManager().getPlugin("Arconix");
    }

    public ArconixAPI hook(JavaPlugin jp) {
        getLogger().info("Plugin hooked: " + jp.getName());
        return api;
    }

    public ArconixAPI getApi() {
        return api;
    }

    public HologramHandler getHolo() {
        return api.getHolo();
    }

    public String getServerVersion() {
        return serverVersion;
    }
}