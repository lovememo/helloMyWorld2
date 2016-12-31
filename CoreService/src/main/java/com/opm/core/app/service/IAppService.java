package com.opm.core.app.service;

import java.util.List;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.common.model.FileRelApp;

public interface IAppService {

	/**
	 * ������������
	 * @param appModel
	 * @return
	 */
	public AppModel saveApp(AppModel appModel);


	/**
	 * ��ѯ�����б�
	 * @param AppQryParam
	 */
	//public List<Object> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum);
	public List<AppModel> qryAppList(AppQryParam appQryParam);
	public String qryAppCount(AppQryParam appQryParam);
	/**
	 * ͨ������Ų�ѯ���׺�
	 * @param appNo
	 * @param trdType
	 */
	public String qryTrdNo(String appNo, String trdType);
	/**
	 * �޸�����
	 * @param appModel
	 */
	public void modApp(AppModel appModel);

	/**
	 * ���̿���
	 * @param appModel
	 */
	public void controlApp(AppModel appModel);

	/**
	 * ά�������뽻�׹�ϵ
	 * @param appTrdRelModel
	 */
	public void relTrdApp(AppTrdRelModel appTrdRelModel);
	
	
	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel);
}
