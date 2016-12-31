package com.opm.core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import com.opm.common.utils.UtilTools;
import com.opm.core.app.dic.AppState;
import com.opm.core.app.model.AppQryParam;
import com.opm.core.app.service.IAppService;
import com.opm.core.plan.model.PlanInfoModle;


/**
 * Created by kfzx-gaoyx on 2016/12/20
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableFeignClients
@RestController
@RequestMapping("/app")
public class AppController {
	@Autowired
	private IAppService appService;

	@RequestMapping(value = "/getAppList",method = {RequestMethod.GET, RequestMethod.POST})
    public List<Object> getAppList(RequestModel reqModel){

		//reqModel.getPrivateData();
		//TODO:华叔提供map转bean的方法。暂时自己转一下
		AppQryParam qryParam=new AppQryParam(reqModel.getPrivateCtx());
		//qryParam.setBeginNum("1");
		//qryParam.setFetchNum("10");
		qryParam.setBeginNum(reqModel.getBegNum());
		qryParam.setFetchNum(reqModel.getFetchNum());
		

		return UtilTools.turnPage(appService.qryAppList(qryParam), Integer.parseInt(appService.qryAppCount(qryParam)));
        //System.out.println(reqModel.getPublicCtx());
    	//System.out.println(reqModel.getPrivateData());
    	//System.out.println(reqModel.getPrivateCtx().get("pim"));
    	
    	
    	
	}
	@RequestMapping(value = "/getAppState",method = {RequestMethod.GET, RequestMethod.POST})
    public List<Map<String,String>> getAppState(){
			return AppState.getList();

			
    	
    	
    	
	}
}
