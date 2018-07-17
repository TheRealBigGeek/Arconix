package com.songoda.arconix.api.packets;

import org.bukkit.entity.Player;

/**
 * Handles NMS code for sending messages to the player via the Title and/or Subtitle
 */
public interface Title {

    /**
     * Sends the specified player a title with the specified params.
     *
     * @param p       The player to send the title to.
     * @param msg     The message for the title.
     * @param fadeIn  How long it takes the title to fade in, in seconds.
     * @param stay    How long the title is to be visible on screen, in seconds.
     * @param fadeOut How long the title is to fade out, in seconds.
     */
    void sendTitle(Player p, String msg, int fadeIn, int stay, int fadeOut);

    /**
     * Sends the specified player a title with a subtitle with the specified params.
     * @param p The player to send the title to.
     * @param msg The message (in the SUBTITLE) for the title.
     * @param fadeIn How long it takes the title to fade in, in seconds.
     * @param stay How long the title is to be visible on screen, in seconds.
     * @param fadeOut How long the title is to fade out, in seconds.
     */
    void sendSubitle(Player p, String msg, int fadeIn, int stay, int fadeOut);
}
