package com.songoda.arconix.packets.ActionBar;

import com.songoda.arconix.Arconix;
import com.songoda.arconix.Events.Custom.ActionBarSendEvent;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar1_7R4 implements ActionBar {

    Arconix arconix = Arconix.getInstance();

    @Override
    public void sendActionBar(Player p, String message) {
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ActionBarSendEvent actionBarSendEvent = new ActionBarSendEvent(p, message);
        arconix.getServer().getPluginManager().callEvent(actionBarSendEvent);
        if (actionBarSendEvent.isCancelled()) {
            return;
        }
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }

}
