package com.songoda.arconix.nms.v1_13_R2;

import com.songoda.arconix.api.events.Custom.ActionBarSendEvent;
import com.songoda.arconix.api.packets.ActionBar;
import net.minecraft.server.v1_13_R2.ChatMessageType;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar1_13R2 implements ActionBar {

    @Override
    public void sendActionBar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.a((byte) 2));
        ActionBarSendEvent actionBarSendEvent = new ActionBarSendEvent(p, message);
        Bukkit.getServer().getPluginManager().callEvent(actionBarSendEvent);
        if (actionBarSendEvent.isCancelled()) {
            return;
        }
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }

}