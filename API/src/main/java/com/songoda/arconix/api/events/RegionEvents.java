package com.songoda.arconix.api.events;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.events.Custom.RegionEnterEvent;
import com.songoda.arconix.api.events.Custom.RegionExitEvent;
import com.songoda.arconix.api.events.Custom.RegionMoveInEvent;
import com.songoda.arconix.api.methods.formatting.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;


public class RegionEvents implements Listener {

    private ArconixAPI arconix = ArconixAPI.getApi();

    private int x1;
    private int y1;
    private int z1;
    private String world1;

    private int x2;
    private int y2;
    private int z2;
    private String world2;

    private int playerX;
    private int playerY;
    private int playerZ;

    private boolean isInCuboid(Location l, Location c1, Location c2) {
        double maxx = Math.max(c1.getX(), c2.getX());
        double maxy = Math.max(c1.getY(), c2.getY());
        double maxz = Math.max(c1.getZ(), c2.getZ());
        Vector max = new Vector(maxx, maxy, maxz);

        double minx = Math.min(c1.getX(), c2.getX());
        double miny = Math.min(c1.getY(), c2.getY());
        double minz = Math.min(c1.getZ(), c2.getZ());
        Vector min = new Vector(minx, miny, minz);

        return l.toVector().isInAABB(min, max);
    }

