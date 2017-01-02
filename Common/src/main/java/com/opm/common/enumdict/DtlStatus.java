package com.opm.common.enumdict;

/**
 * 数据处理字典
 * Created by kfzx-jinjf on 2016/12/16.
 */
public enum DtlStatus {
    SAVED("1","已保存"), PROCESSING("2", "处理中"), SUCCEEDED("3", "成功"), FAILED("4", "失败"), UNKNOWN("-1", "未知");
    private String key;
    private String text;

    private DtlStatus(String key,String text) {
        this.key = key;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public static DtlStatus valueOfCode(String codeStr) {
        String code = null == codeStr ? "" : codeStr;
        DtlStatus ret = null;
        switch (code) {
            case "1":
                ret = DtlStatus.SAVED;
                break;
            case "2":
                ret = DtlStatus.PROCESSING;
                break;
            case "3":
                ret = DtlStatus.SUCCEEDED;
                break;
            case "4":
                ret = DtlStatus.FAILED;
                break;
            default:
                ret = DtlStatus.UNKNOWN;
                break;
        }

        return ret;
    }

}
