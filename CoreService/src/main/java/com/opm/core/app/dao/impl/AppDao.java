package com.opm.core.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.core.app.dao.IAppDao;
import com.opm.core.app.mapper.AppMapper2;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;

@Repository("AppDao")
public class AppDao implements IAppDao {

	@Autowired
	private AppMapper2 appMapping;
	
	@Override
	public void addApp(AppModel appModel) {
		this.appMapping.addApp(appModel);
	}
	
	@Override
	public void audApp(AppModel appModel) {
		this.appMapping.audApp(appModel);
	}
	@Override
	//public List<AppModel> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum) {
	public List<AppModel> qryAppList(AppQryParam appQryParam) {
		return this.appMapping.qryAppList(appQryParam);
	}
	@Override
	public String qryAppCount(AppQryParam appQryParam) {
		return this.appMapping.qryAppCount(appQryParam);
	}
	@Override
	public List<?> qryAppType(String moduleId){
		return this.appMapping.qryAppType(moduleId);
	}
	public List qryApp(AppModel appModel) {
		return this.appMapping.qryApp(appModel);
	}
	@Override
	public void updApp(AppModel appModel) {
		this.appMapping.updApp(appModel);
		
	}



	@Override
	public AppModel qryAppByAppNo(String appNo) {
		//返回对象
		AppModel resAppModel = null;
		//查询对象
		AppModel qryAppModel = new AppModel();
		qryAppModel.setAppNo(appNo);
		List<AppModel> appModelList = this.appMapping.qryApp(qryAppModel);
		if ( appModelList != null && appModelList.size() == 1 ) {
			resAppModel = appModelList.get(0);
		} 
		return resAppModel;
	}

	@Override
	public Long qryTrdMaxOrder(AppTrdRelModel appTrdRelModel) {
		return this.appMapping.qryTrdMaxOrder(appTrdRelModel);
	}

	@Override
	public void addRelAppTrd(AppTrdRelModel appTrdRelModel) {
		this.appMapping.addRelAppTrd(appTrdRelModel);
		
	}

	public AppMapper2 getAppMapping() {
		return appMapping;
	}

	public void setAppMapping(AppMapper2 appMapping) {
		this.appMapping = appMapping;
	}

	@Override
	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel) {
		return this.appMapping.qryTrdAppRef(appTrdRelModel);
	}

	@Override
	public AppTrdRelModel qryTrdAppRelByPK(AppTrdRelModel appTrdRelModel) {
		if ( appTrdRelModel.getAppNo() == null || appTrdRelModel.getTrdNo() == null || appTrdRelModel.getTrdType() == null ) {
			return null;
		} else {
			return this.appMapping.qryTrdAppRef(appTrdRelModel);
		}
	}

	
}
