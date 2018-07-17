package com.songoda.arconix.nms.v1_13_R1;

import com.songoda.arconix.api.methods.Formatting;
import com.songoda.arconix.api.packets.TabList;
import net.minecraft.server.v1_13_R1.IChatBaseComponent;
import net.minecraft.server.v1_13_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_13_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by Kiran Hart on 4/15/2017.
 */
public class TabList1_13R1 implements TabList {

    Formatting formatting = new Formatting();

    @Override
    public void sendTablist(Player p, String header, String footer) {

        PacketPlayOutPlayerListHeaderFooter headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();

        IChatBaseComponent headerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + formatting.formatText(header) + "\"}");
        IChatBaseComponent footerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + formatting.formatText(footer) + "\"}");

        try {
            Field headerField = headerFooterPacket.getClass().getDeclaredField("a");
            Field footerField = headerFooterPacket.getClass().getDeclaredField("b");
            headerField.setAccessible(true);
            footerField.setAccessible(true);
            headerField.set(headerFooterPacket, headerComp);
            footerField.set(headerFooterPacket, footerComp);
            headerField.setAccessible(false);
            footerField.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        connection.sendPacket(headerFooterPacket);
    }
}
