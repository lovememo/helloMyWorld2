package com.opm.data.dtl.model;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
public class DtlData {
    public String serialNo;
    public int    seq;
    public String checkFlag = "Y";
    public String msg = "";
    public String status = "Y";
    public String[] cols = new String[50];

    public DtlData() {
    }

    private void initField(String serialNo, int seq, String[] cols) {
        this.serialNo = serialNo;
        this.seq = seq;
        this.cols = cols;
    }
    public DtlData(String serialNo, int seq, String[] cols) {
        initField(serialNo, seq, cols);
    }

    public DtlData(String serialNo, int rowNum, List<?> rowDataList) {
        String[] rowData = new String[rowDataList.size()];
        rowDataList.toArray(rowData);
        initField(serialNo, rowNum, rowData);
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String value) {
        this.serialNo = value;
    }

    /**
     * 序号
     */
    public int getSeq() {
        return seq;
    }

    public void setSeq(int value) {
        this.seq = value;
    }

    /**
     * 是否校验通过 Y-通过 N-不通过
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String value) {
        this.checkFlag = value;
    }

    /**
     * 报错信息
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * 是否有效 Y-有效 N-无效
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols.clone();
    }

    public void reset() {
        serialNo = null;
        seq = 0;
        checkFlag = null;
        msg = null;
        status = null;
        cols = null;
    }

    public void assignFrom(DtlData dtlData) {
        if (dtlData == null) {
            reset();
            return;
        }
        serialNo = dtlData.serialNo;
        seq = dtlData.seq;
        checkFlag = dtlData.checkFlag;
        msg = dtlData.msg;
        status = dtlData.status;
        cols = dtlData.cols.clone();
    }

    public static void main(String[] args) {
        DtlData di = new DtlData();
        String[] arr = new String[50];
        arr[0] = "helloWorld";
        di.setCols(arr);
        DtlData diclone = new DtlData();
        diclone.assignFrom(di);
        diclone.getCols()[0] = "byeWorld";
        System.out.println(di.getCols()[0]);
        System.out.println(diclone.getCols()[0]);
    }

}
