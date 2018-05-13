package com.songoda.arconix.api.packets;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Handles NMS specific code for particle displays.
 */
public interface Particle {

    /**
     * Shows particle effects to only the player specified.
     *
     * @param player The player to show effects for.
     * @param loc    The location of the particles
     * @param x      X coord to move the particles to.
     * @param y      Y coord to move the particles to.
     * @param z      Z coord to move the particles to.
     * @param speed  The speed at which to move the particles.
     * @param effect The name of the particle effect.
     * @param amount The amount of particles to render.
     */
    void displayParticle(Player player, Location loc, float x, float y, float z, int speed, String effect, int amount);

    /**
     * Creates particles in the world visible to all players.
     * @param loc The player to show effects for.
     * @param x X coord to move the particles to.
     * @param y Y coord to move the particles to.
     * @param z Z coord to move the particles to.
     * @param speed The speed at which to move the particles.
     * @param effect The name of the particle effect.
     * @param amount The amount of particles to render.
     */
    void broadcastParticle(Location loc, float x, float y, float z, int speed, String effect, int amount);

}
