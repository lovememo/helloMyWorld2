package com.opm.core.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.core.common.dao.FileDao;
import com.opm.core.common.model.FileRelApp;
import com.opm.core.feign.IFIleClient;


@Service
public class FileService {

	@Autowired
	FileDao fileDao;

	@Autowired 
	IFIleClient fileClient;
	
	public FileRelApp qryFileRel(FileRelApp fileRelApp) {
		return fileDao.qryFileRel(fileRelApp);
	}
	
	public int insertFileRelApp(String fileNo,String appNo){
		FileRelApp fileRelApp = new FileRelApp();
		fileRelApp.setApp_no(appNo);
		fileRelApp.setSerial_no(fileNo);
		return fileDao.insertFileRelApp(fileRelApp);
	}
	
	/**
	 * �첽��Ч�������ύ���֮����ã���֪�ļ�����Խ��д����ˡ�
	 * @return 
	 */
	public ResponseModel beginFileProcess(RequestModel requestModel){
		return fileClient.clean(requestModel);
	}
	
}
