package com.opm.common.model;

import java.io.Serializable;

/**
 * Created by kfzx-hanxh on 2016/12/30.
 */
public final class SimpleResponseModel implements Serializable {
    /**
     * UID
     */
    private static final long serialVersionUID = -622069033233536413L;

    /**
     * �ɹ���ʶ
     */
    public static final String SUCC_FLAG = "0";

    /**
     * ʧ�ܱ�ʶ
     */
    public static final String FAIL_FLAG = "1";

    /**
     * �����ʶ
     */
    public static final String WARN_FLG = "2";

    /**
     * ���ַ���
     */
    public static final String EMPTY = "";

    /**
     * ��ʶλ��Y ��ʾ�ɹ�; N����������ʶʧ��
     */
    private String flag;

    /**
     * ��ʾ��Ϣ
     */
    private String msg;

    /**
     * ���캯�� ��ע�� Ĭ���ǵ����ɹ�
     */
    public SimpleResponseModel() {
        this(SimpleResponseModel.SUCC_FLAG, SimpleResponseModel.EMPTY);
    }

    /**
     * ���캯��
     *
     * @param flag ִ�б�ʶ
     */
    public SimpleResponseModel(String flag) {
        this(flag, SimpleResponseModel.EMPTY);
    }

    /**
     * ���캯��
     *
     * @param flag ��ʶλ��Y ��ʾ�ɹ�; N����������ʶʧ��
     * @param msg  ��ʾ��Ϣ
     */
    public SimpleResponseModel(String flag, String msg) {
        switch (flag) {
            case SimpleResponseModel.SUCC_FLAG:
                this.flag = SimpleResponseModel.SUCC_FLAG;
                break;
            case SimpleResponseModel.WARN_FLG:
                this.flag = SimpleResponseModel.WARN_FLG;
                break;
            case SimpleResponseModel.FAIL_FLAG:
                this.flag = SimpleResponseModel.FAIL_FLAG;
                break;
            default:
                this.flag = SimpleResponseModel.FAIL_FLAG;
                break;
        }

        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
