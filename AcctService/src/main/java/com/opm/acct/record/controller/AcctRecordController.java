package com.opm.acct.record.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.remote.AppAudTask;
import com.opm.acct.common.remote.AppChannel;
import com.opm.acct.common.remote.AppModel;
import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.acct.common.remote.AppTrdRelTask;
import com.opm.acct.mdt.model.MdtDetTrdModel;
import com.opm.acct.record.trade.MdtAcctRecordTrdPreTask;

@RestController
@RequestMapping("/manacct/acctrecord")
public class AcctRecordController {
	private static final Logger LOGGER =  LoggerFactory.getLogger(AcctRecordController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/submit", method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctrecord/submit
	public ResponseModel submit(RequestModel reqModel){
		String funcName = "受托户记账数据准备";
		Map params = reqModel.getPrivateCtx();
		if (null == params ||  !params.containsKey("list")) {
			return new ResponseModel(ResponseModel.State.FAIL, funcName + "失败, 参数错误", null);
		}
		//受托户会计记账交易数据
		List<Map> tList = (List<Map>)params.get("list");
		if (null == tList || 0 == tList.size()) {
			return new ResponseModel(ResponseModel.State.FAIL, funcName + "失败, 参数错误", null);
		}
		try {
			List<MdtDetTrdModel> mdtDetTrdList = new ArrayList<MdtDetTrdModel>();
			MdtDetTrdModel mdtDetTrdModel = null;
			String retStr= null;
			Map tMap = null;
			for (int i = 0; i < tList.size(); i++) {
				tMap = tList.get(i);
				mdtDetTrdModel = new MdtDetTrdModel();
				retStr = mdtDetTrdModel.checkAndSetParams(tMap, mdtDetTrdModel);
				if (StringUtils.isNotEmpty(retStr)) {
					return new ResponseModel(ResponseModel.State.FAIL, funcName + "失败,"+retStr+" 参数为空", null);
				}
				mdtDetTrdList.add(mdtDetTrdModel);
			}
			
			//提交申请
			reqModel.getPrivateCtx().put("appType", "acctrecord");
			reqModel.getPrivateCtx().put("opType", "submit");
			reqModel.getPrivateCtx().put("list", mdtDetTrdList);
			CommonBusiness netValueBusiness = new CommonBusiness();
			RetResult brr = netValueBusiness
				//.register(AppAudTask.class, reqModel.getPrivateCtx()) //提交申请 //操作标记
				.register(MdtAcctRecordTrdPreTask.class, reqModel.getPrivateCtx()) //保存申请明细
				//.register(AppTrdRelTask.class,  reqModel.getPrivateCtx()) //申请号和交易号 
			.doBusiness();
			if (!brr.getResult()) {
				return  new ResponseModel(ResponseModel.State.FAIL, brr.getReason(), null);
			}
			return new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", brr.getReturnObj());
		} catch(Exception e) {
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
		//参数封装
		//TrdModel trdModel = new TrdModel(params);
		
		
		CommonBusiness netValueBusiness = new CommonBusiness();
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, appModel, AppChannel.class) //审批申请 //操作标记 //根据申请状态写Channel 
			//.register(MdtAcctRecordTrdDoTask.class, trdModel.getTrdNo()) //通过申请号+交易类型 = 取交易流水号（单TrdNo）
			//.register(TrdTask.class, trdModel) //更新交易主表 
			.register(AppTrdRelTask.class, null) //申请号和交易号 
			.doBusiness();
		return (String)brr.getReturnObj();
	}
}
