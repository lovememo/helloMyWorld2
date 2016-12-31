package com.opm.data.dtl.model;


/**
 * Created by kfzx-jinjf on 2016/12/9.
 */
public class DtlFile {
    private String serialNo;
    private String fileName = "";
    private int totalRowNum = 0;//������������������
    private transient byte[] fileContent = null;

    private int errRowNum = 0;//���󱨸�����

    public int getErrRowNum() {
        return errRowNum;
    }

    public void setErrRowNum(int errRowNum) {
        this.errRowNum = errRowNum;
    }
    private float fileSize = 0;//�ļ���С KB

//    private int seq = 0;//�ļ����

    public float getFileSize() {
        return fileSize;
    }

   /* public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }*/

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public DtlFile() {
        super();
    }

    public int getTotalRowNum() {
        return totalRowNum;
    }

    public void setTotalRowNum(int totalRowNum) {
        this.totalRowNum = totalRowNum;
    }

    public DtlFile(String serialNo, String fileName, byte[] fileContent) {
        this.serialNo = serialNo;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    /**
     * ��ȡ�ļ�����
     *
     * @return
     */
    public byte[] getFileContent() {
        return fileContent;
    }

    /**
     * �����ļ�����
     *
     * @param fileContent
     */
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * ��ȡ�ļ���
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * �����ļ���
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * ��ȡ���к�
     *
     * @return
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * �������к�
     *
     * @param serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void reset() {
        serialNo = null;
        fileContent = null;
        fileName = null;
        totalRowNum = 0;
    }

    public void assignFrom(DtlFile fi) {
        if (fi == null) {
            reset();
            return;
        }
        serialNo = fi.serialNo;
        fileContent = fi.fileContent;
        fileName = fi.fileName;
        totalRowNum = fi.totalRowNum;
    }
}
