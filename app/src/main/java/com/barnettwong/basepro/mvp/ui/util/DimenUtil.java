package com.barnettwong.basepro.mvp.ui.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.barnettwong.basepro.app.WEApplication;


/**
 * 获取屏幕的宽高
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = WEApplication.getAppContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources =  WEApplication.getAppContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
