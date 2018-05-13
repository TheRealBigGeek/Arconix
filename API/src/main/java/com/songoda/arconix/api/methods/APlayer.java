package com.songoda.arconix.api.methods;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * ArconixPlayer that handles and makes various computations relating to players.
 */
@SuppressWarnings("unused")
public class APlayer {

    private Player player;

    /**
     * Creates new APlayer based off of the specified player.
     *
     * @param pl The player to use.
     */
    public APlayer(Player pl) {
        player = pl;
    }

    /**
     * Gets the cardinal direction the player is facing.
     * @return The cardinal direction the player is facing.
     */
    public String getPlayerDirection() {
        String dir;
        float y = player.getLocation().getYaw();
        if (y < 0) {
            y += 360;
        }
        y %= 360;
        int i = (int) ((y + 8) / 22.5);
        switch (i) {
            case 0:
                dir = "NORTH";
                break;
            case 1:
                dir = "NORTH";
                break;
            case 2:
                dir = "EAST";
                break;
            case 3:
                dir = "EAST";
                break;
            case 4:
                dir = "EAST";
                break;
            case 5:
                dir = "EAST";
                break;
            case 6:
                dir = "EAST";
                break;
            case 7:
                dir = "SOUTH";
                break;
            case 8:
                dir = "SOUTH";
                break;
            case 9:
                dir = "SOUTH";
                break;
            case 10:
                dir = "WEST";
                break;
            case 11:
                dir = "WEST";
                break;
            case 12:
                dir = "WEST";
                break;
            case 13:
                dir = "WEST";
                break;
            case 14:
                dir = "WEST";
                break;
            case 15:
                dir = "NORTH";
                break;
            default:
                dir = "NORTH";
                break;
        }
        return dir;
    }

    /**
     * Gets the entity the player is currently looking at.
     * @return The entity the player is currently looking at.
     */
    public Entity getTarget() {
        assert player != null;
        Entity target = null;
        double targetDistanceSquared = 0;
        final double radiusSquared = 1;
        final Vector l = player.getEyeLocation().toVector(), n = player.getLocation().getDirection().normalize();
        final double cos45 = Math.cos(Math.PI / 4);
        for (final Hanging other : player.getWorld().getEntitiesByClass(Hanging.class)) {
            if (other == player)
                continue;
            if (target == null || targetDistanceSquared > other.getLocation().distanceSquared(player.getLocation())) {
                final Vector t = other.getLocation().add(0, 1, 0).toVector().subtract(l);
                if (n.clone().crossProduct(t).lengthSquared() < radiusSquared && t.normalize().dot(n) >= cos45) {
                    target = other;
                    targetDistanceSquared = target.getLocation().distanceSquared(player.getLocation());
                }
            }
        }
        return target;
    }

    /**
     * Plays the specified sound to the player.
     * @param s The sound to play.
     */
    public void playSound(Sound s) {
        player.playSound(player.getLocation(), s, 0.6F, 10.0F);
    }
}
