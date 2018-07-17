package com.songoda.arconix.api.methods;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.utils.DefaultFontInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by songoda on 4/2/2017. Use {@link com.songoda.arconix.api.methods.formatting.TextComponent}
 */
@Deprecated
public class Formatting {
    private final int CENTER_PX = 154;

    public void center(Player player, String message) {
        if (message == null || message.equals(""))
            player.sendMessage("");

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
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public String formatEconomy(double amt) {
        DecimalFormat formatter = new DecimalFormat(ArconixAPI.getApi().plugin.getConfig().getString("settings.ECO-format"));
        return formatter.format(amt);
    }

    public String formatTitle(String text) {
        if (text == null || text.equals(""))
            return "";
        if (ArconixAPI.getApi().v1_7 || ArconixAPI.getApi().v1_8) {
            if (text.length() > 31)
                text = text.substring(0, 29) + "...";
        }
        text = formatText(text);
        return text;
    }

    public String formatText(String text) {
        if (text == null || text.equals(""))
            return "";
        return formatText(text, false);
    }

    public String formatText(String text, boolean cap) {
        if (text == null || text.equals(""))
            return "";
        if (cap)
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String readableTime(long time) {
        return String.format(ArconixAPI.getApi().plugin.getConfig().getString("settings.Countdown-format"), TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }

    public String convertToInvisibleString(String s) {
        if (s == null || s.equals(""))
            return "";
        StringBuilder hidden = new StringBuilder();
        for (char c : s.toCharArray()) hidden.append(ChatColor.COLOR_CHAR + "").append(c);
        return hidden.toString();
    }

}
