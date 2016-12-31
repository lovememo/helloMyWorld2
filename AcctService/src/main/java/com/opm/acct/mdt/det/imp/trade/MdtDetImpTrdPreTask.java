package com.opm.acct.mdt.det.imp.trade;

import ch.qos.logback.classic.Logger;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.service.ITrdService;
import com.opm.acct.mdt.det.imp.service.IMdtDetImpService;
import com.opm.acct.mdt.enums.TrdState;
import com.opm.acct.mdt.model.TrdModel;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtDetImpTrdPreTask")
public class MdtDetImpTrdPreTask implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtDetImpTrdPreTask.class);
    
    @Autowired
	private IMdtDetImpService mdtDetImpService;
    
	@Autowired
	private ITrdService trdService;
	

    @Override
    public RetResult check(TaskParam checkParam) {
        LOGGER.info("TestTrade check");
        return new RetResult(true,"check ok");
    }

    @Override
    public boolean compensable() {
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
    public RetResult doTask(TaskParam taskParam) {
        LOGGER.info("TestTrade doTrade");
      //保存交易主表
  		TrdModel trdModel = new TrdModel(null, CommonConstant.TRD_TYPE_MDT_DET_IMP_SUBMIT, TrdState.PREPARED.toString());
  		this.trdService.saveTrd(trdModel);
  		String trdNo = trdModel.getTrdNo();
  		
  		Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
        String planId = (String)paramMap.get("planId");
        String serialNo = (String)paramMap.get("serialNo");
        RetResult ret = mdtDetImpService.doPrepare(trdNo,planId,serialNo);
        ret.getContextObj().put("trdType", CommonConstant.TRD_TYPE_MDT_DET_IMP_SUBMIT);
        return ret;
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTrade doCompensate");
        return new RetResult(true,"doCompensate ok");
    }
}
