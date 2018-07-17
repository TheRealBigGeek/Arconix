package com.songoda.arconix.nms.v1_13_R1;

import com.songoda.arconix.api.packets.Particle;
import net.minecraft.server.v1_13_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_13_R1.ParticleParamRedstone;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Particle1_13R1 implements Particle {

    public void displayParticle(Player player, Location loc, float x, float y, float z, int speed, String effect, int amount) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(new ParticleParamRedstone(1,1,1,1), false,
                (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), x, y, z, speed, amount);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void broadcastParticle(Location loc, float x, float y, float z, int speed, String effect, int amount) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(new ParticleParamRedstone(1,1,1,1), false,
                (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), x, y, z, speed, amount);
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

}
