package com.songoda.arconix.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class Sounds {

    @Deprecated
    public void playSoundForAll(String sound) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sound);
        }
    }

    @Deprecated
    public void playSoundForAll(List<String> sounds) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            playSound(pl, sounds);
        }
    }

    @Deprecated
    public void playSound(Player p, List<String> sounds) {
        for (String sound : sounds) {
            playSound(p, sound);
        }
    }

    @Deprecated
    public void playSound(Player p, String sound) {
        float pitch = Float.valueOf(sound.split(":")[1]);
        sound = sound.split(":")[0];
        p.playSound(p.getLocation(), Sound.valueOf(sound), pitch, 5.0F);
    }
}
