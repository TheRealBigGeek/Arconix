package com.songoda.arconix.api.methods.formatting;

import com.songoda.arconix.api.ArconixAPI;

import java.util.concurrent.TimeUnit;

/**
 * Handles various formatting for time related data.
 */
@SuppressWarnings("unused")
public class TimeComponent {

    /**
     * Makes the specified Unix Epoch time human readable as per the format settings in the Arconix config.
     *
     * @param time The time to convert.
     * @return A human readable string representing to specified time.
     */
    public static String makeReadable(Long time) {
        if (time == null)
            return "";
        return String.format(ArconixAPI.getApi().plugin.getConfig().getString("settings.Countdown-format"), TimeUnit.MILLISECONDS.toHours(time), TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)), TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }
}