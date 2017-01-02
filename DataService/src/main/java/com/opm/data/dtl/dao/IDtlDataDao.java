package com.opm.data.dtl.dao;

import com.opm.data.dtl.model.*;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
public interface IDtlDataDao {
    /**
     * ��������
     *
     * @param dataInfoList
     * @return
     */
    public boolean insertDataInfo(List<String[]> dataInfoList);

    /**
     * �����ļ�
     * @param dtlFile
     */
    public void saveFile(DtlFile dtlFile);

    /**
     * ��ȡ�ļ���Ϣ
     * @param dtlControl
     */
    public DtlFile readFile(DtlControl dtlControl);

    /**
     * �ļ�����Ǽ�
     * @param dtlControl
     */
    public void registerDtlInfo(DtlControl dtlControl) throws Exception;

    /**
     * �����ļ�����״̬
     * @param dtlControl
     * @return
     */
    public boolean updateDtlInfo(DtlControl dtlControl);

    /**
     * ���½������ݵ�״̬
     * @param dtlData
     * @return
     */
    public int updateDtlDataInfo(DtlData dtlData);


    /**
     * �������������
     * @param resultList
     * @return
     */
    public boolean insertOpmDtlDataInf(List<DtlData> resultList);

    /**
     *��ҳ��ѯ�ϴ����ļ��б�
     * @param serialNo �ļ����к�
     * @param beginPos ��ʼλ��
     * @param fetchNum ��ȡ��
     * @return
     */
    public List<DtlFile> queryFileUploadedList(String serialNo, String beginPos, String fetchNum);

    /**
     * ��ҳ��ѯ�ϴ����ļ��б�����
     * @param serialNo
     * @return
     */
    public List queryFileUploadedListTotalNum(String serialNo);

    /**
     * ��ѯ����������ݼ�¼������
     * @param serialNo
     * @return
     */
    public List<Integer> queryProcessedDataCount(String serialNo);

    /**
     * ��ѯ������ϴ��Ŀ
     * @param dtlControl
     * @return
     */
    public List<DtlItem> queryDtlItem(DtlControl dtlControl);

    /**
     * ��ѯ���򼯺�
     * @param ruleId
     * @return
     */
    public List<DtlRule> queryDtlRule(String ruleId);
}
