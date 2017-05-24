package com.songoda.arconix.Events;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import com.songoda.arconix.Arconix;

/**
 * Created by songoda on 4/4/2017.
 */
public class PlayerListeners implements Listener {

	Arconix plugin = Arconix.pl();

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		if (event.getChunk() == null) {
			return;
		}
		plugin.holo.stream(event.getChunk());
	}

	@EventHandler
	public void onPlayerClickSign(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		if (plugin.usingSignEditor.contains(p) && e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST) {

			Sign sign = (Sign) e.getClickedBlock().getState();
			plugin.packetLibrary.getSignEditorManager().openSignEditor(p, sign);
		}
	}
}
