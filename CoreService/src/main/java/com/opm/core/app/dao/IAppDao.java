package com.opm.core.app.dao;

import java.util.List;
import java.util.Map;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;

public interface IAppDao {

	/**
	 * 插入申请
	 * @param appModel
	 */
	public void addApp(AppModel appModel);
	
	/**
	 * 审批申请
	 * @param appModel
	 */
	public void audApp(AppModel appModel);

	/**
	 * 查询申请
	 * @param appModel
	 * @return
	 */

	//public List<AppModel> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum);
	public List<AppModel> qryAppList(AppQryParam appQryParam);
	/**
	 * 可查看申请类型
	 * @param String moduleId
	 * @return
	 */
	public List<?> qryAppType(String moduleId);
	public List qryApp(AppModel appModel);
	/**
	 * 更新申请
	 * @param appModel
	 */
	public void updApp(AppModel appModel);

	/**
	 * 统计申请数量
	 * @param appModel
	 * @return
	 */
	public String qryAppCount(AppQryParam appQryParam);

	/**
	 * 通过申请号查询
	 * @param appNo
	 * @return
	 */
	public AppModel qryAppByAppNo(String appNo);

	/**
	 * 通过申请号去最大的排序
	 * @param qryAppTrdRelModel
	 * @return
	 */
	public Long qryTrdMaxOrder(AppTrdRelModel qryAppTrdRelModel);

	/**
	 * 新增申请和交易关系
	 * @param appTrdRelModel
	 */
	public void addRelAppTrd(AppTrdRelModel appTrdRelModel);


	/**
	 * 获取申请的交易号
	 * @param appNo
	 * @param trdType
	 * @return
	 */

	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel);

	/**
	 * 通过唯一键查询app和trd关联记录
	 * @param qryAppTrdRelModel
	 * @return
	 */
	public AppTrdRelModel qryTrdAppRelByPK(AppTrdRelModel qryAppTrdRelModel);

}
