package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDetModel;


@Mapper
public interface MdtDetMapper {
	/**
	 * �������л������ϸ�˱�
	 * @return
	 */
	public int insertMdtDet (@Param("trdNo") String trdNo);
	
	/**
	 * ��ȡ���ڽ��׽��
	 * @param actId
	 * @param lastDate
	 * @param date
	 * @param mdtSubjectType
	 * @return
	 */
	public String  getPreAmt (@Param("actId") String actId,@Param("lastDate") String lastDate,
			@Param("date") String date,@Param("mdtSubjectType") String mdtSubjectType);
	
	/**
	 * �������ڻ�ȡ���
	 * @param actId
	 * @param date
	 * @return
	 */
	public String  getAmtByDate (@Param("actId") String actId, @Param("date") String date);
	
	/**
	 * ���л��������� ���л������������ϸ��ѯ
	 * @param actId
	 * @param date
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	public List<MdtDetModel> qryMdtDetList (@Param("actId") String actId,@Param("date") String date,
			@Param("amt") String amt,@Param("beginNum") String beginNum,@Param("fetchNum") String fetchNum);
	
	/**
	 * �����ս��ж�
	 * @param trdNo
	 * @return
	 */
	public int  getCountForReverseAct (@Param("trdNo") String trdNo);
	/**
	 * ���˻�ȡ����������
	 * @param trdNo
	 * @return
	 */
	public String  getMaxOpDateForReverseAct (@Param("trdNo") String trdNo);
	
	/**
	 * ���л��������븴��  ���������ѯ
	 * @param actId
	 * @return
	 */
	public List<MdtDetModel> qryReverseActApp (@Param("trdNo") String trdNo);
}
