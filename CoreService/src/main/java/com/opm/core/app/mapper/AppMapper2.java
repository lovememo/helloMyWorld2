package com.opm.core.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;

@Mapper
public interface AppMapper2 {

	/**
	 * insert
	 * table:opm_app
	 * @param appModel
	 */
	public void addApp(AppModel appModel);
	/**
	 * insert
	 * procedure:pkg_opm_app.prod_app_aud
	 * @param appModel
	 */
	public void audApp(AppModel appModel);

	/**
	 * select
	 * table:opm_app
	 * @param appModel
	 * @return
	 */
	//TODO:不知道怎么传三个参数，暂时用一个参数代替
	//public List<AppModel> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum);
	public List<AppModel> qryAppList(AppQryParam appQryParam);
	public String qryAppCount(AppQryParam appQryParam);
	
	public List<AppModel> qryAppType(String moduleId);
	public List qryApp(AppModel appModel);
	/**
	 * update
	 * table:opm_app
	 * @param appModel
	 */
	public void updApp(AppModel appModel);

	/**
	 * 查询申请下的最大交易排序
	 * @param appTrdRelModel
	 * @return
	 */
	public Long qryTrdMaxOrder(AppTrdRelModel appTrdRelModel);

	/**
	 * insert 
	 * table:opm_trd_rel_app
	 * @param appTrdRelModel
	 */
	public void addRelAppTrd(AppTrdRelModel appTrdRelModel);

	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel);

	
}
