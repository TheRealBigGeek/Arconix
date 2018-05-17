package com.songoda.arconix.api.methods;

/**
 * Created by songoda on 4/2/2017. Use {@link com.songoda.arconix.api.methods.math.AMath}
 */
@Deprecated
public class Maths {

    public boolean isNumeric(String s) {
        if (s == null || s.equals(""))
            return false;
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static boolean isInt(String number) {
        if (number == null || number.equals(""))
            return false;
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}