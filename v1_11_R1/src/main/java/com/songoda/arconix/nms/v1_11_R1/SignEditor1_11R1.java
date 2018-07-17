package com.songoda.arconix.nms.v1_11_R1;

import com.songoda.arconix.api.packets.SignEditor;
import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/7/2017.
 */
public class SignEditor1_11R1 implements SignEditor {

    @Override
    public void openSignEditor(Player p, Sign sign) {

        Location loc = sign.getLocation();
        BlockPosition pos = new BlockPosition(loc.getX(), loc.getY(), loc.getZ());
        EntityPlayer nmsPlayer = ((CraftPlayer) p).getHandle();
        TileEntitySign tileEntitySign = (TileEntitySign) nmsPlayer.world.getTileEntity(pos);
        PlayerConnection conn = nmsPlayer.playerConnection;

        tileEntitySign.isEditable = true;
        tileEntitySign.a(nmsPlayer);
        conn.sendPacket(new PacketPlayOutOpenSignEditor(pos));
    }

}

