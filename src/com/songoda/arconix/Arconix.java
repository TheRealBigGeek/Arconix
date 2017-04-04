package com.songoda.arconix;

import com.comphenix.protocol.PacketType;
import com.songoda.arconix.API.Update;
import org.bukkit.plugin.java.JavaPlugin;

import com.songoda.arconix.API.MCUpdate;
import com.songoda.arconix.Handlers.HologramHandler;
import com.songoda.arconix.Methods.*;
import com.songoda.arconix.Utils.ConfigWrapper;
import com.songoda.arconix.packets.PacketLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by songoda on 3/16/2017.
 */
public class Arconix extends JavaPlugin implements Listener {

    //Github test

    public boolean v1_7 = Bukkit.getServer().getClass().getPackage().getName().contains("1_7");
    public boolean v1_8 = Bukkit.getServer().getClass().getPackage().getName().contains("1_8");

    public String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public PacketLibrary packetLibrary;

    public HologramHandler holo;

    public MCUpdate update;

    public void onDisable() {
        hologramFile.saveConfig();
    }

    public void onEnable() {
        loadHoloFile();
        holo = new HologramHandler();
        packetLibrary = new PacketLibrary();
        packetLibrary.setupPackets();

        try {
             update = new MCUpdate(this, true);
        } catch (IOException e) {
            Bukkit.getLogger().info("Arconix Failed initialize MCUpdate");
        }
    }

    public ConfigWrapper hologramFile = new ConfigWrapper(this, "", "holograms.yml");

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("Arconix")) {
                if (args[0].equalsIgnoreCase("actionbar")) {

                    String text = "";
                    for (int i = 1; i < args.length; i++) {
                        text += " " + args[i];
                    }
                    packetLibrary.getActionBarManager().sendActionBar((Player) sender, text);
                } else if (args[0].equalsIgnoreCase("hologram")) {

                    String text = "";
                    for (int i = 2; i < args.length; i++) {
                        text += " " + args[i];
                    }

                    Location location = ((Player) sender).getLocation().add(0.5, 0, 0.5);

                    holo.createHologram(location, args[1], text);
                } else if (args[0].equalsIgnoreCase("show")) {
                    packetLibrary.getHologramManager().showHolograms((Player) sender);
                }
                //Added Title command to test sub titles.
                else if (args[0].equalsIgnoreCase("ptitle")) {
                    packetLibrary.getTitleManager().sendTitle((Player) sender, "&cA Title", 20, 120, 20);
                    packetLibrary.getTitleManager().sendSubitle((Player) sender, "&cA Title", 20, 120, 20);
                }
                //coneEffect(((Player)sender));
            }
        }
        return true;
    }

    private void loadHoloFile() {
        hologramFile.getConfig().options().copyDefaults(true);
        hologramFile.saveConfig();
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
