package com.songoda.arconix.packets.Title;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Events.Custom.TitleSendEvent;
import com.songoda.arconix.Methods.Formatting;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class Title1_8R1 implements Title {

    Formatting formatting = new Formatting();
    Arconix arconix = Arconix.getInstance();

    @Override
    public void sendTitle(Player p, String msg, int fadeIn, int stay, int fadeOut) {

        CraftPlayer craftPlayer = (CraftPlayer) p;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent title = ChatSerializer.a("{\"text\": \"" + formatting.formatText(msg) + "\"}");
        PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.TITLE, title, fadeIn, stay, fadeOut);
        TitleSendEvent titleSendEvent = new TitleSendEvent(p, msg, fadeIn, stay, fadeOut, "TITLE");
        arconix.getServer().getPluginManager().callEvent(titleSendEvent);
        if (titleSendEvent.isCancelled()) {
            return;
        }
        playerConnection.sendPacket(packet);
    }

    @Override
    public void sendSubitle(Player p, String msg, int fadeIn, int stay, int fadeOut) {

        CraftPlayer craftPlayer = (CraftPlayer) p;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent title = ChatSerializer.a("{\"text\": \"" + formatting.formatText(msg) + "\"}");
        PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, title, fadeIn, stay, fadeOut);
        TitleSendEvent titleSendEvent = new TitleSendEvent(p, msg, fadeIn, stay, fadeOut, "SUBTITLE");
        arconix.getServer().getPluginManager().callEvent(titleSendEvent);
        if (titleSendEvent.isCancelled()) {
            return;
        }
        playerConnection.sendPacket(packet);
    }
}
