package com.songoda.arconix.api.methods;

/**
 * Created by songoda on 4/2/2017. Use {@link com.songoda.arconix.api.methods.math.AMath}
 */
@Deprecated
public class Maths {

    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static boolean isInt(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}