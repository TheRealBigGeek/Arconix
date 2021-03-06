package com.songoda.arconix.api.events.Custom;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class CreeperExplodeEvent extends Event implements Cancellable {


    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;

    private Creeper creeper;
    private Location location;

    public CreeperExplodeEvent(Creeper creeper, Location location) {
        this.creeper = creeper;
        this.location = location;
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

    public Creeper getCreeper() {
        return creeper;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Player> getNearbyPlayers(double xRadius, double yRadius, double zRadius) {

        ArrayList<Player> players = new ArrayList<>();
        for (Entity entity : creeper.getNearbyEntities(xRadius, yRadius, zRadius)) {
            if (entity instanceof Player) {
                players.add((Player) entity);
            }
        }
        return players;
    }

    public ArrayList<Entity> getNearbyEntities(double xRadius, double yRadius, double zRadius) {

        ArrayList<Entity> entities = new ArrayList<>();
        for (Entity entity : creeper.getNearbyEntities(xRadius, yRadius, zRadius)) {
            entities.add(entity);
        }
        return entities;
    }
}
