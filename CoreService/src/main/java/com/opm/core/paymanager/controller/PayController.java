package com.opm.core.paymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.model.ResponseModel.State;
import com.opm.core.app.model.AppModel;
import com.opm.core.app.template.AppParam;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.core.IAppOpAction.AUD_ACTION;
import com.opm.core.app.template.core.IAppOpAction.SUMBIT_ACTION;
import com.opm.core.paymanager.app.PayApp;
import com.opm.core.paymanager.app.PayConfirmApp;
import com.opm.core.paymanager.app.PayResultRegisteApp;
import com.opm.core.paymanager.model.PayTradeModel;
import com.opm.core.paymanager.service.PayService;

@RestController
@RequestMapping("/paymanager")
public class PayController {

	@Autowired
	PayApp payApp;// 支付划款导入申请

	@Autowired
	PayConfirmApp payConfirmApp;// 支付划款确认申请

	@Autowired
	PayResultRegisteApp payResutRegisteApp;// 支付结果登记申请

	@Autowired
	PayService payService;

	/**
	 * 支付划款登记提交
	 * 
	 * @param requestModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registe/sumbit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel registeSubmit(RequestModel requestModel) {
		System.out.println("RequestModel:" + requestModel);
		System.out.println("requestModel.getPrivateCtx();:" + requestModel.getPrivateCtx());

		// Map<String, Object> privateCtx = requestModel.getPrivateCtx();
		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		ResultMessage ret = payApp.submit(SUMBIT_ACTION.ASYNC_SUBMIT, appParam);

		ResponseModel responseModel = new ResponseModel(ret.isRight() ? State.SUCC : State.FAIL, ret.getMsg(), ret);
		return responseModel;
	}

	/**
	 * 支付划款登记的回调函数，由文件处理测调用
	 * 
	 * @param requestModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registe/callback", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel registeSubmitCallback(RequestModel requestModel) {
		System.out.println("RequestModel:" + requestModel);
		System.out.println("requestModel.getPrivateCtx();:" + requestModel.getPrivateCtx());
		
		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		payApp.submit(SUMBIT_ACTION.ASYNC_CALLBACK, appParam);

		// payApp.registeSubmitCallback(requestModel.getStringValue("fileNo"));

		ResponseModel responseModel = new ResponseModel(State.SUCC, "OK", null);
		return responseModel;
	}

	/**
	 * 支付划款登记的回调函数，由文件处理测调用
	 * 
	 * @param requestModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registe/callback1", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel registeSubmitCallback1(Object str) {

		AppModel appModel = new AppModel();
		AppParam appParam = new AppParam(appModel);
		RequestModel requestModel = new RequestModel();
		requestModel.getPrivateCtx().put("serialNo", "628");
		appParam.put("requestModel", requestModel);
		payApp.submit(SUMBIT_ACTION.ASYNC_CALLBACK, appParam);
		ResponseModel responseModel = new ResponseModel(State.SUCC, "OK", null);
		return responseModel;
	}

	/**
	 * 支付划款登记提交，测试版
	 * 
	 * @param requestModel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/registe/sumbit1", method = { RequestMethod.POST, RequestMethod.GET })
	public String submit1(Object obj) {
		AppModel appModel = new AppModel();
		appModel.setAppUser("xiaoyang");
		appModel.setPlanId("000001");
		AppParam appParam = new AppParam(appModel);
		ResultMessage ret = payApp.submit(SUMBIT_ACTION.SUBMIT, appParam);

		return ret.getMsg();

	}

	/**
	 * 支付划款登记复核
	 * 
	 * @param requestModel
	 * @return
	 */
	@RequestMapping(value = "/registe/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel audit(RequestModel requestModel) {
		System.out.println("RequestModel:" + requestModel);
		// AppParam appParam = new AppParam(params);
		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		ResultMessage ret = payApp.aud(AUD_ACTION.AGREE, appParam);

		ResponseModel responseModel = new ResponseModel(ret.isRight() ? State.SUCC : State.FAIL, ret.getMsg(), ret);
		return responseModel;
	}

	/**
	 * 支付登记信息查询
	 * 
	 * @param requestModel
	 * @return
	 */
	@RequestMapping(value = "/query/payreport", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel queryPayReport(RequestModel requestModel) {
		// String appNo = requestModel.getStringValue("appNo");
		String payNo = requestModel.getStringValue("payNo");
		PayTradeModel queryPayReport = payService.queryPayReportRecByPayNo(payNo);
		System.out.println("queryPayReport:" + queryPayReport);

		return new ResponseModel(State.SUCC, "ok", queryPayReport);
	}

	/**
	 * 支付划款确认申请提交
	 * 
	 * @param requestModel
	 * @return
	 */
	@RequestMapping(value = "/confirm/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel comfirmAppSubmit(RequestModel requestModel) {

		System.out.println("RequestModel:" + requestModel);

		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		ResultMessage ret = payConfirmApp.submit(SUMBIT_ACTION.SUBMIT, appParam);

		return new ResponseModel(State.SUCC, "ok", ret);
	}

	/**
	 * 支付划款确认申请复核
	 * 
	 * @param requestModel
	 * @return
	 */
	@RequestMapping(value = "/confirm/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel comfirmAppAudit(RequestModel requestModel) {
		System.out.println("RequestModel:" + requestModel);

		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		ResultMessage ret = payConfirmApp.aud(AUD_ACTION.AGREE, appParam);

		return new ResponseModel(State.SUCC, "ok", ret);
	}

	/**
	 * 支付结果登记申请提交
	 * 
	 * @param requestModel
	 * @return
	 */
	@RequestMapping(value = "/resultreg/submit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel resultRegisteAppSubmit(RequestModel requestModel) {

		System.out.println("RequestModel:" + requestModel);

		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		ResultMessage ret = payResutRegisteApp.submit(SUMBIT_ACTION.SUBMIT, appParam);

		return new ResponseModel(State.SUCC, "ok", ret);
	}

	/**
	 * 支付结果登记申请复核
	 * 
	 * @param requestModel
	 * @return
	 */
	@RequestMapping(value = "/resultreg/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseModel resultRegisteAppAudit(RequestModel requestModel) {
		System.out.println("RequestModel:" + requestModel);

		AppParam appParam = new AppParam();
		appParam.put("requestModel", requestModel);
		ResultMessage ret = payResutRegisteApp.aud(AUD_ACTION.AGREE, appParam);

		return new ResponseModel(State.SUCC, "ok", ret);
	}

}
