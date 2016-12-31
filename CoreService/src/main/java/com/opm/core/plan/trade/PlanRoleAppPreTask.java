package com.opm.core.plan.trade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import com.opm.common.business.task.ITask;
import com.opm.core.plan.model.PlanRoleModel;
import com.opm.core.plan.service.IPlanRoleService;

@Service("PlanRoleAppPreTask")
public class PlanRoleAppPreTask implements ITask {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanRoleAppPreTask.class);

	@Autowired
	private IPlanRoleService service;

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
	@Transactional
	public RetResult doTask(TaskParam taskParam) {
		Map<String, Object> paramMap = (Map<String, Object>) taskParam.getSelfParam();
		String type = (String) paramMap.get("type");

		PlanRoleModel model = new PlanRoleModel();
		model.setAppNo((String) paramMap.get("appNo"));
		model.setPlanId((String) paramMap.get("planId"));
		model.setStatusType((String) paramMap.get("statusType"));
		model.setStruId((String) paramMap.get("struId"));
		model.setOrgCode((String) paramMap.get("orgCode"));
		model.setSubinstid((String) paramMap.get("subinstid"));
		model.setAddr((String) paramMap.get("addr"));
		model.setZipcode((String) paramMap.get("zipcode"));
		model.setBusiMgr((String) paramMap.get("busiMgr"));
		model.setTel((String) paramMap.get("tel"));
		model.setFax((String) paramMap.get("fax"));
		model.setEmail((String) paramMap.get("email"));
		model.setStruName((String) paramMap.get("struName"));

		if ("add".equals(type)) {

			// 保存app
			this.service.insertPlanRoleApp(model);
		} else if ("update".equals(type)) {

			// 更新app
			this.service.updatePlanRoleApp(model);
		} else if ("delete".equals(type)) {

			// 删除app
			this.service.deletePlanRoleApp(model);
		}

		RetResult rr = new RetResult();

		rr.setResult(true);
		rr.setReturnObj(service.selectPlanRoleApp((String) paramMap.get("appNo"), null, null));//获取列表
		return rr;
	}

	@Override
	public RetResult doCompensate(TaskParam compensateParam) {
		RetResult rr = new RetResult();
		rr.setResult(false);
		return rr;
	}

}
