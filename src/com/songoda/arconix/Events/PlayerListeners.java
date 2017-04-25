package com.songoda.arconix.Events;

import com.songoda.arconix.Arconix;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

/**
 * Created by songoda on 4/4/2017.
 */
public class PlayerListeners implements Listener {

    Arconix plugin = Arconix.pl();

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (event.getChunk() == null) {
            return;
        }
        plugin.holo.stream(event.getChunk());
    }

}
