package com.songoda.arconix.api.events.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Kiran Hart on 4/8/2017.
 */
public class ActionBarSendEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;

    private Player p;
    private String msg;


    public ActionBarSendEvent(Player p, String msg) {
        this.p = p;
        this.msg = msg;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
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

    public Player getPlayer() {
        return p;
    }

    public String getMessage() {
        return msg;
    }

}
