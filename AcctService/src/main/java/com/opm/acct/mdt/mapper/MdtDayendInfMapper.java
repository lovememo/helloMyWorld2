package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDayendInfModel;

@Mapper
public interface MdtDayendInfMapper {
	/**
	 * ��ȡ���л�������ս����� 
	 * @param actId
	 * @return
	 */
	public String getDayendDateFinished (@Param("actId") String actId);
	
	/**
	 * �ж��Ƿ����ս�
	 * @param actId
	 * @param dayendDate
	 * @return
	 */
	public int isFinishDayend (@Param("actId") String actId, @Param("dayendDate") String dayendDate);
	
	/**
	 * �ж����������Ƿ����ս�
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public int isFinishDayendInterval (@Param("actId") String actId, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
	
	/**
	 * �жϴ���������Ƿ�������½�
	 * @param actId
	 * @param date
	 * @return
	 */
	public int isFinishMonend (@Param("actId") String actId, @Param("date") String date);
	
	/**
	 * ��ȡ���л���ϸ���
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getAmtByDate (@Param("actId") String actId, @Param("date") String date);
	
	/**
	 * ��ȡ���һ���ѽ�������
	 * @param actId
	 * @return
	 */
	public String getLastDayendDate (@Param("actId") String actId);
	
	/**
	 * ��ȡ�ս���Ϣ
	 * @param actId
	 * @param date
	 * @return
	 */
	public MdtDayendInfModel getMdtDayendInf (@Param("actId") String actId, @Param("date") String date);
	/**
	 * ȡ���ս���������Ϣ
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public String updForDayendCancel (@Param("actId") String actId, @Param("date") String date, @Param("trdNo") String trdNo);
	
}
