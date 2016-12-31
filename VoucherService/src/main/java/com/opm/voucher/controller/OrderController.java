package com.opm.voucher.controller;


import com.opm.voucher.order.entity.DictateInf;
import com.opm.voucher.order.service.IOrderService;

import java.util.List;
import java.util.Map;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(OrderController.class);
	
	
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/createOrder",method = RequestMethod.GET)
    public boolean createOrder(){
        return this.orderService.createOrder("aa");
    }
    

    /**
     * 接口<br> 指令生成
     * @param dictateInf
     * @return
     */
    @RequestMapping(value = "/insertDictate",method = RequestMethod.GET)
    public int insertDictate(DictateInf dictateInf){
    	dictateInf.setFileFlag("1");//待生成
    	this.orderService.insertDictate(dictateInf);
    	//TODO:net.sf.jxls.transformer.XLSTransformer.transformXLS(InputStream is, Map beanParams)
    	dictateInf.setFileContent(dictateInf.getVal1().getBytes());
    	dictateInf.setFileFlag("2");//已生成
    	return this.orderService.updateDictate(dictateInf);
    }
    
    /**
     * 获取指令列表
     * @param reqModel
     * @return
     */
    @RequestMapping(value = "/getOrderList", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseModel getOrderList(RequestModel reqModel){
    	ResponseModel resModel = null;
		String funcName = "获取指令列表";
		try {
			String orderType = reqModel.getStringValue("orderType");
			String beginDate = reqModel.getStringValue("beginDate");
			String endDate = reqModel.getStringValue("endDate");
			System.out.println("orderType:"+orderType+", beginDate:"+beginDate+", endDate:"+endDate);
			
			List<Map<String, String>> list = orderService.getOrderList(orderType, beginDate, endDate);
			resModel = new ResponseModel(ResponseModel.State.SUCC, funcName + "成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			resModel = new ResponseModel(ResponseModel.State.FAIL, funcName + "失败", null);
		}
		return resModel;
    }
    /**
     * 导出指令报表
     * @param reqModel
     * @return
     */
    @RequestMapping(value = "/exportOrderReport", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseEntity<byte[]> exportOrderReport(@RequestParam(value = "dictateNo") String dictateNo){
		try {
			System.out.println("dictateNo:"+dictateNo);
			DictateInf datateInf = orderService.exportOrderReport(dictateNo);
			String fileName = datateInf.getOrderType();
			System.out.println("fileName:"+fileName);
			fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName+".xls");
            return new ResponseEntity<>(datateInf.getFileContent(), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Excel导出失败！" + e.getMessage());
		}
		return null;
    }
}
