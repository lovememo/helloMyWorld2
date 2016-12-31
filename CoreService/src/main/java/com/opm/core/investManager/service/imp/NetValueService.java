package com.opm.core.investManager.service.imp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.app.dao.IAppDao;
import com.opm.core.investManager.feign.IPlanClient;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.service.IAppService;
import com.opm.core.app.trade.AppTask;
import com.opm.core.investManager.dao.INetValueAppDao;
import com.opm.core.investManager.dao.INetValueInfDao;
import com.opm.core.investManager.dao.INetValueTrdDao;
import com.opm.core.investManager.entity.NetValueAppEntity;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.entity.NetValueMAppEntity;
import com.opm.core.investManager.entity.NetValueEntity;
import com.opm.core.investManager.entity.NetValueTrdEntity;
import com.opm.core.investManager.model.NetValueAppFormModel;
import com.opm.core.investManager.model.NetValueAppFormModel.Plan;
import com.opm.core.investManager.model.NetValueAppFormModel2;
import com.opm.core.investManager.model.NetValueInfQueryFormModel;
import com.opm.core.investManager.service.INetValueService;
import com.opm.core.plan.model.InvestInfModel;
import com.opm.core.plan.model.OtherInvestInfModel;
import com.opm.core.plan.model.OtherPlanInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;

import ch.qos.logback.classic.Logger;

