package com.opm.common.enumdict;

/**
 * Created by kfzx-jinjf on 2016/12/28.
 */
public enum CommonStatus {
    Y("Y","ÊÇ"), N("N", "·ñ");
    private String key;
    private String text;

    private CommonStatus(String key,String text) {
        this.key = key;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public static CommonStatus valueOfCode(String code) {
        CommonStatus ret = null;
        switch (code) {
            case "Y":
                ret = CommonStatus.Y;
                break;
            case "N":
                ret = CommonStatus.N;
                break;
            default:
                ret = CommonStatus.N;
                break;
        }

        return ret;
    }
}
