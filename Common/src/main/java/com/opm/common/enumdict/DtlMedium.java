package com.opm.common.enumdict;

/**
 * Created by Lovememo on 2017/1/2 0002.
 */
public enum DtlMedium {
    /*���� TL-TXT���� TT-TXT�ָ��� CS-CSV�ļ� EX-EXCEL�ļ� XM-XML JN-JSON*/
    TXT_FIXED_SIZE("TL","TXT����"), TXT_SEPARATOR("TT", "TXT�ָ���"), CSV("CS", "CSV�ļ�"), EXCEL("EX","EXCEL�ļ�"), XML("XM", "XML"), JSON("JN","JSON");
    private String key;
    private String text;

    private DtlMedium(String key, String text) {
        this.key = key;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public static DtlMedium valueOfCode(String codeStr) {
        String code = null == codeStr ? "" : codeStr;
        DtlMedium ret = null;
        switch (code) {
            case "TL":
                ret = DtlMedium.TXT_FIXED_SIZE;
                break;
            case "TT":
                ret = DtlMedium.TXT_SEPARATOR;
                break;
            case "CS":
                ret = DtlMedium.CSV;
                break;
            case "EX":
                ret = DtlMedium.EXCEL;
                break;
            case "XM":
                ret = DtlMedium.XML;
                break;
            case "JN":
                ret = DtlMedium.JSON;
                break;
            default:
                ret = DtlMedium.TXT_SEPARATOR;
                break;
        }
        return ret;
    }
}
