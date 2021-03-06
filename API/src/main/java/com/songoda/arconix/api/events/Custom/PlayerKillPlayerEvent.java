package com.songoda.arconix.api.events.Custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class PlayerKillPlayerEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player killer;
    private Player victim;

    public PlayerKillPlayerEvent(Player killer, Player victim) {
        this.killer = killer;
        this.victim = victim;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getKiller() {
        return killer;
    }

    public Player getVictim() {
        return victim;
    }

    public Location getKillerLocation() {
        return killer.getLocation();
    }

    public Location getVictimLocation() {
        return victim.getLocation();
    }

    public double getKillerHealth() {
        return killer.getHealth();
    }

    public ItemStack getItemUsedToKill() {
        return killer.getItemInHand();
    }
}
