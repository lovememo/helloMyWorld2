package com.opm.core.app.service;

import java.util.List;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.common.model.FileRelApp;

public interface IAppService {

	/**
	 * 保存申请主表
	 * @param appModel
	 * @return
	 */
	public AppModel saveApp(AppModel appModel);


	/**
	 * 查询申请列表
	 * @param AppQryParam
	 */
	//public List<Object> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum);
	public List<AppModel> qryAppList(AppQryParam appQryParam);
	public String qryAppCount(AppQryParam appQryParam);
	/**
	 * 通过申请号查询交易号
	 * @param appNo
	 * @param trdType
	 */
	public String qryTrdNo(String appNo, String trdType);
	/**
	 * 修改申请
	 * @param appModel
	 */
	public void modApp(AppModel appModel);

	/**
	 * 流程控制
	 * @param appModel
	 */
	public void controlApp(AppModel appModel);

	/**
	 * 维护申请与交易关系
	 * @param appTrdRelModel
	 */
	public void relTrdApp(AppTrdRelModel appTrdRelModel);
	
	
	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel);
}
