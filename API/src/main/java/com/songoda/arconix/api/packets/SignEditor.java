package com.songoda.arconix.api.packets;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * Handles NMS specific code for opening the Sign Editor GUI.
 */
public interface SignEditor {

    /**
     * Opens the sign editor GUI for the specified sign and player.
     *
     * @param p    The player to use the GUI.
     * @param sign The sign to edit.
     */
    void openSignEditor(Player p, Sign sign);

}
