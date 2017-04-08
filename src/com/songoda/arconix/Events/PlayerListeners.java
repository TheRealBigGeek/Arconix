package com.songoda.arconix.Events;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Handlers.HologramHandler;
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

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        plugin.holo.handleMovement(e.getFrom(), e.getTo(), p);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        plugin.holo.handleMovement(p.getLocation(), null, p);
    }

}
