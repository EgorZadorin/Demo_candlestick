package utils;

import static java.lang.Integer.parseInt;

public class TimeUtils {

    public static int convertToSecondstime(String time) {
        int hours = parseInt(time.substring(0, 2));
        int minutes = parseInt(time.substring(3, 5));
        int seconds = parseInt(time.substring(6, 8));
        seconds += (hours * 60 + minutes) * 60;
        return seconds;
    }

    public static String convertToReadableTime(long seconds) {
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}