package com.github.log2c.b1lib1li_tv.common;

import java.util.Locale;

public class CommonUtils {
    public static String formatSeconds(long seconds) {
        String standardTime;
        if (seconds <= 0) {
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }

    public static String formatNumbers(long numbers) {
        String result = numbers + "";
        if (numbers > 10000) {
            return String.format(Locale.CHINA, "%1$.1f万", numbers / 10000.0f);
        } else
            return result;
    }
}
