package com.songoda.arconix.api.events.Custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Kiran Hart on 4/12/2017.
 */
public class RegionMoveInEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player p;
    private String regionName;
    private Location locationOne;
    private Location locationTwo;

    public RegionMoveInEvent(Player p, String regionName, Location locationOne, Location locationTwo) {
        this.p = p;
        this.regionName = regionName;
        this.locationOne = locationOne;
        this.locationTwo = locationTwo;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return p;
    }

    public String getRegionName() {
        return regionName;
    }

    public Location getLocationOne() {
        return locationOne;
    }

    public Location getLocationTwo() {
        return locationTwo;
    }
}
