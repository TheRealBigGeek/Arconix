package com.songoda.arconix.packets.Hologram;

import com.songoda.arconix.Arconix;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hologram1_8R3 implements Hologram {

    Arconix plugin = Arconix.pl();

    private List<EntityArmorStand> entityList;
    private ArrayList<Location> locations = new ArrayList<>();

    public Hologram1_8R3() {
        reload();
    }

    public void spawnHolograms(Location location, List<String> holograms) {
        for (String line : holograms) {
            spawnHologram(location, line);
            location = new Location(location.getWorld(), location.getX(), location.getY() - .25, location.getZ());
            locations.add(location);
        }
    }

    public void spawnHologram(Location location, String line) {
        EntityArmorStand entity = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
        entity.setCustomName(plugin.format().formatText(line));
        entity.setCustomNameVisible(true);
        entity.setInvisible(true);
        entity.setGravity(false); // Only diference between 1.10R1 and 1.9R2
        entity.setSmall(true);

        NBTTagCompound compoundTag = new NBTTagCompound(); // 1.8R3 does not have the marker option but has it if you use NBT tags.
        entity.c(compoundTag);
        compoundTag.setBoolean("Marker", true);
        entity.f(compoundTag);

        entityList.add(entity);
        reloadHolograms();
        locations.add(location);
    }

    public void despawnHologram(Location location) {
        EntityArmorStand ent = null;
        for (EntityArmorStand entity : entityList) {
            Location loc = new Location(entity.world.getWorld(), entity.locX, entity.locY, entity.locZ, 0, 0);
            if (loc.equals(location)) {
                ent = entity;
            }
        }
        entityList.remove(ent);
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            hideHologram(ent, p);
        }
    }

    public void showHolograms(Player p) {
        for (EntityArmorStand entity : entityList) {
            showHologram(entity, p);
        }
    }

    public void reloadHolograms() {
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            hideHolograms(p);
            showHolograms(p);
        }
    }

    public void unload() {
            for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                hideHolograms(p);
            }
    }

    @Override
    public ArrayList<Location> getLocation() {
        return locations;
    }

    public void showHologram(EntityArmorStand entity, Player p) {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(entity);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }

    public void hideHolograms(Player p) {
        for (EntityArmorStand entity : entityList) {
            hideHologram(entity, p);
        }
    }

    public void hideHologram(EntityArmorStand entity, Player p) {
        if (entity != null) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entity.getId());
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void reload() {
        entityList = new ArrayList<>();
        Map<Location, List<String>> map = plugin.holo.getHologramList();
        for (Map.Entry<Location, List<String>> entry : map.entrySet()) {
            spawnHolograms(entry.getKey(), entry.getValue());
        }
    }
}
