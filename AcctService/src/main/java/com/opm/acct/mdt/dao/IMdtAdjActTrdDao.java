package com.opm.acct.mdt.dao;

import java.util.List;

import com.opm.acct.mdt.model.MdtAdjActTrdModel;

public interface IMdtAdjActTrdDao {
	/**
	 * 插入受托户调账交易表
	 * @param list
	 * @return
	 */
	public int insertMdtAdjActTrd (List<MdtAdjActTrdModel> list);
	
	/**
	 * 查询最大操作日期
	 * @param trdNo
	 * @return
	 */
	public String getMaxOpDate (String trdNo);
	
	/**
	 * 获取指定字段的最大值
	 * @param trdNo
	 * @param fieldName
	 * @return
	 */
	public String getMaxValueByFieldName (String trdNo,String fieldName);
	
	/**
	 * 查询交易申请信息
	 * @param trdNo
	 * @return
	 */
	public List<MdtAdjActTrdModel> getMdtAdjActTrd (String trdNo);
	
//	/**
//	 * 查询借金额或者贷金额
//	 * @param trdNo
//	 * @param debitCreditFlag
//	 * @return
//	 */
//	public String getDebitOrCreditAmt (String trdNo,String debitCreditFlag);
//	
//	/**
//	 * 查询所有者权益
//	 * @param trdNo
//	 * @return
//	 */
//	public String getCreditAmt (String trdNo);
//	
//	/**
//	 * 查询调账交易信息  不是资产 ,负债类型
//	 * @param trdNo
//	 * @return
//	 */
//	public List<MdtAdjActTrdModel> getMdtAdjActTrdForCheck (String trdNo);
}
