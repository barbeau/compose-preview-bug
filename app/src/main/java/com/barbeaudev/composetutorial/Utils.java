package com.barbeaudev.composetutorial;

import android.os.Build;

public class Utils {

    /**
     * Returns true if the platform supports providing speed and bearing accuracy values, false if it does not
     *
     * @return true if the platform supports providing speed and bearing accuracy values, false if it does not
     */
    public static boolean isSpeedAndBearingAccuracySupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }
}
