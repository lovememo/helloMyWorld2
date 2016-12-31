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
 * ��ֵ����Service������һ�������൱��һ���洢���̣�
 * @author kfzx-chenym
 *
 */
public interface INetValueService {

	/**
	 * ���澻ֵ������ϸ
	 * @param netValueAppModel
	 */
	public void saveApp(NetValueAppEntity netValueAppModel) ;


	/**
	 * ͨ�����׺Ų�ѯ������ϸ
	 * @param trdNo
	 * @return
	 */
	public List<NetValueTrdEntity> qryTrdList(String trdNo);

	/**
	 * ��ѯ����ľ�ֵ¼������
	 * @param appModel
	 * @return
	 */
	public List qrySavedApp(AppModel appModel);

	/**
	 * ��ѯ�ƻ���Ͷ�������Ϣ
	 * @param planId
	 * @return
	 */
	public NetValueAppFormModel qryPlanInvest(String planId);

	/**
	 * ���澻ֵ������ϸ
	 * @param netValueTrdModel
	 */
	public void saveTrd(NetValueTrdEntity netValueTrdModel);

	/**
	 * ���澻ֵ��Ϣ
	 * @param tNetValueInfMode
	 */
	public void saveInf(NetValueInfEntity tNetValueInfMode);

	/**
	 * ���ɾ�� 
	 * @param netValueAppModel
	 */
	public void delApp(NetValueAppEntity netValueAppModel);

	/**
	 * ��ѯ��������
	 * @param appNo
	 * @return
	 */
	public NetValueAppFormModel qryAppDet(String appNo);

	/**
	 * ��ʼ����ֵ��ѯ������
	 * @param planId
	 * @return 
	 */
	public NetValueInfQueryFormModel initQryForm(String planId);


	/**
	 * ��ѯ��ֵ
	 * @param netValueInfEntity
	 * @return
	 */
	public NetValueAppFormModel2 qryInfDesc(NetValueInfEntity netValueInfEntity);


	/**
	 * ��ֵ��������
	 * @param tNetValueMAppEntity
	 */
	public void saveMApp(NetValueMAppEntity tNetValueMAppEntity);


	/**
	 * ��ѯOpm��������
	 * @param appQryParam
	 * @return
	 */
	public List qryOpmApp(AppQryParam appQryParam);


	/**
	 * ��ѯ�������Ƿ�ʹ��
	 * @param planId
	 * @param evaluateDate
	 * @return
	 */
	public Boolean itfEveluateDateInUse(String planId, String evaluateDate);

}
