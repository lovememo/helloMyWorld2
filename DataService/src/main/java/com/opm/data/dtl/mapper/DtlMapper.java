package com.opm.data.dtl.mapper;

import com.opm.data.dtl.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/15.
 */
public interface DtlMapper {
    /**
     * �����ļ�
     *
     * @param dtlFile
     * @return
     */
    public void saveFile(DtlFile dtlFile);

    /**
     * ��ȡ�ļ���Ϣ
     * @param dtlControl
     */
    public List<DtlFile> readFile(DtlControl dtlControl);

    /**
     * ���ݴ���Ǽ���Ϣ
     * @param dtlControl
     * @return serialNo
     */
    public int registerDtlInfo(DtlControl dtlControl);

    /**
     * �����ļ�����״̬
     * @param dtlControl
     * @return
     */
    public int updateDtlInfo(DtlControl dtlControl);

    /**
     * ���½������ݵ�״̬
     * @param dtlData
     * @return
     */
    public int updateDtlDataInfo(DtlData dtlData);

    /**
     *  ��ѯ�ϴ����ļ��б�
     *
     /**
     *��ҳ��ѯ�ϴ����ļ��б�
     * @param serialNo �ļ����к�
     * @param beginPos ��ʼλ��(����)
     * @param fetchNum ��ȡ��
     * @return
     */
    public List<DtlFile> queryFileUploadedList(@Param("serialNo") String serialNo, @Param("beginPos") String beginPos, @Param("fetchNum") String fetchNum);

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
    public List<DtlRule> queryDtlRule(@Param("ruleId") String ruleId);
}
