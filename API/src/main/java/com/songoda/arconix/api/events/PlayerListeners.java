package com.songoda.arconix.api.events;

import com.songoda.arconix.api.handlers.HologramHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * Created by songoda on 4/4/2017.
 */
public class PlayerListeners implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        try {
            if (event.getChunk() == null) {
                return;
            }
            new HologramHandler().stream(event.getChunk());
        } catch (Exception ignore) {
        }
    }
}