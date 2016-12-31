package com.opm.core.paymanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.core.app.service.IAppService;
import com.opm.core.app.template.ResultMessage;
import com.opm.core.app.template.trade.TradeName;
import com.opm.core.common.model.FileRelApp;
import com.opm.core.common.service.FileService;
import com.opm.core.common.tools.CompareTool;
import com.opm.core.paymanager.dao.IPayDao;
import com.opm.core.paymanager.model.EmpPayNoteStatisticData;
import com.opm.core.paymanager.model.PayTradeConstance.PAY_REPORT_STATE;
import com.opm.core.paymanager.model.PayTradeModel;

@Service
public class PayService {

	@Autowired
	IPayDao payDao;

	@Autowired
	IAppService appService;
	
	@Autowired
	FileService fileService;
	
	public int insertPayTradeInfo(PayTradeModel payTradeModel) {
//		PayTradeModel payTradeModel = (PayTradeModel)param.get(PayTradeConstance.PAY_REPORT_OBJ);
//		payTradeModel.setState(PAY_REPORT_STATE.UNCONFIRM.toString());
		return payDao.insertPayTrade(payTradeModel);
	}

//	public int updatePayReportRecstatus(String tradeNo ,PayTradeConstance.PAY_REPORT_STATE state){
//		return payDao.updatePayReportRecstatus(tradeNo, state);
//	}
	
	public int updatePayReport(PayTradeModel payTradeModel){
		return payDao.updatePayReport(payTradeModel);
	}
	
	public EmpPayNoteStatisticData getStaticPayNoteData(String tradeNo){
		return payDao.getStaticPayNoteData(tradeNo);
	}

//	public String qryTradeNoByFileNo(String fileNo){
//		//先找到文件号对应的申请号
//		FileRelApp fileRelApp = new FileRelApp();
//		fileRelApp.setSerial_no(fileNo);
//		FileRelApp qryFileRel = fileService.qryFileRel(fileRelApp);
//		//再通过申请号找到交易号
//		String qryTrdNo = appService.qryTrdNo(qryFileRel.getApp_no(), TradeName.PAY_TRD);
//		return qryTrdNo;
//		
//	}
	
	public int insertPayTradeDet(String fileNo,String tradeNo) {
		return payDao.insertPayTradeDet(fileNo, tradeNo);
	}

	
	public PayTradeModel queryPayReportByTradeNo(String tradeNo) {
		//先找到trdNo
//		String qryTrdNo = appService.qryTrdNo(payNo, TradeName.PAY_TRD);
//		assert(qryTrdNo != null);
		PayTradeModel payTradeModel = new PayTradeModel();
		payTradeModel.setTrd_no(tradeNo);
		return payDao.queryPayReport(payTradeModel);
	}
	
	/**
	 * 获取正式表的数据
	 * @param payNo
	 * @return
	 */
	public PayTradeModel queryPayReportRecByPayNo(String payNo) {
		return payDao.queryPayReportRecByPayNo(payNo);
	}
	/**
	 * 支付划款登记生效
	 * @param appNo
	 */
	public void payRegisteEffect(String tradeNo){
		//1、将汇总数据插入正式表
		PayTradeModel payTradeModel = queryPayReportByTradeNo(tradeNo);
		payTradeModel.setState(PAY_REPORT_STATE.UNCONFIRM.toString());
		payDao.insertPayReportRec(payTradeModel);
		//2、将明细插入正式表
		int count = payDao.insertEmpPayNoteRecFromTrade(payTradeModel);
		System.out.println("insertEmpPayNoteRecFromTrade count :"+count);
		assert(payTradeModel.getPay_no()!=null);
	}

}
