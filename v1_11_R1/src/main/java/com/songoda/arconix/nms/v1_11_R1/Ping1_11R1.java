package com.songoda.arconix.nms.v1_11_R1;

import com.songoda.arconix.api.packets.UserPing;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class Ping1_11R1 implements UserPing {

    @Override
    public int getPing(Player p) {
        return ((CraftPlayer) p).getHandle().ping;
    }
}
