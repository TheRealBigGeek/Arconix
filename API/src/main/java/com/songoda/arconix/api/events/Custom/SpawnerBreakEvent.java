package com.songoda.arconix.api.events.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class SpawnerBreakEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;

    private Player p;
    private ItemStack tool;
    private String spawnType;

    public SpawnerBreakEvent(Player p, ItemStack tool, String spawnType) {
        this.p = p;
        this.tool = tool;
        this.spawnType = spawnType;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return p;
    }

    public ItemStack getTool() {
        return tool;
    }

    public String getSpawnType() {
        return spawnType;
    }

}
