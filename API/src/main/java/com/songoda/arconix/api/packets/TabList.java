package com.songoda.arconix.api.packets;

import org.bukkit.entity.Player;

/**
 * Handles NSM code for the player tablist.
 */
public interface TabList {

    /**
     * Sends a new tab list to the player with the specified params.
     *
     * @param p      The player to send the tab list to.
     * @param header The header for the tablist.
     * @param footer The footer for the tablist.
     */
    void sendTablist(Player p, String header, String footer);
}
