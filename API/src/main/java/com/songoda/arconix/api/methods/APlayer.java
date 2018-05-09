package com.songoda.arconix.api.methods;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by songoda on 4/2/2017.
 */
@SuppressWarnings("unused")
public class APlayer {

    private Player player;

    public APlayer(Player pl) {
        player = pl;
    }


    public String getPlayerDirection() {
        String dir;
        float y = player.getLocation().getYaw();
        if (y < 0) {
            y += 360;
        }
        y %= 360;
        int i = (int) ((y + 8) / 22.5);
        if (i == 0) {
            dir = "NORTH";
        } else if (i == 1) {
            dir = "NORTH";
        } else if (i == 2) {
            dir = "EAST";
        } else if (i == 3) {
            dir = "EAST";
        } else if (i == 4) {
            dir = "EAST";
        } else if (i == 5) {
            dir = "EAST";
        } else if (i == 6) {
            dir = "EAST";
        } else if (i == 7) {
            dir = "SOUTH";
        } else if (i == 8) {
            dir = "SOUTH";
        } else if (i == 9) {
            dir = "SOUTH";
        } else if (i == 10) {
            dir = "WEST";
        } else if (i == 11) {
            dir = "WEST";
        } else if (i == 12) {
            dir = "WEST";
        } else if (i == 13) {
            dir = "WEST";
        } else if (i == 14) {
            dir = "WEST";
        } else if (i == 15) {
            dir = "NORTH";
        } else {
            dir = "NORTH";
        }
        return dir;
    }

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

    public void playSound(Sound s) {
        player.playSound(player.getLocation(), s, 0.6F, 10.0F);
    }
}
