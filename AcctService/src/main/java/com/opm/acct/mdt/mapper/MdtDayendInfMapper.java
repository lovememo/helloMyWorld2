package com.opm.acct.mdt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDayendInfModel;

@Mapper
public interface MdtDayendInfMapper {
	/**
	 * 获取受托户已完成日结日期 
	 * @param actId
	 * @return
	 */
	public String getDayendDateFinished (@Param("actId") String actId);
	
	/**
	 * 判断是否已日结
	 * @param actId
	 * @param dayendDate
	 * @return
	 */
	public int isFinishDayend (@Param("actId") String actId, @Param("dayendDate") String dayendDate);
	
	/**
	 * 判断日期区间是否已日结
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public int isFinishDayendInterval (@Param("actId") String actId, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
	
	/**
	 * 判断传入的日期是否已完成月结
	 * @param actId
	 * @param date
	 * @return
	 */
	public int isFinishMonend (@Param("actId") String actId, @Param("date") String date);
	
	/**
	 * 获取受托户明细余额
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getAmtByDate (@Param("actId") String actId, @Param("date") String date);
	
	/**
	 * 获取最后一个已结账日期
	 * @param actId
	 * @return
	 */
	public String getLastDayendDate (@Param("actId") String actId);
	
	/**
	 * 获取日结信息
	 * @param actId
	 * @param date
	 * @return
	 */
	public MdtDayendInfModel getMdtDayendInf (@Param("actId") String actId, @Param("date") String date);
	/**
	 * 取消日结更新相关信息
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public String updForDayendCancel (@Param("actId") String actId, @Param("date") String date, @Param("trdNo") String trdNo);
	
}
