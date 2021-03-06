package com.songoda.arconix.api.events;

import com.songoda.arconix.api.events.Custom.CreeperExplodeEvent;
import com.songoda.arconix.api.events.Custom.PlayerDamagePlayerEvent;
import com.songoda.arconix.api.events.Custom.PlayerKillPlayerEvent;
import com.songoda.arconix.api.events.Custom.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * Created by Kiran Hart on 4/16/2017.
 */
public class CustomEventListeners implements Listener {

    @EventHandler
    public void onSpawnerBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() != Material.MOB_SPAWNER) return;
        CreatureSpawner cs = (CreatureSpawner) e.getBlock().getState();
        SpawnerBreakEvent spawnerBreakEvent = new SpawnerBreakEvent(e.getPlayer(), e.getPlayer().getItemInHand(), cs.getSpawnedType().name());
        Bukkit.getServer().getPluginManager().callEvent(spawnerBreakEvent);
        if (spawnerBreakEvent.isCancelled()) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {
        if (!(e.getEntity() instanceof Creeper)) return;
        Creeper creeper = (Creeper) e.getEntity();
        CreeperExplodeEvent creeperExplodeEvent = new CreeperExplodeEvent(creeper, creeper.getLocation());
        Bukkit.getServer().getPluginManager().callEvent(creeperExplodeEvent);
        if (creeperExplodeEvent.isCancelled()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player playerOne = (Player) e.getEntity();
            Player playerTwo = (Player) e.getDamager();
            PlayerDamagePlayerEvent playerDamagePlayerEvent = new PlayerDamagePlayerEvent(playerOne, playerTwo, playerOne.getLocation(), playerTwo.getLocation(), playerOne.getHealth(), playerTwo.getHealth());
            Bukkit.getServer().getPluginManager().callEvent(playerDamagePlayerEvent);
            if (playerDamagePlayerEvent.isCancelled()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerKillPlayer(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller();
            Player victim = (Player) e.getEntity();
            PlayerKillPlayerEvent playerKillPlayerEvent = new PlayerKillPlayerEvent(killer, victim);
            Bukkit.getServer().getPluginManager().callEvent(playerKillPlayerEvent);
        }
    }

}
