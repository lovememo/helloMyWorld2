package com.opm.acct.mdt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtAcctdetInfModel;

@Mapper
public interface MdtAcctdetInfMapper {
	/**
	 * 受托账户明细查询
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
	 * 导入前最后一条明细信息查询
	 * @param actId
	 * @return
	 */
	public List<Map<String,String>> getMdtAcctLastDet (@Param("actId") String actId);
	
	
	/**
	 * 获取有效的受托户资金账务信息数
	 * @param actId
	 * @param validFlag
	 * @return
	 */
	public int getMdtAcctDetInfCount (@Param("actId") String actId, @Param("validFlag")  String validFlag);
	
	/**
	 * 获取受托户导入计息起始日期
	 * @param actId
	 * @return
	 */
	public String getBeginDateForImpDraw (@Param("actId") String actId);
	
	/**
	 * 插入受托户资金账务信息
	 * @param appNo
	 * @return
	 */
	public String insertMdtAcctdetInf (@Param("trdNo") String trdNo);
	
    /**
     * 获取导入前最后一条明细的交易日期
     * @param actId
     * @return
     */
	public String getOpDateBefImpLastDet (@Param("actId") String actId);
	
	/**
	 * 根据流水号获取受托户资金账务信息
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetInfModel getMdtAcctdetInfBySeqId (@Param("seqId") String seqId);
	
	/**
	 * 更新受托户资金账务信息
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public String updMdtAcctdetInf (@Param("trdNo") String trdNo);
	
	/**
	 * 判断是否计提利息,输入日期至该日期所在月份最后一天范围内
	 * @param actId
	 * @param date
	 * @return
	 */
	public int isCalDrawBtwDateAndLastest (@Param("actId") String actId, @Param("endDate") String endDate);
	
	/**
	 * 获取计息起始日期
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getBeginDateCalDraw (@Param("actId") String actId, @Param("endDate") String endDate,@Param("actDate") String actDate);
	/**
	 * 获取受托户最后一条明细余额
	 * @param actId
	 * @param date
	 * @return
	 */
	public String getActLastAmtBefDate (@Param("actId") String actId, @Param("date") String date);
	/**
	 * 更新或插入资产净值
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
	 * 获取受托户两个日期之间的资金账务信息
	 * @param actId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<MdtAcctdetInfModel> getInfBetween2Date (@Param("actId") String actId, 
			@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
