package com.songoda.arconix.api.methods.formatting;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.utils.DefaultFontInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

@SuppressWarnings({"Duplicates", "unused"})
public class TextComponent {

    public static void center(Player player, String message) {
        if (message == null || message.equals(""))
            player.sendMessage("");
        //noinspection ConstantConditions
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public static String formatEconomy(double amt) {
        DecimalFormat formatter = new DecimalFormat(ArconixAPI.getApi().plugin.getConfig().getString("settings.ECO-format"));
        return formatter.format(amt);
    }

    public static String formatTitle(String text) {
        if (ArconixAPI.getApi().v1_7 || ArconixAPI.getApi().v1_8) {
            if (text.length() > 31)
                text = text.substring(0, 29) + "...";
        }
        text = TextComponent.formatText(text);
        return text;
    }

    public static String formatText(String text) {
        return TextComponent.formatText(text, false);
    }

    public static String formatText(String text, boolean cap) {
        if (cap)
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String convertToInvisibleString(String s) {
        StringBuilder hidden = new StringBuilder();
        for (char c : s.toCharArray()) hidden.append(ChatColor.COLOR_CHAR + "").append(c);
        return hidden.toString();
    }
}