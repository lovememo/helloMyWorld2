package com.opm.core.investManager.trade;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.service.ITrdService;
import com.opm.core.investManager.entity.NetValueInfEntity;
import com.opm.core.investManager.entity.NetValueTrdEntity;
import com.opm.core.investManager.service.INetValueService;

/**
 * ��ֵ����
 * Do
 * ��ֵ��ϸ��Ч
 * @author kfzx-chenym
 *
 */
@Service("NetValueTrdDoTask")
public class NetValueTrdDoTask implements ITask {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NetValueTrdDoTask.class);
	
	@Autowired
	private INetValueService netValueService;
	
	@Autowired
	private ITrdService trdService;

	@Override
	public RetResult check(TaskParam checkParam) {
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}

	@Override
	public boolean compensable() {
		return false;
	}

	@Override
	public RetResult doTask(TaskParam taskParam) {
		//TODO ���ս��׺�
		Map paramMap = (Map) taskParam.getSelfParam();
		String trdNo = (String) taskParam.getSelfParam();
		String appNo = (String) taskParam.getContextParam().get("appNo");
		
		//��ѯ��ֵ������ϸ
		List<NetValueTrdEntity> netValueTrdList = this.netValueService.qryTrdList(trdNo);
		for (int i = 0; i < netValueTrdList.size(); i++) {
			NetValueTrdEntity tNetValueTrdMode = netValueTrdList.get(i);
			NetValueInfEntity tNetValueInfMode = new NetValueInfEntity(tNetValueTrdMode);
			tNetValueInfMode.setAcceptDate("1");
			tNetValueInfMode.setLastAppNo(appNo);
			//���뾻ֵ��Ϣ
			this.netValueService.saveInf(tNetValueInfMode);
		}
		
		RetResult rr = new RetResult();
		rr.setResult(true);
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

	public INetValueService getNetValueService() {
		return netValueService;
	}

	public void setNetValueService(INetValueService netValueService) {
		this.netValueService = netValueService;
	}

}
