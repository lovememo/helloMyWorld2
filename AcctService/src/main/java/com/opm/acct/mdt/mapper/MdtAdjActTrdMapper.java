package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtAdjActTrdModel;


@Mapper
public interface MdtAdjActTrdMapper {
	/**
	 * 插入受托户调账交易表
	 * @param list
	 * @return
	 */
	public int insertMdtAdjActTrd (@Param("list") List<MdtAdjActTrdModel> list);
	
	
	/**
	 * 查询最大操作日期
	 * @param trdNo
	 * @return
	 */
	public String getMaxOpDate (@Param("trdNo") String trdNo);
	
	/**
	 * 获取指定字段的最大值
	 * @param trdNo
	 * @param fieldName
	 * @return
	 */
	public String getMaxValueByFieldName (@Param("trdNo") String trdNo,@Param("fieldName") String fieldName);
	
	/**
	 * 查询交易申请信息
	 * @param trdNo
	 * @return
	 */
	public List<MdtAdjActTrdModel> getMdtAdjActTrd (@Param("trdNo") String trdNo);
//	/**
//	 * 查询借金额或者贷金额
//	 * @param trdNo
//	 * @param debitCreditFlag
//	 * @return
//	 */
//	public String getDebitOrCreditAmt (@Param("trdNo") String trdNo,@Param("debitCreditFlag") String debitCreditFlag);
//	
//	/**
//	 * 查询所有者权益
//	 * @param trdNo
//	 * @return
//	 */
//	public String getCreditAmt (@Param("trdNo") String trdNo);
//	
//	/**
//	 * 查询调账交易信息  不是资产 ,负债类型
//	 * @param trdNo
//	 * @return
//	 */
//	public List<MdtAdjActTrdModel> getMdtAdjActTrdForCheck (@Param("trdNo") String trdNo);
	
}
