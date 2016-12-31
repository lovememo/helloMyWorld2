package com.opm.data.dtl.service;

import com.opm.data.dtl.dao.impl.DtlDataDao;
import com.opm.data.dtl.model.DtlControl;
import com.opm.data.dtl.model.DtlFile;

import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
public interface IDataProcessService {
    /**
     * �����ϴ����ļ����������ļ���
     * @param file
     * @param fileCtrlInfo
     * @return
     */
    //public DtlFile processUploadedFile(MultipartFile file, String fileName, DtlControl fileCtrlInfo) throws Exception;

    /**
     * �����ļ�
     * @param serialNo
     * @return �ļ��ֽ���
     */
    public DtlFile readFile(String serialNo);
    /**
     *��ҳ��ѯ�ϴ����ļ��б�
     * @param serialNo �ļ����к�
     * @param beginPos ��ʼλ��
     * @param fetchNum ��ȡ��
     * @return
     */
    public List<Object> turnPageFileUploadedList(String serialNo, String beginPos, String fetchNum);

    /**
     * ��ȡ�ļ����������������
     * @param serialNo
     * @return
     */
    public int getDataProcessedCount(String serialNo);

    /**
     * ��ȡ���ݴ��������Ϣ
     * @param dtlItemId
     * @return
     */
    public DtlControl getCtrlInfo(String dtlItemId) throws Exception;
    public DtlDataDao getDtlDataDao();
}
