package com.opm.core.plan.service;

import java.util.List;
import java.util.Map;

import com.opm.core.plan.model.PlanAppInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.plan.model.PlanInfoModle;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
public interface IPlanService {
	public PlanInfoModle getPlanBasicInfo(String planId);

	/**
	 * 获取待复核的申请列表
	 * 
	 * @param branchId
	 *            机构编码
	 * @param userId
	 *            用户编码
	 * @param roleId
	 *            角色编码
	 * @return 申请列表
	 */
	public List<Map<String, String>> getPlanApplys(String branchId, String userId, String roleId);

	/**
	 * 获取申请明细信息
	 * 
	 * @param appNo
	 *            申请号
	 * @return 申请明细信息
	 */
	public PlanAppInfModel getPlanAppDet(long appNo);

	/**
	 * 接口 传入当前计划编码，返回关联计划列表，含计划编码，是否主受托人。
	 * 
	 * @param planId
	 *            计划编码
	 * @return 计划信息
	 */
	public PlanBasicInfoModel getPlanInfByPlanId(String planId);

	/**
	 * app 保存计划基本信息
	 */
	public Integer insertPlanBasicApp(PlanBasicInfoModel model);

	/**
	 * app 查询计划基本信息
	 */
	public PlanBasicInfoModel selectPlanBasicApp(String appNo);

	/**
	 * app 更新计划基本信息
	 */
	public Integer updatePlanBasicApp(PlanBasicInfoModel model);

	/**
	 * app 获取保存的计划基本信息
	 */
	public List<Map<String, String>> selectSavedPlanApp();

	/**
	 * 交易 保存计划基本信息
	 */
	public Integer insertPlanBasicTrd(PlanBasicInfoModel model);

	/**
	 * 交易 查询计划基本信息
	 */
	public PlanBasicInfoModel selectPlanBasicTrd(String trdNo);

	/**
	 * 交易 更新计划基本信息
	 */
	public Integer updatePlanBasicTrd(PlanBasicInfoModel model);

	/**
	 * 交易 获取保存的计划基本信息
	 */
	public List<Map<String, String>> selectSavedPlanTrd();

	/**
	 * 生效 保存计划基本信息
	 */
	public Integer insertPlanBasicInf(PlanBasicInfoModel model);

	/**
	 * 
	 * 查询计划基本信息
	 */
	public PlanBasicInfoModel selectPlanBasicInf(String planId);

	/**
	 * 生效 更新计划基本信息
	 */
	public Integer updatePlanBasicInf(PlanBasicInfoModel model);
}
