package com.songoda.arconix.api.methods.math;

/**
 * Handles various math based logic.
 */
@SuppressWarnings("unused")
public class AMath {
    /**
     * Determines if the provided string is a valid number (int, double, float, or otherwise).
     *
     * @param s The string to check.
     * @return <code>true</code> if the string is numeric, otherwise <code>false</code>
     */
    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Validates whether or not the provided string is an Integer.
     * @param number The string to check.
     * @return <code>true</code> if the string is an Integer, otherwise <code>false</code>
     */
    public static boolean isInt(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}