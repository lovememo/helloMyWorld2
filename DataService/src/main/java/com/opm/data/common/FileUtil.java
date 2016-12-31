package com.opm.data.common;

/**
 * Created by kfzx-jinjf on 2016/12/26.
 */
public class FileUtil {
    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return lastIndex == 0 ? "" : fileName.substring(lastIndex + 1).toUpperCase();
    }
}
