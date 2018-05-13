package com.songoda.arconix.api.packets;

import org.bukkit.entity.Player;

/**
 * Handles NMS code for the Player's actionbar
 */
public interface ActionBar {
    /**
     * Sends a message to the player via their action bar (tool tip).
     *
     * @param p       The player to send the message to.
     * @param message The message to send.
     */
    void sendActionBar(Player p, String message);
}
