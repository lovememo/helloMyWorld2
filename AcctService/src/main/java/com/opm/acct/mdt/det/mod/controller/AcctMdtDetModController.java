package com.opm.acct.mdt.det.mod.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.acct.common.remote.AppChannel;
import com.opm.acct.common.remote.AppModel;
import com.opm.acct.common.remote.AppAudTask;
import com.opm.acct.mdt.det.imp.service.IMdtDetImpService;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdDoTask;
import com.opm.acct.mdt.det.imp.trade.MdtDetImpTrdPreTask;
import com.opm.acct.mdt.model.MdtAcctdetInfModel;
import com.opm.common.business.business.CommonBusiness;
import com.opm.common.business.param.RetResult;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.model.ResponseModel.State;
import com.opm.acct.common.remote.AppTrdRelTask;

@RestController
@RequestMapping("/manacct/acctmdtdetmod")
public class AcctMdtDetModController {
	@Autowired
	private IMdtDetImpService mdtDetImpService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qryMdtAcctdetInfList",method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctrecord/submit
	public ResponseModel qryMdtAcctdetInf(RequestModel reqModel){
		Map params = reqModel.getPrivateCtx();
		String planId = (String) params.get("planId");
		String opDate = (String) params.get("opDate");
		String amt = (String) params.get("amt");
		String oppAcctName = (String) params.get("oppAcctName");
		String direct = (String) params.get("direct");
		String beginNum = (String) params.get("beginNum");
		String fetchNum = (String) params.get("fetchNum");
		List<MdtAcctdetInfModel> ret =  mdtDetImpService.qryMdtAcctdetInfList(planId, opDate, amt, oppAcctName, 
				direct, Integer.parseInt(beginNum), Integer.parseInt(fetchNum));
		ResponseModel responseModel = new ResponseModel(State.SUCC, "成功", ret);
		return responseModel;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/sumbit",method = { RequestMethod.POST, RequestMethod.GET })//URL=acct/manacct/acctmdtdetimp/submit
	public String submit(Map params){
		//申请主表参数封装
		AppModel appModel = new AppModel();
		appModel.setAppType("034401");//受托户明细导入
		appModel.setAppState("14");
		//module=5402
		appModel.setBakFlag("1");
		
//		//交易主表参数封装
//		TrdModel trdModel = new TrdModel(params);
//		trdModel.setTrdNo("");
//		trdModel.setTrdType("034401");
//		trdModel.setTrdState("");
		
		//dataservice文件上传 解析 入库 opm_mdt_acctdet_trd受托户明细交易表 返回trdNo
		String fileNo = "";//dataservice返回
		String appNo = "";//dataservice返回
		HashMap map = new HashMap();
		map.put("fileNo", fileNo);
		map.put("appNo", appNo);
		map.put("trdType", "034401");
		List<HashMap> trdList = new ArrayList<HashMap>();
		trdList.add(map);
		//提交申请
		CommonBusiness mdtDetImpBusiness = new CommonBusiness();
		RetResult brr = mdtDetImpBusiness
			.register(AppAudTask.class, appModel) //提交申请 //操作标记
//			.register(TrdTask.class, trdModel) //保存交易主表 //TODO Q：交易公共Trade方法实现？
			.register(MdtDetImpTrdPreTask.class, fileNo) //受托户明细导入数据准备  校验  申请号和交易号 //TODO Q：需要取到保存申请主表的AppNo和保存交易主表的TrdNo
			.register(AppTrdRelTask.class, trdList) //申请号和交易号 //TODO Q：需要取到保存申请主表的AppNo和保存交易主表的TrdNo
		.doBusiness();
		return (String)brr.getReturnObj();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/audit",method = { RequestMethod.POST, RequestMethod.GET })
	public String audit(Map params){
		//参数封装
		AppModel appModel = new AppModel(params);
		//参数封装
//		TrdModel trdModel = new TrdModel(params);
		
		CommonBusiness netValueBusiness = new CommonBusiness();//TODO Q：回滚时需要保存每一Trade的Context，因为同一个key值可能在不同的Trade中发生变化
		RetResult brr = netValueBusiness
			.register(AppAudTask.class, appModel, AppChannel.class) //审批申请 //操作标记 //根据申请状态写Channel //TODO Q：Channel是在Trade执行完成后执行？
			.register(MdtDetImpTrdDoTask.class, null) //保存信息表 //TODO Q：和保存交易明细合并为一个Trade(一个保存trd表 + 一个inf表)
//			.register(TrdTask.class, trdModel) //更新交易主表 //TODO Q：公共交易主表Trade方法实现？
			//.register(AppTrdRelTask.class, null) //申请号和交易号 //TODO Q：需要取到保存申请主表的AppNo和保存交易主表的TrdNo
		.doBusiness();
		return (String)brr.getReturnObj();
	}
}
