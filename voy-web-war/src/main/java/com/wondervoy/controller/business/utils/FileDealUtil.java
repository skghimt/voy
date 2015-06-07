package com.wondervoy.controller.business.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by ckzhang on 15-1-12.
 */
public class FileDealUtil {

    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String fileRename() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        sb.append(""+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }

        String strData = new SimpleDateFormat("yyyy-MM-dd-").format(Calendar.getInstance().getTime());

        return strData + sb.toString();
    }
}
