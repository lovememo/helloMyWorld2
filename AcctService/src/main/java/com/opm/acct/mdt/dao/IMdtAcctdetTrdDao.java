package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtAcctdetTrdModel;

public interface IMdtAcctdetTrdDao {
	/**
	 * 受托户明细导入交易校验
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public String checkMdtDetImpTrade (String trdNo, String actId);
	
	/**
	 * 根据trdNo查询受托户明细信息
	 * @param trdNo
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdListByTrdNo(String trdNo);
	
	/**
	 * 获取受托户计息区间中的结束日期
	 * @param trdNo
	 * @return
	 */
	public String getMdtDailyIntDrawEndDate (String trdNo);
	
	/**
	 * 获取本次导入最小操作日期
	 * @param appNo
	 * @return
	 */
	public String getMinOpDate (String trdNo);
	
	/**
	 * 插入单条交易信息
	 * @param mdtAcctdetTrdModel
	 * @return
	 */
	public int insertMdtAcctdetTrd (MdtAcctdetTrdModel mdtAcctdetTrdModel);
	
	/**
	 *  受托户修改 更新部分字段
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int updMdtAcctdetTrdForMod (String trdNo,  String seqId);
	
	/**
	 * 根据流水号查询交易信息
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetTrdModel getMdtAcctdetTrdBySeqId (String seqId);
	
	/**
	 * 插入受托户交易表
	 * @param list
	 * @return
	 */
	public int insertMdtAcctdetTrdList (String serialNo, String actId,String trdNo);
	
	/**
	 * 获取校验不成功记录数
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int getCountsByCheckFlag (String trdNo, String checkFlag);
}
