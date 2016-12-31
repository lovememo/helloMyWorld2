package com.opm.acct.mdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtDetTrdModel;

@Mapper
public interface MdtDetTrdMapper {
	/**
	 * 受托户明细导入-插入受托户会计记账交易表
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public int insertMdtDetTrdForImp (@Param("list") List<MdtDetTrdModel> list, @Param("trdNo") String trdNo);
	
	/**
	 * 受托户导入插入利息信息
	 * @param trdNo
	 * @param opDate
	 * @param actId
	 * @return
	 */
	public int insertDrawForImp (@Param("trdNo") String trdNo, @Param("opDate") String opDate, @Param("actId") String actId);
	
	/**
	 * 借贷明细总额校验
	 * @return
	 */
	public String debitCreditAmtCheck(@Param("trdNo") String trdNo);
	
	/**
	 * 会记记账恒等式校验
	 * @param mdtSubjectClass
	 * @return
	 */
	public String debitTradeAmtCheck(@Param("trdNo") String trdNo);
	
	/**
	 * 受托户明细修改-插入受托户会计记账交易表
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public int insertMdtDetTrdForMod (@Param("list") List<MdtDetTrdModel> list, @Param("trdNo") String trdNo, @Param("actDate") String actDate);
	
	/**
	 * 日结插入利息信息
	 * @param trdNo
	 * @param opDate
	 * @param actId
	 * @param amt
	 * @return
	 */
	public int insertDrawForDayend (@Param("trdNo") String trdNo, @Param("opDate") String opDate, 
			@Param("actId") String actId, @Param("amt") String amt);
	/**
	 * 插入受托户会计记账交易表
	 * @param list
	 * @return
	 */
	public int insertMdtDetTrd (@Param("list") List<MdtDetTrdModel> list);
	/**
	 * 冲回第二日受托户受托管理费和托管费的账务处理
	 * @param actId
	 * @param date
	 * @param trdNo
	 * @return
	 */
	public int reverseMdtAndTrustFee (@Param("actId") String actId,  @Param("date") String date,@Param("trdNo") String trdNo);
	/**
	 * 冲回月末损益结转
	 * @param actId
	 * @param trdNo
	 * @param date
	 * @param beginDateDraw
	 * @return
	 */
	public int reverseMdtMonAcct (@Param("actId") String actId,@Param("trdNo") String trdNo, 
			@Param("date") String date,  @Param("beginDateDraw") String beginDateDraw);
	
	/**
	 * 冲账-插入受托户会计记账交易表
	 * @param trdNo
	 * @param actId
	 * @return
	 */
	public int insertMdtDetTrdForReverAct (@Param("trdNo") String trdNo, @Param("actId") String actId);
	
	/**
	 * 受托户记账-插入受托户会计记账交易表
	 * @param list
	 * @param trdNo
	 * @return
	 */
	public int insertMdtDetTrdForAcctRecord (@Param("list") List<MdtDetTrdModel> list, @Param("trdNo") String trdNo);
}
