package com.songoda.arconix.api.packets;

import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public interface TabList {

    void sendTablist(Player p, String header, String footer);
}
