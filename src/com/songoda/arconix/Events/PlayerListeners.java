package com.songoda.arconix.Events;

import com.songoda.arconix.Arconix;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by songoda on 4/4/2017.
 */
public class PlayerListeners implements Listener {

    Arconix plugin = Arconix.pl();

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (event.getFrom().getChunk() != event.getTo().getChunk()) {
            plugin.packetLibrary.getHologramManager().showHolograms(p);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        plugin.packetLibrary.getHologramManager().showHolograms(p);
    }


}
