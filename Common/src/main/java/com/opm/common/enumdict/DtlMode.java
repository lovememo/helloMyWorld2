package com.opm.common.enumdict;

/**
 * Created by Lovememo on 2017/1/2 0002.
 */
public enum DtlMode {
    //���䷽ʽ 1-�ļ� 2-����
    FILE("1", "�ļ��ӿڴ���"), NET("2", "�����ӿڴ���");
    private String key;
    private String text;

    private DtlMode(String key,String text) {
        this.key = key;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public static DtlMode valueOfCode(String codeStr) {
        String code = null == codeStr ? "" : codeStr;
        DtlMode ret = null;
        switch (code) {
            case "1":
                ret = DtlMode.FILE;
                break;
            case "2":
                ret = DtlMode.NET;
                break;
            default:
                ret = DtlMode.FILE;
                break;
        }

        return ret;
    }
}
