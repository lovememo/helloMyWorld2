package com.opm.core.investManager.service;

import java.util.List;
import java.util.Map;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;
import com.opm.core.investManager.entity.NetValueTrdEntity;
import com.opm.core.investManager.model.NetValueAppFormModel;
import com.opm.core.investManager.model.NetValueAppFormModel2;
import com.opm.core.investManager.model.NetValueInfQueryFormModel;
import com.opm.core.plan.model.PlanBasicInfoModel;

/**
 * 净值管理Service（其中一个方法相当于一个存储过程）
 * @author kfzx-chenym
 *
 */
public interface INetValueService {

	/**
	 * 保存净值申请明细
	 * @param netValueAppModel
	 */
	public void saveApp(NetValueAppEntity netValueAppModel) ;


	/**
	 * 通过交易号查询交易明细
	 * @param trdNo
	 * @return
	 */
	public List<NetValueTrdEntity> qryTrdList(String trdNo);

	/**
	 * 查询保存的净值录入申请
	 * @param appModel
	 * @return
	 */
	public List qrySavedApp(AppModel appModel);

	/**
	 * 查询计划和投资组合信息
	 * @param planId
	 * @return
	 */
	public NetValueAppFormModel qryPlanInvest(String planId);

	/**
	 * 保存净值交易明细
	 * @param netValueTrdModel
	 */
	public void saveTrd(NetValueTrdEntity netValueTrdModel);

	/**
	 * 保存净值信息
	 * @param tNetValueInfMode
	 */
	public void saveInf(NetValueInfEntity tNetValueInfMode);

	/**
	 * 标记删除 
	 * @param netValueAppModel
	 */
	public void delApp(NetValueAppEntity netValueAppModel);

	/**
	 * 查询保存申请
	 * @param appNo
	 * @return
	 */
	public NetValueAppFormModel qryAppDet(String appNo);

	/**
	 * 初始化净值查询表单数据
	 * @param planId
	 * @return 
	 */
	public NetValueInfQueryFormModel initQryForm(String planId);


	/**
	 * 查询净值
	 * @param netValueInfEntity
	 * @return
	 */
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity);


	/**
	 * 净值申请主表
	 * @param tNetValueMAppEntity
	 */
	public void saveMApp(NetValueMAppEntity tNetValueMAppEntity);


	/**
	 * 查询Opm申请主表
	 * @param appQryParam
	 * @return
	 */
	public List qryOpmApp(AppQryParam appQryParam);


	/**
	 * 查询定价日是否使用
	 * @param planId
	 * @param evaluateDate
	 * @return
	 */
	public Boolean itfEveluateDateInUse(String planId, String evaluateDate);

}
