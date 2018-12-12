package com.necer.timing;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by necer on 2018/12/12.
 */
public class Util {

    public static float sp2px(Context context, float spVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static float dp2px(Context context, int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public static String changeTime(int second, boolean isDividerText) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }

        String hour = (h + "").length() == 1 ? ("0" + h) : (h + "");
        String min = (d + "").length() == 1 ? ("0" + d) : (d + "");
        String sec = (s + "").length() == 1 ? ("0" + s) : (s + "");

        return hour + (isDividerText ? "时" : ":") + min + (isDividerText ? "分" : ":") + sec + (isDividerText ? "秒" : "");
    }
}
