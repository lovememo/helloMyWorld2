package com.opm.acct.mdt.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.acct.mdt.model.MdtAcctdetTrdModel;

@Mapper
public interface MdtAcctdetTrdMapper {
	
	/**
	 * 受托户明细导入申请批量文件校验,每条明细的出账金额和入账金额只有一项不为空
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck1 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,出账金额或入账金额不能为0
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck2 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,对方户名不能为-1
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck3 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,对方账号不能包含汉字
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck4 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,日期不能大于当前系统时间
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck5 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,确认状态必须为空
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck6 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,备注必须为空
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck7 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,交易类型只能为资产提取、结息或空
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck8 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,每日最后一条明细必须有余额
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck9 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,导入明细不能跨月
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck10 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入申请批量文件校验,本次导入的首条明细的交易日期和交易时间（若有）不能早于上次导入的最后一条明细的交易日期和交易时间（若有）
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck11 (@Param("trdNo") String trdNo,@Param("actId") String actId);
	/**
	 * 受托户明细导入申请批量文件校验,交易日期不能早于计划信息中的受托户核算起始日期
	 * @param trdNo
	 * @return
	 */
	public int mdtDetImpCheck12 (@Param("trdNo") String trdNo,@Param("actId") String actId);
	
	/**
	 * 根据trdNo,checkFlag查询受托户明细交易表记录
	 * @param trdNo
	 * @param checkFlagMdtAcctdetTmpModel
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdByTrdNoAndCheckFlag (@Param("trdNo") String trdNo,@Param("checkFlag") String checkFlag);
	
	/**
	 * 更新受托户明细交易表每条记录校验结果
	 * @param list
	 * @return
	 */
	public int updCheckResultPerRecord (@Param("list")  List<MdtAcctdetTrdModel> list);
	
	/**
	 * 根据trdNo条件更新受托户明细交易表校验结果
	 * @param trdNo
	 * @param checkMemo
	 * @return
	 */
	public int updCheckResultByTrdNo (@Param("trdNo") String trdNo, @Param("checkMemo") String checkMemo);
	
	/**
	 * 受托户明细导入后 最后一条明细的交易日期
	 * @param trdNo
	 * @return
	 */
	public String getMaxOpDateByTrdNo (@Param("trdNo") String trdNo);
	
	/**
	 * 获取受托户计息区间中的结束日期
	 * @param trdNo
	 * @return
	 */
	public String getMdtDailyIntDrawEndDate (@Param("trdNo") String trdNo);
	
	/**
	 * 获取本次导入最小操作日期
	 * @param trdNo
	 * @return
	 */
	public String getMinOpDate (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入金额校验中更新金额
	 * @param trdNo
	 * @return
	 */
	public int updateAmtImpCheck (@Param("trdNo") String trdNo, @Param("actId") String actId);
	
	/**
	 * 受托户明细导入前金额校验,导入余额不正确
	 * @param trdNo
	 * @return
	 */
	public int checkAmtBefImp1 (@Param("trdNo") String trdNo);
	/**
	 * 受托户明细导入前金额校验,余额不能为负
	 * @param trdNo
	 * @return
	 */
	public int checkAmtBefImp2 (@Param("trdNo") String trdNo);
	
	/**
	 * 根据trdNo查询受托户导入明细信息
	 * @param trdNo
	 * @return
	 */
	public List<MdtAcctdetTrdModel> qryMdtAcctdetTrdListByTrdNo (@Param("trdNo") String trdNo);
	
	
	/**
	 * 插入交易信息
	 * @param mdtAcctdetTrdModel
	 * @return
	 */
	public int insertMdtAcctdetTrd (@Param("mdtAcctdetTrdModel") MdtAcctdetTrdModel mdtAcctdetTrdModel);
	
	/**
	 * 受托户修改 更新部分字段
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int updMdtAcctdetTrdForMod (@Param("trdNo") String trdNo, @Param("seqId") String seqId);
	
	/**
	 * 根据流水号查询交易信息
	 * @param seqId
	 * @return
	 */
	public MdtAcctdetTrdModel getMdtAcctdetTrdBySeqId (@Param("seqId") String seqId);
	
	/**
	 * 获取交易流水号
	 * @return
	 */
	public String getTrdNo();
	
	/**
	 * 插入受托户交易表
	 * @param list
	 * @return
	 */
	public int insertMdtAcctdetTrdList (@Param("serialNo") String serialNo, @Param("actId") String actId,
			@Param("trdNo") String trdNo);
	
	/**
	 * 获取校验不成功记录数
	 * @param trdNo
	 * @param seqId
	 * @return
	 */
	public int getCountsByCheckFlag (@Param("trdNo") String trdNo, @Param("checkFlag") String checkFlag);
}
