package com.opm.data.common;

/**
 * Created by Lovememo on 2017/1/2 0002.
 */
public class StringUtil {
    public static String trimLeftZero(String str) {
        return str.trim().replaceAll("^(0+)", "").trim();
    }

    public static String trimRightSpace(String str) {
        return str.trim().replaceAll("\\s*$","").trim();
    }
    /*public static void main(String[] args) {
        System.out.println(trimLeftZero(" 010242233234000"));
        System.out.println(trimLeftZero("0 10242233234000"));
        String str = "0000010242233234000";
        System.out.println(str);
        System.out.println(trimRightSpace(str));
    }*/

}
