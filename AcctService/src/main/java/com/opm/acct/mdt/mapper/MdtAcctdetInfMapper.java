package com.opm.acct.mdt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtAcctdetInfModel;

@Mapper
public interface MdtAcctdetInfMapper {
	/**
	 * �����˻���ϸ��ѯ
	 * @param actId
	 * @param opDate
	 * @param amt
	 * @param oppAcctName
	 * @param direct
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	List<MdtAcctdetInfModel> qryMdtAcctdetInfList(@Param("actId") String actId, @Param("opDate") String opDate,
			@Param("amt") String amt, @Param("oppAcctName") String oppAcctName, @Param("direct") String direct,
			@Param("beginNum") int beginNum, @Param("fetchNum") int fetchNum);
	
	/**
	 * ����ǰ���һ����ϸ��Ϣ��ѯ
	 * @param actId
	 * @return
	 */
	public List<Map<String,String>> getMdtAcctLastDet (@Param("actId") String actId);
	
	
	/**
	 * ��ȡ��Ч�����л��ʽ�������Ϣ��
	 * @param actId
	 * @param validFlag
	 * @return
	 */
	public int getMdtAcctDetInfCount (@Param("actId") String actId, @Param("validFlag")  String validFlag);
	
	/**
	 * ��ȡ���л������Ϣ��ʼ����
	 * @param actId
	 * @return
	 */
	public String getBeginDateForImpDraw (@Param("actId") String actId);
	
	/**
	 * �������л��ʽ�������Ϣ
	 * @param appNo
	 * @return
	 */
	public String insertMdtAcctdetInf (@Param("trdNo") String trdNo);
	
    /**
     * ��ȡ����ǰ���һ����ϸ�Ľ�������
     * @param actId
     * @return
     */
	public String getOpDateBefImpLastDet (@Param("actId") String actId);
	
	/**
	 * ������ˮ�Ż�ȡ���л��ʽ�������Ϣ
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetInfModel getMdtAcctdetInfBySeqId (@Param("seqId") String seqId);
	
	/**
	 * �������л��ʽ�������Ϣ
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public String updMdtAcctdetInf (@Param("trdNo") String trdNo);
	
	/**
	 * �ж��Ƿ������Ϣ,���������������������·����һ�췶Χ��
	 * @param actId
	 * @param date
	 * @return
	 */
	public int isCalDrawBtwDateAndLastest (@Param("actId") String actId, @Param("endDate") String endDate);
	
	/**
	 * ��ȡ��Ϣ��ʼ����
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getBeginDateCalDraw (@Param("actId") String actId, @Param("endDate") String endDate,@Param("actDate") String actDate);
	/**
	 * ��ȡ���л����һ����ϸ���
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getActLastAmtBefDate (@Param("actId") String actId, @Param("date") String date);
	/**
	 * ���»�����ʲ���ֵ
	 * @param actId
	 * @param date
	 * @param isFinished
	 * @param trdNo
	 * @param amt
	 * @return
	 */
	public String updOrInsertAmt (@Param("actId") String actId, @Param("date") String date,
			@Param("isFinished") String isFinished,
			@Param("trdNo") String trdNo, @Param("amt") String amt);
	
	/**
	 * ��ȡ���л���������֮����ʽ�������Ϣ
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<MdtAcctdetInfModel> getInfBetween2Date (@Param("actId") String actId, 
			@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
