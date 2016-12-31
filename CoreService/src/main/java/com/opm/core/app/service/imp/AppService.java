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
		//�Ƿ��������
		if ( appModel.getAppNo() == null ) {
			//Ĭ��Ϊ��������룿
			if(appModel.getAppState()==null){
				appModel.setAppState(AppState.SAVE.getCode());
			}
			this.appDao.addApp(appModel);
		} else {
			//�ж������Ƿ����
			AppModel resAppModel = this.appDao.qryAppByAppNo(appModel.getAppNo());
			if ( resAppModel == null ) { //��������
				appModel.setAppState(AppState.SAVE.getCode());
				this.appDao.addApp(appModel);
			} else { //��������
				AppModel updAppModel = new AppModel();
				if ( resAppModel.getAppType().equals(appModel.getAppType()) ) { //����������һ��
					
					if ( resAppModel.getAppState().equals(AppState.SAVE.getCode()) ) { //����״̬������
						
						updAppModel.setAppNo(appModel.getAppNo());
						updAppModel.setAppStru(appModel.getAppStru());
						updAppModel.setAppUser(appModel.getAppUser());
						updAppModel.setAppTime("1"); //��������ʱ��
						
						this.appDao.updApp(updAppModel);
					}
					
				}
			}
		}

		return appModel;
		
	}
	
	@Override
	public void controlApp(AppModel appModel) {
		//TODO ���̿���
		//TODO ��������
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
		//��ѯ����
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
				updAppModel.setAudTime("1"); //���¸���ʱ��
				
				this.appDao.updApp(updAppModel);
			} else {
				//TODO �����Ѹ���
			}
		}
		
	}

	@Override
	public void relTrdApp(AppTrdRelModel appTrdRelModel) {
		//ȡ����Order
		AppTrdRelModel qryAppTrdRelModel = new AppTrdRelModel();
		qryAppTrdRelModel.setTrdNo(appTrdRelModel.getTrdNo());
		qryAppTrdRelModel.setAppNo(appTrdRelModel.getAppNo());
		qryAppTrdRelModel.setTrdType(appTrdRelModel.getTrdType());
		AppTrdRelModel resAppTrdRel = this.appDao.qryTrdAppRelByPK(qryAppTrdRelModel);
		if ( resAppTrdRel == null ) {
			Long maxOrder = this.appDao.qryTrdMaxOrder(qryAppTrdRelModel); //TODO ��opm_trd_rel_app��
			if(maxOrder == null){
				maxOrder = 0L;
			}
			//��������ͽ���
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