@Service("NetValueService")
public class NetValueService implements INetValueService {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AppTask.class);
	
	private static String keyinAppType = "NETVALUEADD"; //净值录入申请类型
	private static String modAppType = "NETVALUEMOD"; //净值修改申请类型
	
	@Autowired
	private INetValueAppDao netValueAppDao;
	
	@Autowired
	private INetValueTrdDao netValueTrdDao;
	
	@Autowired
	private INetValueInfDao netValueInfDao;
	
	@Autowired
	private IAppDao appDao;
	
	@Autowired
	private IAppService appService;
	
	@Autowired
	private IPlanClient planClient;

	@Override
	public void saveApp(NetValueAppEntity netValueAppModel) {
		//TODO 备份申请明细
		//删除申请明细
		this.netValueAppDao.delApp(netValueAppModel);
		//新增申请明细
		this.netValueAppDao.addApp(netValueAppModel);
		
	}


	@Override
	public List qrySavedApp(AppModel appModel) {
		
		AppModel qryAppModel = new AppModel();
		qryAppModel.setPlanId(appModel.getPlanId());
		qryAppModel.setAppType(keyinAppType);
		qryAppModel.setAppState("2"); //保存状态
		return this.appDao.qryApp(qryAppModel);
		
	}
	
	@Override
	public List<NetValueTrdEntity> qryTrdList(String trdNo) {
		return this.netValueTrdDao.qryTrdByTrdNo(trdNo);
	}


	@Override
	public void saveTrd(NetValueTrdEntity netValueTrdModel) {
		//TODO 备份交易明细
		//删除交易明细
		this.netValueTrdDao.delTrd(netValueTrdModel);
		//新增交易明细
		this.netValueTrdDao.addTrd(netValueTrdModel);
		
	}

	@Override
	public void saveInf(NetValueInfEntity netValueInfMode) {
		//TODO 备份信息
		//信息是否存在
		NetValueInfEntity qryNetValueInfModel = new NetValueInfEntity();
		qryNetValueInfModel.setPlanId(netValueInfMode.getPlanId());
		qryNetValueInfModel.setEvaluateDate(netValueInfMode.getEvaluateDate());
		qryNetValueInfModel.setOpDate(netValueInfMode.getOpDate());
		NetValueInfEntity resNetValueInfModel = this.netValueInfDao.qryInfByPK(qryNetValueInfModel);
		if ( resNetValueInfModel == null ) { //信息不存在
			
			//新增信息
			this.netValueInfDao.addInf(netValueInfMode);
			
		} else { //信息存在

			NetValueInfEntity updNetValueInfModel = netValueInfMode;
			//根据 计划+组合+定价日 更新数据，可更新汇总日、每份金额、累计份额、资产净值、净值表接收时间、申请号
			this.netValueInfDao.updInf(updNetValueInfModel);
			
		}		
		
	}
	
	@Override
	public NetValueAppFormModel qryPlanInvest(String planId) {

		PlanBasicInfoModel planBasicInfoModel = this.planClient.getPlanInfByPlanId(planId);
		//将主计划也加入其他计划列表以便前台取数
		NetValueAppFormModel netValueAppFormModel = new NetValueAppFormModel();
		
		netValueAppFormModel.setCommonPlanId(planBasicInfoModel.getCommonPlanId());
		netValueAppFormModel.setEvaluateDate(""); //TODO 定价日
		netValueAppFormModel.setIcbcFlag(planBasicInfoModel.getIcbcFlag());
		netValueAppFormModel.setNextEvaluateData(""); //TODO 下一定价日
		netValueAppFormModel.setPlanEvalType(planBasicInfoModel.getPlanEvalType());
		netValueAppFormModel.setPlanId(planBasicInfoModel.getPlanId());
		netValueAppFormModel.setPlanName(planBasicInfoModel.getPlanName());
		List<Plan> planList = new LinkedList<Plan>();
		
		//将计划信息转为副计划
		Plan mPlan = netValueAppFormModel.new Plan();
		mPlan.setPlanId(planBasicInfoModel.getPlanId());
		mPlan.setPlanName(planBasicInfoModel.getPlanName());

		List<NetValueAppEntity> mInvestList = new LinkedList<>();
		for (InvestInfModel tInvestInfModel : planBasicInfoModel.getInvestInfModelList()) {
			NetValueAppEntity tNetValueAppEntity = new NetValueAppEntity();
			tNetValueAppEntity.setInvestId(tInvestInfModel.getInvestId());
			tNetValueAppEntity.setInvestName(tInvestInfModel.getInvestName());
			mInvestList.add(tNetValueAppEntity);
		}
		mPlan.setInvestList(mInvestList);
		planList.add(mPlan);
		
		//将主计划投资组合
		for (OtherPlanInfModel tOtherPlanInfModel : planBasicInfoModel.getOtherPlanInfModelList()) {
			Plan tPlan = netValueAppFormModel.new Plan();
			tPlan.setPlanId(tOtherPlanInfModel.getCommonPlanId());
			tPlan.setPlanName(tOtherPlanInfModel.getPlanName());
			
			List<NetValueAppEntity> tInvestList = new LinkedList<NetValueAppEntity>();
			for ( OtherInvestInfModel tOtherInvestInfModel : tOtherPlanInfModel.getOtherInvestInfModelList()) {
				NetValueAppEntity tNetValueAppEntity = new NetValueAppEntity();
				tNetValueAppEntity.setInvestId(tOtherInvestInfModel.getInvestId());
				tNetValueAppEntity.setInvestName(tOtherInvestInfModel.getInvestName());
				tInvestList.add(tNetValueAppEntity);
			}
			tPlan.setInvestList(tInvestList);
			planList.add(tPlan);
		}
		netValueAppFormModel.setPlanList(planList);
		return netValueAppFormModel;
	}


	@Override
	public void delApp(NetValueAppEntity netValueAppModel) {
		//标记删除申请主表
		AppModel appModel = new AppModel();
		appModel.setAppNo(netValueAppModel.getAppNo());
		appModel.setAppState("10"); //TODO 状态枚举？
		this.appService.modApp(appModel);
		
	}
	

	@Override
	public NetValueAppFormModel qryAppDet(String appNo) {
		//根据申请查询出已录入的计划下的投资组合
		AppModel resAppModel = this.appDao.qryAppByAppNo(appNo);
		
		//根据计划查询计划下的投组
		PlanBasicInfoModel planBasicInfoModel = this.planClient.getPlanInfByPlanId(resAppModel.getPlanId());
		
		//将申请录入结果和计划下的投组数据绑定
		NetValueAppFormModel netValueAppFormModel = new NetValueAppFormModel();
		netValueAppFormModel.setPlanId(planBasicInfoModel.getPlanId());
		netValueAppFormModel.setPlanName(planBasicInfoModel.getPlanName());
		netValueAppFormModel.setCommonPlanId(planBasicInfoModel.getCommonPlanId());
		netValueAppFormModel.setEvaluateDate(""); //TODO 定价日
		netValueAppFormModel.setNextEvaluateData(""); //TODO 下个定价日
		netValueAppFormModel.setPlanEvalType(planBasicInfoModel.getPlanEvalType());
		
		List<Plan> planList = new LinkedList<Plan>();
		
		//主计划投组
		String mPlanId = planBasicInfoModel.getPlanId();
		String mPlanName = planBasicInfoModel.getPlanName();
		List<NetValueAppEntity> mInvestList = new LinkedList<>();
		int keyInMInvestNum = 0;//计划下是否录入投组判断标记
		for ( InvestInfModel investInfModel : planBasicInfoModel.getInvestInfModelList()) {
			String tInvestId = investInfModel.getInvestId();
			String tInvestName = investInfModel.getInvestName();
			//查询计划下投组录入结果
			NetValueAppEntity tQryNetValueAppModel = new NetValueAppEntity();
			tQryNetValueAppModel.setAppNo(appNo);
			tQryNetValueAppModel.setPlanId(mPlanId);
			tQryNetValueAppModel.setInvestId(tInvestId);
			NetValueAppEntity tInvest = this.netValueAppDao.qryAppByUniq(tQryNetValueAppModel);
			if ( tInvest == null ) { //没录入
				tInvest = new NetValueAppEntity();
				tInvest.setInvestId(tInvestId);
				tInvest.setInvestName(tInvestName);
			} else { //已录入
				tInvest.setInvestName(tInvestName);//更新投组名称
				keyInMInvestNum++;
			}
			mInvestList.add(tInvest);
		}
		
		//统一计划层投资组合汇总列
		NetValueAppEntity qryNetValueAppModel = new NetValueAppEntity();
		qryNetValueAppModel.setAppNo(appNo);
		qryNetValueAppModel.setPlanId(netValueAppFormModel.getPlanId());
		qryNetValueAppModel.setInvestId("FFFFFF");//汇总investId
		NetValueAppEntity sumNetValueAppModel = this.netValueAppDao.qryAppByUniq(qryNetValueAppModel);
		if ( sumNetValueAppModel != null ) {
			netValueAppFormModel.setSumInvest(sumNetValueAppModel);
		}
		

		Plan mPlan = netValueAppFormModel.new Plan();
		mPlan.setPlanId(mPlanId);
		mPlan.setPlanName(mPlanName);
		mPlan.setState(keyInMInvestNum > 0 ? "已录入" : "");
		mPlan.setInvestList(mInvestList);
		planList.add(mPlan);
		
		//其他计划投组
		for (OtherPlanInfModel otherPlanInfModel: planBasicInfoModel.getOtherPlanInfModelList()) {

			String tPlanId = otherPlanInfModel.getCommonPlanId();
			String tPlanName = otherPlanInfModel.getPlanName();
			List<NetValueAppEntity> tInvestList = new LinkedList<NetValueAppEntity>();
			
			int keyInInvestNum = 0;//计划下是否录入投组判断标记
			for ( OtherInvestInfModel otherInvestInfModel : otherPlanInfModel.getOtherInvestInfModelList() ) {
				String tInvestId = otherInvestInfModel.getInvestId();
				String tInvest_name = otherInvestInfModel.getInvestName();
				//查询计划下投组录入结果
				NetValueAppEntity tQryNetValueAppModel = new NetValueAppEntity();
				tQryNetValueAppModel.setAppNo(appNo);
				tQryNetValueAppModel.setPlanId(tPlanId);
				tQryNetValueAppModel.setInvestId(tInvestId);
				NetValueAppEntity tInvest = this.netValueAppDao.qryAppByUniq(tQryNetValueAppModel);
				if ( tInvest == null ) { //没录入
					tInvest = new NetValueAppEntity();
					tInvest.setInvestId(tInvestId);
					tInvest.setInvestName(tInvest_name);
				} else { //已录入
					tInvest.setInvestName(tInvest_name);//更新投组名称
					keyInInvestNum++;
				}
				tInvestList.add(tInvest);
				
			}

			//单一计划层汇总列
			NetValueAppEntity tpQNetValueAppModel = new NetValueAppEntity();
			tpQNetValueAppModel.setAppNo(appNo);
			tpQNetValueAppModel.setPlanId(tPlanId);
			tpQNetValueAppModel.setInvestId("FFFFFF");//汇总investId
			NetValueAppEntity tpInvest = this.netValueAppDao.qryAppByUniq(tpQNetValueAppModel);
			if ( tpInvest != null ) {
				tInvestList.add(tpInvest);
			}

			Plan tPlan = netValueAppFormModel.new Plan();
			tPlan.setPlanId(tPlanId);
			tPlan.setPlanName(tPlanName);
			tPlan.setState(keyInInvestNum > 0 ? "已录入" : "");
			tPlan.setInvestList(tInvestList);
			planList.add(tPlan);
			
		}
		netValueAppFormModel.setPlanList(planList);
		
		return netValueAppFormModel;
	}

	@Override
	public NetValueInfQueryFormModel initQryForm(String planId) {
		NetValueInfQueryFormModel netValueInfQueryFormModel = new NetValueInfQueryFormModel();
		//计划列表
		PlanBasicInfoModel planBasicInfoModel = this.planClient.getPlanInfByPlanId(planId);
		List<NetValueAppEntity> planList = new LinkedList<>();
		//主计划
		NetValueAppEntity mPlan = new NetValueAppEntity();
		mPlan.setPlanId(planBasicInfoModel.getPlanId());
		mPlan.setPlanName(planBasicInfoModel.getPlanName());
		planList.add(mPlan);
		//其他计划
		for (OtherPlanInfModel tOtherPlanInfModel : planBasicInfoModel.getOtherPlanInfModelList()) {
			NetValueAppEntity tPlan = new NetValueAppEntity();
			tPlan.setPlanId(tOtherPlanInfModel.getCommonPlanId());
			tPlan.setPlanName(tOtherPlanInfModel.getPlanName());
			planList.add(tPlan);
		}
		//添加计划列表
		netValueInfQueryFormModel.setPlanList(planList);
		
		//定价日列表
		List<NetValueAppEntity> evaluateDateList = this.netValueInfDao.qryEvaluateDateList(planId);
		//添加定价日列表
		netValueInfQueryFormModel.setEvaluateDateList(evaluateDateList);
		return netValueInfQueryFormModel;
	}


	@Override
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity) {
		NetValueAppFormModel2 netValueAppFormModel = this.netValueInfDao.qryInfDesc(netValueInfEntity);
		return netValueAppFormModel;
	}


	@Override
	public void saveMApp(NetValueMAppEntity netValueMAppEntity) {
		this.netValueAppDao.delMApp(netValueMAppEntity);
		this.netValueAppDao.addMApp(netValueMAppEntity);
	}


	@Override
	public List qryOpmApp(AppQryParam appQryParam) {
		appQryParam.setAppType(keyinAppType);
		return this.appService.qryAppList(appQryParam);
	}


	@Override
	public Boolean itfEveluateDateInUse(String planId, String evaluateDate) {
		NetValueInfEntity qryNetValueInfEntity = new NetValueInfEntity();
		qryNetValueInfEntity.setPlanId(planId);
		qryNetValueInfEntity.setEvaluateDate(evaluateDate);
		List<NetValueInfEntity> resInfList = this.netValueInfDao.qryInf(qryNetValueInfEntity);
		return ( resInfList != null && resInfList.size() > 0 );
	}



}
