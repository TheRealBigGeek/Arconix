package com.songoda.arconix.nms.v1_8_R2;

import com.songoda.arconix.api.packets.UserPing;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class Ping1_8R2 implements UserPing {

    @Override
    public int getPing(Player p) {
        return ((CraftPlayer) p).getHandle().ping;
    }
}
