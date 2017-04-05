package com.songoda.arconix.Methods;

/**
 * Created by songoda on 4/2/2017.
 */
public class Maths {

    Object object;

    public Maths(Object obj) {
        object = obj;
    }

    public boolean isNumeric() {
        String s = (String)object;
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
