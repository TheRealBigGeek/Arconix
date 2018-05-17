package com.songoda.arconix.api.methods.sound;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Simplifies methods for playing sounds for players.
 * Sound format <code>ENUM_VALUE:pitch</code>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ASound {

    /**
     * Plays the specified sound for all players on the server.
     *
     * @param sound The sound to play. Format: <code>ENUM_VALUE:pitch</code>
     */
    public static void playSoundForAll(String sound) {
        if (sound == null || sound.equals(""))
            return;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sound);
        }
    }

    /**
     * Plays a set of sounds specified for all players on the server.
     * @param sounds A list of sounds to play. Format: <code>ENUM_VALUE:pitch</code>
     */
    public static void playSoundForAll(List<String> sounds) {
        if (sounds == null || sounds.size() < 1)
            return;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sounds);
        }
    }

    /**
     * Plays a set of sounds for only the specified player.
     * @param p The player to play the sounds for.
     * @param sounds A list of sounds to play. Format: <code>ENUM_VALUE:pitch</code>
     */
    public static void playSound(Player p, List<String> sounds) {
        if (p == null || sounds == null || sounds.size() < 1)
            return;
        for (String sound : sounds) {
            playSound(p, sound);
        }
    }

    /**
     * Plays the specified sound for the specified player.
     * @param p The player to play the sound for.
     * @param sound The sound to play. Format: <code>ENUM_VALUE:pitch</code>
     */
    public static void playSound(Player p, String sound) {
        if (p == null || sound == null || sound.equals(""))
            return;
        float pitch = Float.valueOf(sound.split(":")[1]);
        sound = sound.split(":")[0];
        p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(sound), pitch, 5.0F);
    }
}