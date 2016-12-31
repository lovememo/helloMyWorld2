package com.opm.data.dtl.dao.impl;

import com.opm.common.enumdict.DtlStatus;
import com.opm.data.dtl.dao.IDtlDataDao;
import com.opm.data.dtl.mapper.DtlMapper;
import com.opm.data.dtl.model.DtlControl;
import com.opm.data.dtl.model.DtlData;
import com.opm.data.dtl.model.DtlFile;
import com.opm.data.dtl.model.DtlItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by kfzx-jinjf on 2016/12/14.
 */
@Repository("DtlDataDao")
@Transactional
public class DtlDataDao implements IDtlDataDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DtlMapper dtlMapper;

    @Override
    public boolean insertDataInfo(List<String[]> dataInfoList) {
        return false;
    }

    /**
     * �����ļ�
     *
     * @param dtlFile
     */
    public void saveFile(DtlFile dtlFile) {
        this.dtlMapper.saveFile(dtlFile);
    }

    /**
     * ��ȡ�ļ���Ϣ
     *
     * @param dtlControl
     */
    public DtlFile readFile(DtlControl dtlControl) {
        List<DtlFile> dtlFileList = this.dtlMapper.readFile(dtlControl);
        if (dtlFileList.size() > 0) {
            return dtlFileList.get(0);
        }
        return new DtlFile();
    }

    /**
     * �ļ�����Ǽ�
     *
     * @param dtlControl
     */
    @Override
    public void registerDtlInfo(DtlControl dtlControl) throws Exception {
        dtlControl.setDtlStatus(DtlStatus.PROCESSING.getKey());//������
        dtlControl.setInvokeTime(new Date());
        int count = this.dtlMapper.registerDtlInfo(dtlControl);
        if (1 != count) {
            throw new Exception("�ļ�����ʧ�ܣ��Ǽ���Ϣʧ�ܣ�");
        }
    }

    /**
     * �����ļ�����״̬
     *
     * @param dtlControl
     * @return
     */
    @Override
    public boolean updateDtlInfo(DtlControl dtlControl) {
        int count = this.dtlMapper.updateDtlInfo(dtlControl);
        return count > 0;
    }

    /**
     * ���½������ݵ�״̬
     * @param dtlData
     * @return
     */
    @Override
    public int updateDtlDataInfo(DtlData dtlData) {
        return this.dtlMapper.updateDtlDataInfo(dtlData);
    }

    /**
     * ����������
     *
     * @param resultList
     * @return
     */
    @Override
    public boolean insertOpmDtlDataInf(List<DtlData> resultList) {
        String sql = "insert into OPM_DTL_DATA_INF" +
                "(SERIAL_NO,SEQ,CHECK_FLAG,MSG,STATUS,COL0,COL1,COL2,COL3,COL4,COL5,COL6,COL7,COL8,COL9,COL10,COL11,COL12,COL13,COL14,COL15,COL16,COL17,COL18,COL19,COL20,COL21,COL22,COL23,COL24,COL25,COL26,COL27,COL28,COL29,COL30,COL31,COL32,COL33,COL34,COL35,COL36,COL37,COL38,COL39,COL40,COL41,COL42,COL43,COL44,COL45,COL46,COL47,COL48,COL49)values" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                DtlData dtlData = resultList.get(i);
                ps.setString(1, dtlData.getSerialNo());
                ps.setInt   (2, dtlData.getSeq());
                ps.setString(3, dtlData.getCheckFlag());
                ps.setString(4, dtlData.getMsg());
                ps.setString(5, dtlData.getStatus());
                String[] data = dtlData.getCols();
                for (int j = 0; j < data.length && j < 50; j++) {
                    ps.setString(6 + j, data[j]);
                }
                for (int j = data.length; j < 50; j++) {
                    ps.setString(6 + j, "");
                }
            }

            @Override
            public int getBatchSize() {
                return resultList.size();
            }
        });
        return true;
    }

    /**
     *��ҳ��ѯ�ϴ����ļ��б�
     * @param serialNo �ļ����к�
     * @param beginPos ��ʼλ��
     * @param fetchNum ��ȡ��
     * @return
     */
    @Deprecated
    public List<DtlFile> queryFileUploadedList(String serialNo, String beginPos, String fetchNum) {
        return this.dtlMapper.queryFileUploadedList(serialNo, beginPos, fetchNum);
    }

    /**
     * ��ҳ��ѯ�ϴ����ļ��б�����
     * @param serialNo
     * @return
     */
    @Deprecated
    public List queryFileUploadedListTotalNum(String serialNo) {
        return this.dtlMapper.queryFileUploadedListTotalNum(serialNo);
    }


    /**
     * ��ѯ����������ݼ�¼������
     * @param serialNo
     * @return
     */
    public List<Integer> queryProcessedDataCount(String serialNo) {
        return this.dtlMapper.queryProcessedDataCount(serialNo);
    }

    /**
     * ��ѯ������ϴ��Ŀ
     * @param dtlControl
     * @return
     */
    @Override
    public List<DtlItem> queryDtlItem(DtlControl dtlControl) {
        return this.dtlMapper.queryDtlItem(dtlControl);
    }
}
