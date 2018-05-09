package com.songoda.arconix.api.methods.formatting;

import com.songoda.arconix.api.ArconixAPI;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class Time {

    public static String makeReadable(Long time) {
        return String.format(ArconixAPI.getApi().plugin.getConfig().getString("settings.Countdown-format"), TimeUnit.MILLISECONDS.toHours(time), TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)), TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }
}