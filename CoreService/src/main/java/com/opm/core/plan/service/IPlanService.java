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
	 * ��ȡ�����˵������б�
	 * 
	 * @param branchId
	 *            ��������
	 * @param userId
	 *            �û�����
	 * @param roleId
	 *            ��ɫ����
	 * @return �����б�
	 */
	public List<Map<String, String>> getPlanApplys(String branchId, String userId, String roleId);

	/**
	 * ��ȡ������ϸ��Ϣ
	 * 
	 * @param appNo
	 *            �����
	 * @return ������ϸ��Ϣ
	 */
	public PlanAppInfModel getPlanAppDet(long appNo);

	/**
	 * �ӿ� ���뵱ǰ�ƻ����룬���ع����ƻ��б����ƻ����룬�Ƿ��������ˡ�
	 * 
	 * @param planId
	 *            �ƻ�����
	 * @return �ƻ���Ϣ
	 */
	public PlanBasicInfoModel getPlanInfByPlanId(String planId);

	/**
	 * app ����ƻ�������Ϣ
	 */
	public Integer insertPlanBasicApp(PlanBasicInfoModel model);

	/**
	 * app ��ѯ�ƻ�������Ϣ
	 */
	public PlanBasicInfoModel selectPlanBasicApp(String appNo);

	/**
	 * app ���¼ƻ�������Ϣ
	 */
	public Integer updatePlanBasicApp(PlanBasicInfoModel model);

	/**
	 * app ��ȡ����ļƻ�������Ϣ
	 */
	public List<Map<String, String>> selectSavedPlanApp();

	/**
	 * ���� ����ƻ�������Ϣ
	 */
	public Integer insertPlanBasicTrd(PlanBasicInfoModel model);

	/**
	 * ���� ��ѯ�ƻ�������Ϣ
	 */
	public PlanBasicInfoModel selectPlanBasicTrd(String trdNo);

	/**
	 * ���� ���¼ƻ�������Ϣ
	 */
	public Integer updatePlanBasicTrd(PlanBasicInfoModel model);

	/**
	 * ���� ��ȡ����ļƻ�������Ϣ
	 */
	public List<Map<String, String>> selectSavedPlanTrd();

	/**
	 * ��Ч ����ƻ�������Ϣ
	 */
	public Integer insertPlanBasicInf(PlanBasicInfoModel model);

	/**
	 * 
	 * ��ѯ�ƻ�������Ϣ
	 */
	public PlanBasicInfoModel selectPlanBasicInf(String planId);

	/**
	 * ��Ч ���¼ƻ�������Ϣ
	 */
	public Integer updatePlanBasicInf(PlanBasicInfoModel model);
}
