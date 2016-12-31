package com.opm.core.plan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.opm.core.plan.model.PlanAppInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.workflow.model.ApplicationModle;

@Mapper
public interface PlanMapper {
	/**
	 * ��ȡ�ƻ�������Ϣ
	 * 
	 * @param appNo
	 *            �����
	 * @return ��������
	 */
	public PlanAppInfModel getPlanAppDet(@Param("appNo") long appNo);

	/**
	 * ��ȡ�ƻ������б�
	 * 
	 * @param branchId
	 *            ��������
	 * @param userId
	 *            �û�����
	 * @param roleId
	 *            ��ɫ����
	 * @return �б�
	 */
	public List<ApplicationModle> getPlanApplys(@Param("branchId") String branchId, 
												@Param("userId") String userId, 
												@Param("roleId") String roleId);
	

	/**
     * �ӿ�
     * ���뵱ǰ�ƻ����룬���ع����ƻ��б����ƻ����룬�Ƿ��������ˡ�
     * @param planId �ƻ�����
     * @return �ƻ���Ϣ
     */
    public PlanBasicInfoModel getPlanInfByPlanId(String planId);
    

	/**
     * app
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicApp(PlanBasicInfoModel model);
    

	/**
     * app
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicApp(String appNo);
    

	/**
     * app
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicApp(PlanBasicInfoModel model);
    

	/**
     * app
     * ��ȡ����ļƻ�������Ϣ
     */
    public List<Map<String,String>> selectSavedPlanApp();
    

	/**
     * ����
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicTrd(PlanBasicInfoModel model);
    

	/**
     * ����
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicTrd(String trdNo);
    

	/**
     * ����
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicTrd(PlanBasicInfoModel model);
    

	/**
     * ����
     * ��ȡ����ļƻ�������Ϣ
     */
    public List<Map<String,String>> selectSavedPlanTrd();
    

	/**
     * ��Ч
     * ����ƻ�������Ϣ
     */
    public Integer insertPlanBasicInf(PlanBasicInfoModel model);
    

	/**
     * 
     * ��ѯ�ƻ�������Ϣ
     */
    public PlanBasicInfoModel selectPlanBasicInf(String planId);
    

	/**
     * ��Ч
     * ���¼ƻ�������Ϣ
     */
    public Integer updatePlanBasicInf(PlanBasicInfoModel model);
}