    /**
     * This method is called when a player
     * enters a new region, once they enter we'll run through
     * all the activations (if any) for that region and play
     * them to the player. Only onEnter activations will be
     * played to the player using this method.
     */
    @EventHandler
    public void onRegionEnter(RegionEnterEvent e) {

        ArrayList<String> enterPresets = new ArrayList<>();

        Player p = e.getPlayer();
        if (arconix.regionFile.getConfig().getConfigurationSection("regions." + e.getRegionName() + ".activations") != null) {

            ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions." + e.getRegionName() + ".activations.onEnter");

            if (section == null) return;

            for (String keys : section.getKeys(false)) {

                if (keys.equalsIgnoreCase("actionbar")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onEnter." + keys);
                    arconix.packetLibrary.getActionBarManager().sendActionBar(p, TextComponent.formatText(text));
                }

                if (keys.equalsIgnoreCase("ping")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onEnter." + keys);
                    p.sendMessage(TextComponent.formatText(text).replace("{ping}", arconix.packetLibrary.getPingManager().getPing(p) + ""));
                }

                if (keys.equalsIgnoreCase("text")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onEnter." + keys);
                    p.sendMessage(TextComponent.formatText(text));
                }

                if (keys.equalsIgnoreCase("title")) {

                    int fadeIn = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".fadein");
                    int stay = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".stay");
                    int fadeOut = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".fadeout");
                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".text");
                    arconix.packetLibrary.getTitleManager().sendTitle(p, TextComponent.formatText(text), fadeIn, stay, fadeOut);
                }

                if (keys.equalsIgnoreCase("subtitle")) {

                    int fadeIn = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".fadein");
                    int stay = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".stay");
                    int fadeOut = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".fadeout");
                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onEnter." + keys + ".text");
                    arconix.packetLibrary.getTitleManager().sendSubitle(p, TextComponent.formatText(text), fadeIn, stay, fadeOut);
                }
            }
        }
    }

    @EventHandler
    public void onRegionExit(RegionExitEvent e) {

        ArrayList<String> enterPresets = new ArrayList<>();

        Player p = e.getPlayer();
        if (arconix.regionFile.getConfig().getConfigurationSection("regions." + e.getRegionName() + ".activations") != null) {

            ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions." + e.getRegionName() + ".activations.onExit");

            if (section == null) return;

            for (String keys : section.getKeys(false)) {

                if (keys.equalsIgnoreCase("actionbar")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onExit." + keys);
                    arconix.packetLibrary.getActionBarManager().sendActionBar(p, TextComponent.formatText(text));
                }

                if (keys.equalsIgnoreCase("ping")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onExit." + keys);
                    p.sendMessage(TextComponent.formatText(text).replace("{ping}", arconix.packetLibrary.getPingManager().getPing(p) + ""));
                }

                if (keys.equalsIgnoreCase("text")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onExit." + keys);
                    p.sendMessage(TextComponent.formatText(text));
                }

                if (keys.equalsIgnoreCase("title")) {

                    int fadeIn = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onExit." + keys + ".fadein");
                    int stay = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onExit." + keys + ".stay");
                    int fadeOut = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onExit." + keys + ".fadeout");
                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onExit." + keys + ".text");
                    arconix.packetLibrary.getTitleManager().sendTitle(p, TextComponent.formatText(text), fadeIn, stay, fadeOut);
                }

                if (keys.equalsIgnoreCase("subtitle")) {

                    int fadeIn = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onExit." + keys + ".fadein");
                    int stay = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onExit." + keys + ".stay");
                    int fadeOut = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onExit." + keys + ".fadeout");
                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onExit." + keys + ".text");
                    arconix.packetLibrary.getTitleManager().sendSubitle(p, TextComponent.formatText(text), fadeIn, stay, fadeOut);
                }
            }
        }
    }

    @EventHandler
    public void onRegionWalk(RegionMoveInEvent e) {

        ArrayList<String> enterPresets = new ArrayList<>();

        Player p = e.getPlayer();

        if (arconix.regionFile.getConfig().getConfigurationSection("regions." + e.getRegionName() + ".activations") != null) {

            ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions." + e.getRegionName() + ".activations.onWalk");

            if (section == null) return;

            for (String keys : section.getKeys(false)) {

                if (keys.equalsIgnoreCase("actionbar")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onWalk." + keys);
                    arconix.packetLibrary.getActionBarManager().sendActionBar(p, TextComponent.formatText(text));
                }

                if (keys.equalsIgnoreCase("ping")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onWalk." + keys);
                    p.sendMessage(TextComponent.formatText(text).replace("{ping}", arconix.packetLibrary.getPingManager().getPing(p) + ""));
                }

                if (keys.equalsIgnoreCase("text")) {

                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onWalk." + keys);
                    p.sendMessage(TextComponent.formatText(text));
                }

                if (keys.equalsIgnoreCase("title")) {

                    int fadeIn = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".fadein");
                    int stay = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".stay");
                    int fadeOut = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".fadeout");
                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".text");
                    arconix.packetLibrary.getTitleManager().sendTitle(p, TextComponent.formatText(text), fadeIn, stay, fadeOut);
                }

                if (keys.equalsIgnoreCase("subtitle")) {

                    int fadeIn = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".fadein");
                    int stay = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".stay");
                    int fadeOut = arconix.regionFile.getConfig().getInt("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".fadeout");
                    String text = arconix.regionFile.getConfig().getString("regions." + e.getRegionName() + ".activations.onWalk." + keys + ".text");
                    arconix.packetLibrary.getTitleManager().sendSubitle(p, TextComponent.formatText(text), fadeIn, stay, fadeOut);
                }
            }
        }
    }

    //Selection
    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();

        if (!arconix.inSelectionMode.contains(p)) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (!arconix.inSelectionMode.contains(p)) return;

        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {

            if (arconix.selectedLocationOne.containsKey(p)) {
                arconix.selectedLocationOne.remove(p);
                arconix.selectedLocationOne.put(p, e.getClickedBlock().getLocation());
                p.sendMessage(TextComponent.formatText("&eFirst location updated!"));
                return;
            }
            arconix.selectedLocationOne.put(p, e.getClickedBlock().getLocation());
            p.sendMessage(TextComponent.formatText("&eYou selected the first location."));
            return;
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (arconix.selectedLocationTwo.containsKey(p)) {
                arconix.selectedLocationTwo.remove(p);
                arconix.selectedLocationTwo.put(p, e.getClickedBlock().getLocation());
                p.sendMessage(TextComponent.formatText("&eSecond location updated!"));
                return;
            }
            arconix.selectedLocationTwo.put(p, e.getClickedBlock().getLocation());
            p.sendMessage(TextComponent.formatText("&eYou selected the second location."));
        }
    }


    @EventHandler
    public void onPlayerEnterRegion(PlayerMoveEvent e) {

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {

            this.playerX = e.getTo().getBlockX();
            this.playerY = e.getTo().getBlockY();
            this.playerZ = e.getTo().getBlockZ();

            ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions");

            if (section == null) return;

            for (String ids : section.getKeys(false)) {

                x1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.x");
                y1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.y");
                z1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.z");
                world1 = arconix.regionFile.getConfig().getString("regions." + ids + ".location1.world");
                x2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.x");
                y2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.y");
                z2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.z");
                world2 = arconix.regionFile.getConfig().getString("regions." + ids + ".location2.world");

                Location location1 = new Location(Bukkit.getWorld(world1), x1, y1, z1);
                Location location2 = new Location(Bukkit.getWorld(world2), x2, y2, z2);

                if (isInCuboid(e.getFrom(), location1, location2)) return;

                if (isInCuboid(e.getTo(), location1, location2)) {

                    RegionEnterEvent regionEnterEvent = new RegionEnterEvent(e.getPlayer(), ids, location1, location2);
                    Bukkit.getServer().getPluginManager().callEvent(regionEnterEvent);

                }
            }
        }

    }

    @EventHandler
    public void onPlayerLeaveRegion(PlayerMoveEvent e) {

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {

            this.playerX = e.getTo().getBlockX();
            this.playerY = e.getTo().getBlockY();
            this.playerZ = e.getTo().getBlockZ();

            ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions");

            if (section == null) return;

            for (String ids : section.getKeys(false)) {

                x1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.x");
                y1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.y");
                z1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.z");
                world1 = arconix.regionFile.getConfig().getString("regions." + ids + ".location1.world");
                x2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.x");
                y2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.y");
                z2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.z");
                world2 = arconix.regionFile.getConfig().getString("regions." + ids + ".location2.world");

                Location location1 = new Location(Bukkit.getWorld(world1), x1, y1, z1);
                Location location2 = new Location(Bukkit.getWorld(world2), x2, y2, z2);

                if (isInCuboid(e.getTo(), location1, location2)) return;

                if (isInCuboid(e.getFrom(), location1, location2)) {
                    RegionExitEvent regionEnterEvent = new RegionExitEvent(e.getPlayer(), ids, location1, location2);
                    Bukkit.getServer().getPluginManager().callEvent(regionEnterEvent);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMoveInRegion(PlayerMoveEvent e) {

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {

            this.playerX = e.getTo().getBlockX();
            this.playerY = e.getTo().getBlockY();
            this.playerZ = e.getTo().getBlockZ();

            ConfigurationSection section = arconix.regionFile.getConfig().getConfigurationSection("regions");

            if (section == null) return;

            for (String ids : section.getKeys(false)) {

                x1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.x");
                y1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.y");
                z1 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location1.z");
                world1 = arconix.regionFile.getConfig().getString("regions." + ids + ".location1.world");
                x2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.x");
                y2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.y");
                z2 = arconix.regionFile.getConfig().getInt("regions." + ids + ".location2.z");
                world2 = arconix.regionFile.getConfig().getString("regions." + ids + ".location2.world");

                Location location1 = new Location(Bukkit.getWorld(world1), x1, y1, z1);
                Location location2 = new Location(Bukkit.getWorld(world2), x2, y2, z2);

                if (isInCuboid(e.getTo(), location1, location2)) {
                    RegionMoveInEvent regionMoveInEvent = new RegionMoveInEvent(e.getPlayer(), ids, location1, location2);
                    Bukkit.getServer().getPluginManager().callEvent(regionMoveInEvent);
                }
            }

        }
    }
}
