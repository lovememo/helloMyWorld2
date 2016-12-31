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
	
	private static String keyinAppType = "NETVALUEADD"; //��ֵ¼����������
	private static String modAppType = "NETVALUEMOD"; //��ֵ�޸���������
	
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
		//TODO ����������ϸ
		//ɾ��������ϸ
		this.netValueAppDao.delApp(netValueAppModel);
		//����������ϸ
		this.netValueAppDao.addApp(netValueAppModel);
		
	}


	@Override
	public List qrySavedApp(AppModel appModel) {
		
		AppModel qryAppModel = new AppModel();
		qryAppModel.setPlanId(appModel.getPlanId());
		qryAppModel.setAppType(keyinAppType);
		qryAppModel.setAppState("2"); //����״̬
		return this.appDao.qryApp(qryAppModel);
		
	}
	
	@Override
	public List<NetValueTrdEntity> qryTrdList(String trdNo) {
		return this.netValueTrdDao.qryTrdByTrdNo(trdNo);
	}


	@Override
	public void saveTrd(NetValueTrdEntity netValueTrdModel) {
		//TODO ���ݽ�����ϸ
		//ɾ��������ϸ
		this.netValueTrdDao.delTrd(netValueTrdModel);
		//����������ϸ
		this.netValueTrdDao.addTrd(netValueTrdModel);
		
	}

	@Override
	public void saveInf(NetValueInfEntity netValueInfMode) {
		//TODO ������Ϣ
		//��Ϣ�Ƿ����
		NetValueInfEntity qryNetValueInfModel = new NetValueInfEntity();
		qryNetValueInfModel.setPlanId(netValueInfMode.getPlanId());
		qryNetValueInfModel.setEvaluateDate(netValueInfMode.getEvaluateDate());
		qryNetValueInfModel.setOpDate(netValueInfMode.getOpDate());
		NetValueInfEntity resNetValueInfModel = this.netValueInfDao.qryInfByPK(qryNetValueInfModel);
		if ( resNetValueInfModel == null ) { //��Ϣ������
			
			//������Ϣ
			this.netValueInfDao.addInf(netValueInfMode);
			
		} else { //��Ϣ����

			NetValueInfEntity updNetValueInfModel = netValueInfMode;
			//���� �ƻ�+���+������ �������ݣ��ɸ��»����ա�ÿ�ݽ��ۼƷݶ�ʲ���ֵ����ֵ�����ʱ�䡢�����
			this.netValueInfDao.updInf(updNetValueInfModel);
			
		}		
		
	}
	
	@Override
	public NetValueAppFormModel qryPlanInvest(String planId) {

		PlanBasicInfoModel planBasicInfoModel = this.planClient.getPlanInfByPlanId(planId);
		//�����ƻ�Ҳ���������ƻ��б��Ա�ǰ̨ȡ��
		NetValueAppFormModel netValueAppFormModel = new NetValueAppFormModel();
		
		netValueAppFormModel.setCommonPlanId(planBasicInfoModel.getCommonPlanId());
		netValueAppFormModel.setEvaluateDate(""); //TODO ������
		netValueAppFormModel.setIcbcFlag(planBasicInfoModel.getIcbcFlag());
		netValueAppFormModel.setNextEvaluateData(""); //TODO ��һ������
		netValueAppFormModel.setPlanEvalType(planBasicInfoModel.getPlanEvalType());
		netValueAppFormModel.setPlanId(planBasicInfoModel.getPlanId());
		netValueAppFormModel.setPlanName(planBasicInfoModel.getPlanName());
		List<Plan> planList = new LinkedList<Plan>();
		
		//���ƻ���ϢתΪ���ƻ�
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
		
		//�����ƻ�Ͷ�����
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
		//���ɾ����������
		AppModel appModel = new AppModel();
		appModel.setAppNo(netValueAppModel.getAppNo());
		appModel.setAppState("10"); //TODO ״̬ö�٣�
		this.appService.modApp(appModel);
		
	}
	

	@Override
	public NetValueAppFormModel qryAppDet(String appNo) {
		//���������ѯ����¼��ļƻ��µ�Ͷ�����
		AppModel resAppModel = this.appDao.qryAppByAppNo(appNo);
		
		//���ݼƻ���ѯ�ƻ��µ�Ͷ��
		PlanBasicInfoModel planBasicInfoModel = this.planClient.getPlanInfByPlanId(resAppModel.getPlanId());
		
		//������¼�����ͼƻ��µ�Ͷ�����ݰ�
		NetValueAppFormModel netValueAppFormModel = new NetValueAppFormModel();
		netValueAppFormModel.setPlanId(planBasicInfoModel.getPlanId());
		netValueAppFormModel.setPlanName(planBasicInfoModel.getPlanName());
		netValueAppFormModel.setCommonPlanId(planBasicInfoModel.getCommonPlanId());
		netValueAppFormModel.setEvaluateDate(""); //TODO ������
		netValueAppFormModel.setNextEvaluateData(""); //TODO �¸�������
		netValueAppFormModel.setPlanEvalType(planBasicInfoModel.getPlanEvalType());
		
		List<Plan> planList = new LinkedList<Plan>();
		
		//���ƻ�Ͷ��
		String mPlanId = planBasicInfoModel.getPlanId();
		String mPlanName = planBasicInfoModel.getPlanName();
		List<NetValueAppEntity> mInvestList = new LinkedList<>();
		int keyInMInvestNum = 0;//�ƻ����Ƿ�¼��Ͷ���жϱ��
		for ( InvestInfModel investInfModel : planBasicInfoModel.getInvestInfModelList()) {
			String tInvestId = investInfModel.getInvestId();
			String tInvestName = investInfModel.getInvestName();
			//��ѯ�ƻ���Ͷ��¼����
			NetValueAppEntity tQryNetValueAppModel = new NetValueAppEntity();
			tQryNetValueAppModel.setAppNo(appNo);
			tQryNetValueAppModel.setPlanId(mPlanId);
			tQryNetValueAppModel.setInvestId(tInvestId);
			NetValueAppEntity tInvest = this.netValueAppDao.qryAppByUniq(tQryNetValueAppModel);
			if ( tInvest == null ) { //û¼��
				tInvest = new NetValueAppEntity();
				tInvest.setInvestId(tInvestId);
				tInvest.setInvestName(tInvestName);
			} else { //��¼��
				tInvest.setInvestName(tInvestName);//����Ͷ������
				keyInMInvestNum++;
			}
			mInvestList.add(tInvest);
		}
		
		//ͳһ�ƻ���Ͷ����ϻ�����
		NetValueAppEntity qryNetValueAppModel = new NetValueAppEntity();
		qryNetValueAppModel.setAppNo(appNo);
		qryNetValueAppModel.setPlanId(netValueAppFormModel.getPlanId());
		qryNetValueAppModel.setInvestId("FFFFFF");//����investId
		NetValueAppEntity sumNetValueAppModel = this.netValueAppDao.qryAppByUniq(qryNetValueAppModel);
		if ( sumNetValueAppModel != null ) {
			netValueAppFormModel.setSumInvest(sumNetValueAppModel);
		}
		

		Plan mPlan = netValueAppFormModel.new Plan();
		mPlan.setPlanId(mPlanId);
		mPlan.setPlanName(mPlanName);
		mPlan.setState(keyInMInvestNum > 0 ? "��¼��" : "");
		mPlan.setInvestList(mInvestList);
		planList.add(mPlan);
		
		//�����ƻ�Ͷ��
		for (OtherPlanInfModel otherPlanInfModel: planBasicInfoModel.getOtherPlanInfModelList()) {

			String tPlanId = otherPlanInfModel.getCommonPlanId();
			String tPlanName = otherPlanInfModel.getPlanName();
			List<NetValueAppEntity> tInvestList = new LinkedList<NetValueAppEntity>();
			
			int keyInInvestNum = 0;//�ƻ����Ƿ�¼��Ͷ���жϱ��
			for ( OtherInvestInfModel otherInvestInfModel : otherPlanInfModel.getOtherInvestInfModelList() ) {
				String tInvestId = otherInvestInfModel.getInvestId();
				String tInvest_name = otherInvestInfModel.getInvestName();
				//��ѯ�ƻ���Ͷ��¼����
				NetValueAppEntity tQryNetValueAppModel = new NetValueAppEntity();
				tQryNetValueAppModel.setAppNo(appNo);
				tQryNetValueAppModel.setPlanId(tPlanId);
				tQryNetValueAppModel.setInvestId(tInvestId);
				NetValueAppEntity tInvest = this.netValueAppDao.qryAppByUniq(tQryNetValueAppModel);
				if ( tInvest == null ) { //û¼��
					tInvest = new NetValueAppEntity();
					tInvest.setInvestId(tInvestId);
					tInvest.setInvestName(tInvest_name);
				} else { //��¼��
					tInvest.setInvestName(tInvest_name);//����Ͷ������
					keyInInvestNum++;
				}
				tInvestList.add(tInvest);
				
			}

			//��һ�ƻ��������
			NetValueAppEntity tpQNetValueAppModel = new NetValueAppEntity();
			tpQNetValueAppModel.setAppNo(appNo);
			tpQNetValueAppModel.setPlanId(tPlanId);
			tpQNetValueAppModel.setInvestId("FFFFFF");//����investId
			NetValueAppEntity tpInvest = this.netValueAppDao.qryAppByUniq(tpQNetValueAppModel);
			if ( tpInvest != null ) {
				tInvestList.add(tpInvest);
			}

			Plan tPlan = netValueAppFormModel.new Plan();
			tPlan.setPlanId(tPlanId);
			tPlan.setPlanName(tPlanName);
			tPlan.setState(keyInInvestNum > 0 ? "��¼��" : "");
			tPlan.setInvestList(tInvestList);
			planList.add(tPlan);
			
		}
		netValueAppFormModel.setPlanList(planList);
		
		return netValueAppFormModel;
	}

	@Override
	public NetValueInfQueryFormModel initQryForm(String planId) {
		NetValueInfQueryFormModel netValueInfQueryFormModel = new NetValueInfQueryFormModel();
		//�ƻ��б�
		PlanBasicInfoModel planBasicInfoModel = this.planClient.getPlanInfByPlanId(planId);
		List<NetValueAppEntity> planList = new LinkedList<>();
		//���ƻ�
		NetValueAppEntity mPlan = new NetValueAppEntity();
		mPlan.setPlanId(planBasicInfoModel.getPlanId());
		mPlan.setPlanName(planBasicInfoModel.getPlanName());
		planList.add(mPlan);
		//�����ƻ�
		for (OtherPlanInfModel tOtherPlanInfModel : planBasicInfoModel.getOtherPlanInfModelList()) {
			NetValueAppEntity tPlan = new NetValueAppEntity();
			tPlan.setPlanId(tOtherPlanInfModel.getCommonPlanId());
			tPlan.setPlanName(tOtherPlanInfModel.getPlanName());
			planList.add(tPlan);
		}
		//��Ӽƻ��б�
		netValueInfQueryFormModel.setPlanList(planList);
		
		//�������б�
		List<NetValueAppEntity> evaluateDateList = this.netValueInfDao.qryEvaluateDateList(planId);
		//��Ӷ������б�
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
