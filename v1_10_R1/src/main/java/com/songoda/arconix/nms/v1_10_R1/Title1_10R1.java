package com.songoda.arconix.nms.v1_10_R1;

import com.songoda.arconix.api.events.Custom.TitleSendEvent;
import com.songoda.arconix.api.methods.Formatting;
import com.songoda.arconix.api.packets.Title;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_10_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Kiran Hart on 4/4/2017.
 */
public class Title1_10R1 implements Title {

    Formatting formatting = new Formatting();

    @Override
    public void sendTitle(Player p, String msg, int fadeIn, int stay, int fadeOut) {

        CraftPlayer craftPlayer = (CraftPlayer) p;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent title = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + formatting.formatText(msg) + "\"}");
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, title, fadeIn, stay, fadeOut);
        TitleSendEvent titleSendEvent = new TitleSendEvent(p, msg, fadeIn, stay, fadeOut, "TITLE");
        Bukkit.getServer().getPluginManager().callEvent(titleSendEvent);
        if (titleSendEvent.isCancelled()) {
            return;
        }
        playerConnection.sendPacket(packet);
    }

    @Override
    public void sendSubitle(Player p, String msg, int fadeIn, int stay, int fadeOut) {

        CraftPlayer craftPlayer = (CraftPlayer) p;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent title = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + formatting.formatText(msg) + "\"}");
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, title, fadeIn, stay, fadeOut);
        TitleSendEvent titleSendEvent = new TitleSendEvent(p, msg, fadeIn, stay, fadeOut, "SUBTITLE");
        Bukkit.getServer().getPluginManager().callEvent(titleSendEvent);
        if (titleSendEvent.isCancelled()) {
            return;
        }
        playerConnection.sendPacket(packet);
    }
}
