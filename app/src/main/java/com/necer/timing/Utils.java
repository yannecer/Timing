package com.necer.timing;

import android.widget.EditText;

/**
 * Created by necer on 2017/11/8.
 */

public class Utils {



    public static String changeTime(int second) {
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

        return hour + ":" + min + ":" + sec + "";
    }


    public static boolean isEmpty(EditText editText) {
        String string = editText.getText().toString();
        return string.isEmpty();
    }

    public static int getTime(EditText editText) {
        String string = editText.getText().toString();
        return Integer.parseInt(string);
    }



}
