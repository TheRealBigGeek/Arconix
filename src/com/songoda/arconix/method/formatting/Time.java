package com.songoda.arconix.method.formatting;

import com.songoda.arconix.Arconix;

import java.util.concurrent.TimeUnit;

public class Time {

    public static String makeReadable(Long time) {
        return String.format(Arconix.getInstance().getConfig().getString("settings.Countdown-format"), TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }

}
