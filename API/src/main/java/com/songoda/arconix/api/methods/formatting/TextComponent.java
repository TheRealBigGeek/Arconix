package com.songoda.arconix.api.methods.formatting;

import com.songoda.arconix.api.ArconixAPI;
import com.songoda.arconix.api.utils.DefaultFontInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

/**
 * Handles various text formatting methods for simplicity.
 */
@SuppressWarnings({"Duplicates", "unused"})
public class TextComponent {

    /**
     * Sends a message to the player with the message centered.
     *
     * @param player  The player to send the message to.
     * @param message The message to send.
     */
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

    /**
     * Formats the specified double into the Economy format specified in the Arconix config.
     * @param amt The double to format.
     * @return The economy formatted double.
     */
    public static String formatEconomy(double amt) {
        DecimalFormat formatter = new DecimalFormat(ArconixAPI.getApi().plugin.getConfig().getString("settings.ECO-format"));
        return formatter.format(amt);
    }

    /**
     * Formats the specified test for a title. If MC 1.7 or 1.8, truncates to 32 characters.
     * @param text The text to format.
     * @return The formatted text.
     */
    public static String formatTitle(String text) {
        if (ArconixAPI.getApi().v1_7 || ArconixAPI.getApi().v1_8) {
            if (text.length() > 31)
                text = text.substring(0, 29) + "...";
        }
        text = TextComponent.formatText(text);
        return text;
    }

    /**
     * Formats the text and translates ChatColor characters.
     * @param text The text to format.
     * @return The formatted text.
     */
    public static String formatText(String text) {
        return TextComponent.formatText(text, false);
    }

    /**
     * Formats the text and translates ChatColor characters.
     * @param text The text to format.
     * @param cap Whether to capitalize the first character in the specified text.
     * @return The formatted text.
     */
    public static String formatText(String text, boolean cap) {
        if (cap)
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Converts the specified string to an invisible string (invisible to MC clients).
     * @param s The string to convert.
     * @return The string but invisible to MC clients.
     */
    public static String convertToInvisibleString(String s) {
        StringBuilder hidden = new StringBuilder();
        for (char c : s.toCharArray()) hidden.append(ChatColor.COLOR_CHAR + "").append(c);
        return hidden.toString();
    }
}