package com.songoda.arconix.api.methods;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Use {@link com.songoda.arconix.api.methods.sound.ASound}
 */
@Deprecated
public class Sounds {

    public void playSoundForAll(String sound) {
        if (sound == null || sound.equals(""))
            return;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sound);
        }
    }

    public void playSoundForAll(List<String> sounds) {
        if (sounds == null || sounds.size() < 1)
            return;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sounds);
        }
    }

    public void playSound(Player p, List<String> sounds) {
        if (p == null || sounds == null || sounds.size() < 1)
            return;
        for (String sound : sounds) {
            playSound(p, sound);
        }
    }

    public void playSound(Player p, String sound) {
        if (p == null || sound == null || sound.equals(""))
            return;
        float pitch = Float.valueOf(sound.split(":")[1]);
        sound = sound.split(":")[0];
        p.playSound(p.getLocation(), Sound.valueOf(sound), pitch, 5.0F);
    }
}
