package com.alisadmitrieva.galleryproject.utils;

import android.content.Context;

import com.alisadmitrieva.galleryproject.R;

public class CommonUtils {

    private static int TABLET_COLUMN_COUNT = 3;
    private static int PHONE_COLUMN_COUNT = 2;

    public static int getColumnCount(Context context) {
        if (isTablet(context)) {
            return TABLET_COLUMN_COUNT;
        } else {
            return PHONE_COLUMN_COUNT;
        }
    }

    // determine if device is tablet or not
    public static boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.isTablet);
    }

}
