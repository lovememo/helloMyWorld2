package com.opm.common.enumdict;

/**
 * Created by Lovememo on 2017/1/2 0002.
 */
public enum DtlType {
    ////1-接口 2-文件处理
    INTERFACE("1","接口处理"), FILE("2", "文件处理");
    private String key;
    private String text;

    private DtlType(String key,String text) {
        this.key = key;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public static DtlType valueOfCode(String codeStr) {
        String code = null == codeStr ? "" : codeStr;
        DtlType ret = null;
        switch (code) {
            case "1":
                ret = DtlType.INTERFACE;
                break;
            case "2":
                ret = DtlType.FILE;
                break;
            default:
                ret = DtlType.FILE;
                break;
        }

        return ret;
    }
}
