package com.opm.acct.mdt.det.imp.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.remote.AppAudTask;
import com.opm.acct.common.remote.AppChannel;
import com.opm.acct.common.remote.AppModel;
import com.opm.acct.common.remote.AppTrdRelTask;
import com.opm.acct.mdt.det.imp.service.IMdtDetImpService;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdDoTask;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdPreTask;
import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;

@RestController
@RequestMapping("/manacct/acctmdtdetimp")
public class AcctMdtDetImpController {
	private static final Logger LOGGER =  LoggerFactory.getLogger(AcctMdtDetImpController.class);
	@Autowired
	private IMdtDetImpService mdtDetImpService;
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/submit", method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctmdtdetimp/submit
	public ResponseModel submit(RequestModel reqModel){
		String funcName = "受托户明细导入";
		Map params = reqModel.getPrivateCtx();
		String serialNo = params.get("serialNo")+"";
		if (StringUtils.isEmpty(serialNo)) {
			return new ResponseModel(ResponseModel.State.FAIL, funcName + "提交失败, 未导入文件", null);
		}
		try{
				//提交申请
				CommonBusiness mdtDetImpBusiness = new CommonBusiness();
				RetResult brr = mdtDetImpBusiness
					.register(AppAudTask.class, reqModel.getPrivateCtx()) //提交申请 
					.register(MdtDetImpTrdPreTask.class, reqModel.getPrivateCtx()) //受托户明细导入数据准备  校验  申请号和交易号 
					.register(AppTrdRelTask.class, reqModel.getPrivateCtx()) //申请号和交易号 
				.doBusiness();
				if (!brr.getResult()) {
					return  new ResponseModel(ResponseModel.State.FAIL, brr.getReason(), null);
				}
				return new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", brr.getReturnObj());
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				return new ResponseModel(ResponseModel.State.FAIL, funcName + "异常", null);
			}
		
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/audit",method = RequestMethod.POST)
	public String audit(Map params){
		//参数封装
		AppModel appModel = new AppModel(params);
		
		CommonBusiness netValueBusiness = new CommonBusiness();//TODO Q：回滚时需要保存每一Trade的Context，因为同一个key值可能在不同的Trade中发生变化
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, appModel, AppChannel.class) //审批申请 //操作标记 //根据申请状态写Channel //TODO Q：Channel是在Trade执行完成后执行？
			.register(MdtDetImpTrdDoTask.class, null) //保存信息表 //TODO Q：和保存交易明细合并为一个Trade(一个保存trd表 + 一个inf表)
			.register(AppTrdRelTask.class, null) //申请号和交易号 //TODO Q：需要取到保存申请主表的AppNo和保存交易主表的TrdNo
		.doBusiness();
		return (String)brr.getReturnObj();
	}
	
	@RequestMapping(value = "/mdtAcctLastDet", method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctrecord/submit
	public ResponseModel mdtAcctLastDet(RequestModel reqModel){
		String funcName = "查询最后一条导入明细";
		@SuppressWarnings("rawtypes")
		Map params = reqModel.getPrivateCtx();
		ResponseModel resModel = new ResponseModel(ResponseModel.State.SUCC, 
				funcName + "成功", mdtDetImpService.getMdtAcctLastDet(params.get("planId")+""));
		return resModel;
	}
}
