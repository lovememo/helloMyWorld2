package com.opm.acct.record.trade;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.acct.common.CommonConstant;
import com.opm.acct.common.service.ITrdService;
import com.opm.acct.mdt.enums.TrdState;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.mdt.model.TrdModel;
import com.opm.acct.record.service.IMdtAcctRecordService;
import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.common.model.ResponseModel;

import ch.qos.logback.classic.Logger;

/**
 * Created by kfzx-wanghong01 on 2016/12/8.
 */

@Service("MdtAcctRecordTrdPreTask")
public class MdtAcctRecordTrdPreTask implements ITask {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MdtAcctRecordTrdPreTask.class);
    
    @Autowired
	private IMdtAcctRecordService mdtAcctRecordService;
    
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

    @SuppressWarnings({ "unchecked" })
	@Override
    public RetResult doTask (TaskParam taskParam) {
        LOGGER.info("TestTrade doTrade");
        //保存交易主表
  		TrdModel trdModel = new TrdModel(null, CommonConstant.TRD_TYPE_ACCT_RECORD_SUBMIT, TrdState.PREPARED.toString());
  		this.trdService.saveTrd(trdModel);
  		String trdNo = trdModel.getTrdNo();
        //受托户会计记账数据准备及校验
  		Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
		//List<MdtDetTrdModel> mdtDetTrd = (List<MdtDetTrdModel>) taskParam.getSelfParam();
  		//Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
  		List<MdtDetTrdModel> mdtDetTrd = (List<MdtDetTrdModel>) paramMap.get("list");
        String planId = (String)paramMap.get("planId");
        RetResult ret = this.mdtAcctRecordService.doPrepare(mdtDetTrd, trdNo, planId);
        if (!ret.getResult()) {
        	return new RetResult(ret.getResult(), ret.getReason(), null);
        }
        mdtAcctRecordService.doTrade(trdNo, planId);
        return new RetResult(true, "受托户会计记账数据准备成功", null);
    }

    @Override
    public RetResult doCompensate(TaskParam compensateParam) {
        LOGGER.info("TestTrade doCompensate");
        return new RetResult(true,"doCompensate ok");
    }
    
   
}
