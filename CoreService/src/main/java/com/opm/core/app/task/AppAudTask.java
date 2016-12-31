package com.opm.core.app.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.app.dao.IAppDao;
import com.opm.core.app.model.AppModel;
@Service("AppAudTask")
public class AppAudTask implements ITask {
	
	@Autowired
	private IAppDao appDao;
	
	@Override
	public RetResult check(TaskParam checkParam) {

		RetResult res=new RetResult();
		res.setResult(true);
		return res;
	}

	@Override
	public boolean compensable() {
		return true;
	}

	@Override
	public RetResult doTask(TaskParam taskParam) {
		Map<String, Object> ctx = taskParam.getContextParam();
		Map<String, Object> param = (Map<String, Object>) taskParam.getSelfParam();
		AppModel app = new AppModel();
		RetResult ret=new RetResult();
		if(ctx != null){
		if(ctx.containsKey("appNo")){app.setAppNo((String)ctx.get("appNo"));}
		if(ctx.containsKey("appType")){app.setAppType((String)ctx.get("appType"));}
		if(ctx.containsKey("opType")){app.setOpFlag((String)ctx.get("opType"));}
		if(ctx.containsKey("planId")){app.setPlanId((String)ctx.get("planId"));}
		if(ctx.containsKey("planTime")){app.setAudPlanTime((String)ctx.get("planTime"));}
		if(ctx.containsKey("userId")){app.setAudUser((String)ctx.get("userId"));}
		if(ctx.containsKey("defaultRoleId")){app.setAudRole((String)ctx.get("defaultRoleId"));}
		if(ctx.containsKey("branchId")){app.setAudStru((String)ctx.get("branchId"));}
		if(ctx.containsKey("memo")){app.setAudMemo((String)ctx.get("memo"));}
		}else{
			ctx = new HashMap<String, Object>();
		}
		if(param != null){
		if(param.containsKey("appNo")){app.setAppNo((String)param.get("appNo"));}
		if(param.containsKey("appType")){app.setAppType((String)param.get("appType"));}
		if(param.containsKey("opType")){app.setOpFlag((String)param.get("opType"));}
		if(param.containsKey("planId")){app.setPlanId((String)param.get("planId"));}
		if(param.containsKey("planTime")){app.setAudPlanTime((String)param.get("planTime"));}
		if(param.containsKey("userId")){app.setAudUser((String)param.get("userId"));}
		if(param.containsKey("defaultRoleId")){app.setAudRole((String)param.get("defaultRoleId"));}
		if(param.containsKey("branchId")){app.setAudStru((String)param.get("branchId"));}
		if(param.containsKey("memo")){app.setAudMemo((String)param.get("memo"));}
		}
		appDao.audApp(app);
		ret.setReason(app.getRetMsg());
		
		if("0".equals(app.getRetFlag())){
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		ret.setReturnObj(app);
		ctx.put("appNo", app.getAppNo());
		ctx.put("appState", app.getAppState());
		
		ret.setContextObj(ctx);
		return ret;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {

		RetResult res=new RetResult();
		res.setResult(false);
		return res;
	}

}
