package com.wyn.videoparser.common.utils;
/*
 *  @Author: wangyinuo
 *  @Date:   2024/6/28
 */

import java.util.regex.Pattern;

public class StringPattern {
    public static boolean isUrlString(String str) {
        String regex = "^([a-zA-Z]+)://([^:/]+)(?::(\\d+))?(/[^?#]*)?(?:\\?([^#]*))?(?:#(.*))?$";
        return Pattern.compile(regex).matcher(str).find();
    }

}
