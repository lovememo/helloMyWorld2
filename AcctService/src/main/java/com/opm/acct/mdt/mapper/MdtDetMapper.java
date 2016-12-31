package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDetModel;


@Mapper
public interface MdtDetMapper {
	/**
	 * 插入受托户会计明细账表
	 * @return
	 */
	public int insertMdtDet (@Param("trdNo") String trdNo);
	
	/**
	 * 获取当期交易金额
	 * @param actId
	 * @param lastDate
	 * @param date
	 * @param mdtSubjectType
	 * @return
	 */
	public String  getPreAmt (@Param("actId") String actId,@Param("lastDate") String lastDate,
			@Param("date") String date,@Param("mdtSubjectType") String mdtSubjectType);
	
	/**
	 * 根据日期获取余额
	 * @param actId
	 * @param date
	 * @return
	 */
	public String  getAmtByDate (@Param("actId") String actId, @Param("date") String date);
	
	/**
	 * 受托户冲账申请 受托户会计账务处理明细查询
	 * @param actId
	 * @param date
	 * @param beginNum
	 * @param fetchNum
	 * @return
	 */
	public List<MdtDetModel> qryMdtDetList (@Param("actId") String actId,@Param("date") String date,
			@Param("amt") String amt,@Param("beginNum") String beginNum,@Param("fetchNum") String fetchNum);
	
	/**
	 * 冲账日结判断
	 * @param trdNo
	 * @return
	 */
	public int  getCountForReverseAct (@Param("trdNo") String trdNo);
	/**
	 * 冲账获取最大操作日期
	 * @param trdNo
	 * @return
	 */
	public String  getMaxOpDateForReverseAct (@Param("trdNo") String trdNo);
	
	/**
	 * 受托户冲账申请复核  冲账申请查询
	 * @param actId
	 * @return
	 */
	public List<MdtDetModel> qryReverseActApp (@Param("trdNo") String trdNo);
}
