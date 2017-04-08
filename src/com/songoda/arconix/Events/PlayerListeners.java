package com.songoda.arconix.Events;

import com.songoda.arconix.Arconix;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

/**
 * Created by songoda on 4/4/2017.
 */
public class PlayerListeners implements Listener {

    Arconix plugin = Arconix.pl();

    private boolean isVisible;
    private ArrayList<Location> locations = new ArrayList<>();
    private int maxDistance = plugin.hologramFile.getConfig().getInt("max-view-distance");

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        for (Location loc : plugin.packetLibrary.getHologramManager().getLocation()) {
            if (!locations.contains(loc)) {
                locations.add(loc);
            }
        }

        for (Location loc : locations) {

            if (loc.distance(p.getLocation()) >= maxDistance) {
                plugin.packetLibrary.getHologramManager().hideHolograms(p);
                isVisible = false;
                return;
            } else {

                if (!isVisible) {
                    plugin.packetLibrary.getHologramManager().showHolograms(p);
                    isVisible = true;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        plugin.packetLibrary.getHologramManager().showHolograms(p);
    }

}
