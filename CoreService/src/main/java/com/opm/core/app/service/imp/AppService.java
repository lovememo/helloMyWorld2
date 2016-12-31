package com.opm.core.app.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.app.dao.IAppDao;
import com.opm.core.app.dic.AppState;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.model.AppTrdRelModel;
import com.opm.core.app.service.IAppService;
import com.opm.core.workflow.mapper.AppMapper;
import com.opm.core.common.model.FileRelApp;

@Service("AppService")
public class AppService implements IAppService {
	
	@Autowired
	private IAppDao appDao;
	//private AppMapper appDao;

	@Override
	public AppModel saveApp(AppModel appModel) {
		//是否有申请号
		if ( appModel.getAppNo() == null ) {
			//默认为保存的申请？
			if(appModel.getAppState()==null){
				appModel.setAppState(AppState.SAVE.getCode());
			}
			this.appDao.addApp(appModel);
		} else {
			//判断申请是否存在
			AppModel resAppModel = this.appDao.qryAppByAppNo(appModel.getAppNo());
			if ( resAppModel == null ) { //新增申请
				appModel.setAppState(AppState.SAVE.getCode());
				this.appDao.addApp(appModel);
			} else { //更新申请
				AppModel updAppModel = new AppModel();
				if ( resAppModel.getAppType().equals(appModel.getAppType()) ) { //申请类型需一致
					
					if ( resAppModel.getAppState().equals(AppState.SAVE.getCode()) ) { //保存状态的申请
						
						updAppModel.setAppNo(appModel.getAppNo());
						updAppModel.setAppStru(appModel.getAppStru());
						updAppModel.setAppUser(appModel.getAppUser());
						updAppModel.setAppTime("1"); //更新申请时间
						
						this.appDao.updApp(updAppModel);
					}
					
				}
			}
		}

		return appModel;
		
	}
	
	@Override
	public void controlApp(AppModel appModel) {
		//TODO 流程控制
		//TODO 更新申请
		this.modApp(appModel);
		
	}

	@Override
	public String qryTrdNo(String appNo, String trdType) {
		AppTrdRelModel qryAppTrdRelModel = new AppTrdRelModel();
		qryAppTrdRelModel.setAppNo(appNo);
		qryAppTrdRelModel.setTrdType(trdType);
		AppTrdRelModel qryTrdAppRef = appDao.qryTrdAppRef(qryAppTrdRelModel);
		return qryTrdAppRef.getTrdNo();
	}


	@Override
	public void modApp(AppModel appModel) {
		//查询申请
		AppModel qryAppModel = new AppModel();
		qryAppModel.setAppNo(appModel.getAppNo());
		AppModel resAppModel = this.appDao.qryAppByAppNo(appModel.getAppNo());
		if ( resAppModel != null ) {
			if ( !( resAppModel.getAppState().equals(AppState.AGREE.getCode()) 
					||  resAppModel.getAppState().equals(AppState.DENY.getCode()) 
					|| resAppModel.getAppState().equals(AppState.DELETE.getCode()) ) ) {
				
				AppModel updAppModel = new AppModel();
				updAppModel.setAppNo(appModel.getAppNo());
				updAppModel.setAppState(appModel.getAppState());
				updAppModel.setAudPlanTime(appModel.getAudPlanTime());
				updAppModel.setAudStru(appModel.getAudStru());
				updAppModel.setAudUser(appModel.getAudUser());
				updAppModel.setAudTime("1"); //更新复核时间
				
				this.appDao.updApp(updAppModel);
			} else {
				//TODO 申请已复核
			}
		}
		
	}

	@Override
	public void relTrdApp(AppTrdRelModel appTrdRelModel) {
		//取最大的Order
		AppTrdRelModel qryAppTrdRelModel = new AppTrdRelModel();
		qryAppTrdRelModel.setTrdNo(appTrdRelModel.getTrdNo());
		qryAppTrdRelModel.setAppNo(appTrdRelModel.getAppNo());
		qryAppTrdRelModel.setTrdType(appTrdRelModel.getTrdType());
		AppTrdRelModel resAppTrdRel = this.appDao.qryTrdAppRelByPK(qryAppTrdRelModel);
		if ( resAppTrdRel == null ) {
			Long maxOrder = this.appDao.qryTrdMaxOrder(qryAppTrdRelModel); //TODO 锁opm_trd_rel_app表？
			if(maxOrder == null){
				maxOrder = 0L;
			}
			//保存申请和交易
			appTrdRelModel.setTrdOrder(++maxOrder);
			this.appDao.addRelAppTrd(appTrdRelModel);
		}
	}

	public void audApp(AppModel appModel){
		// TODO Auto-generated method stub
		appDao.audApp(appModel);
	}
	@Override
	//public List<Object> qryAppList(AppQryParam appQryParam,String beginNum,String fetchNum){
	public List<AppModel> qryAppList(AppQryParam appQryParam){
		return this.appDao.qryAppList(appQryParam);
	}
	public String qryAppCount(AppQryParam appQryParam){
		return this.appDao.qryAppCount(appQryParam);
	}
	public IAppDao getAppDao() {
		return appDao;
	}

	public void setAppDao(IAppDao appDao) {
		this.appDao = appDao;
	}

	@Override
	public AppTrdRelModel qryTrdAppRef(AppTrdRelModel appTrdRelModel) {
		return appDao.qryTrdAppRef(appTrdRelModel);
	}
}
