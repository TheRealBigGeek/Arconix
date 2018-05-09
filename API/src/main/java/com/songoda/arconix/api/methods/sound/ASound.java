package com.songoda.arconix.api.methods.sound;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ASound {

    public static void playSoundForAll(String sound) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sound);
        }
    }

    public static void playSoundForAll(List<String> sounds) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sounds);
        }
    }

    public static void playSound(Player p, List<String> sounds) {
        for (String sound : sounds) {
            playSound(p, sound);
        }
    }

    public static void playSound(Player p, String sound) {
        float pitch = Float.valueOf(sound.split(":")[1]);
        sound = sound.split(":")[0];
        p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(sound), pitch, 5.0F);
    }
}