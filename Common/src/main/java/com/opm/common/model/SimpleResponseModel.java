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
     * 成功标识
     */
    public static final String SUCC_FLAG = "0";

    /**
     * 失败标识
     */
    public static final String FAIL_FLAG = "1";

    /**
     * 警告标识
     */
    public static final String WARN_FLG = "2";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 标识位：Y 表示成功; N及其他，标识失败
     */
    private String flag;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 构造函数 备注： 默认是当作成功
     */
    public SimpleResponseModel() {
        this(SimpleResponseModel.SUCC_FLAG, SimpleResponseModel.EMPTY);
    }

    /**
     * 构造函数
     *
     * @param flag 执行标识
     */
    public SimpleResponseModel(String flag) {
        this(flag, SimpleResponseModel.EMPTY);
    }

    /**
     * 构造函数
     *
     * @param flag 标识位：Y 表示成功; N及其他，标识失败
     * @param msg  提示消息
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
