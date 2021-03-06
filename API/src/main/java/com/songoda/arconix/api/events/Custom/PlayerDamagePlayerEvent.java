package com.songoda.arconix.api.events.Custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class PlayerDamagePlayerEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;

    private Player playerOne;
    private Player playerTwo;
    private Location playerOneLocation;
    private Location playerTwoLocation;
    private double playerOneHealth;
    private double playerTwoHealth;

    public PlayerDamagePlayerEvent(Player playerOne, Player playerTwo, Location playerOneLocation, Location playerTwoLocation, double playerOneHealth, double playerTwoHealth) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerOneLocation = playerOneLocation;
        this.playerTwoLocation = playerTwoLocation;
        this.playerOneHealth = playerOneHealth;
        this.playerTwoHealth = playerTwoHealth;
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

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Location getPlayerOneLocation() {
        return playerOneLocation;
    }

    public Location getPlayerTwoLocation() {
        return playerTwoLocation;
    }

    public double getPlayerOneHealth() {
        return playerOneHealth;
    }

    public double getPlayerTwoHealth() {
        return playerTwoHealth;
    }
}
