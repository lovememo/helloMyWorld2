package com.opm.core.app.dao;

import java.util.List;
import java.util.Map;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;

public interface IAppDao {

	/**
	 * ��������
	 * @param appModel
	 */
	public void addApp(AppModel appModel);
	
	/**
	 * ��������
	 * @param appModel
	 */
	public void audApp(AppModel appModel);

	/**
	 * ��ѯ����
	 * @param appModel
	 * @return
	 */

	//public List<AppModel> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum);
	public List<AppModel> qryAppList(AppQryParam appQryParam);
	/**
	 * �ɲ鿴��������
	 * @param String moduleId
	 * @return
	 */
	public List<?> qryAppType(String moduleId);
	public List qryApp(AppModel appModel);
	/**
	 * ��������
	 * @param appModel
	 */
	public void updApp(AppModel appModel);

	/**
	 * ͳ����������
	 * @param appModel
	 * @return
	 */
	public String qryAppCount(AppQryParam appQryParam);

	/**
	 * ͨ������Ų�ѯ
	 * @param appNo
	 * @return
	 */
	public AppModel qryAppByAppNo(String appNo);

	/**
	 * ͨ�������ȥ��������
	 * @param qryAppTrdRelModel
	 * @return
	 */
	public Long qryTrdMaxOrder(AppTrdRelModel qryAppTrdRelModel);

	/**
	 * ��������ͽ��׹�ϵ
	 * @param appTrdRelModel
	 */
	public void addRelAppTrd(AppTrdRelModel appTrdRelModel);


	/**
	 * ��ȡ����Ľ��׺�
	 * @param appNo
	 * @param trdType
	 * @return
	 */

	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel);

	/**
	 * ͨ��Ψһ����ѯapp��trd������¼
	 * @param qryAppTrdRelModel
	 * @return
	 */
	public AppTrdRelModel qryTrdAppRelByPK(AppTrdRelModel qryAppTrdRelModel);

}
