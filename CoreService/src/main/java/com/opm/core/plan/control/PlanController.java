package com.opm.core.plan.control;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opm.common.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opm.common.model.RequestModel;
import com.opm.core.plan.model.PlanAppInfModel;
import com.opm.core.plan.model.PlanBasicInfoModel;
import com.opm.core.plan.model.PlanInfoModle;
import com.opm.core.plan.service.IPlanService;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableFeignClients
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @RequestMapping(value = "/getPlanBasicInfo/{planId}",method = RequestMethod.GET)
    public PlanInfoModle getPlanBasicInfo(@PathVariable String planId){
        return this.planService.getPlanBasicInfo(planId);
    }
    
    @RequestMapping(value = "/app/saves",method = RequestMethod.GET)
    public String getSavedPlans(){
    	return  "[{\"totalNum\":\"1\"},{\"appNo\":\"123\"}]";
    }
    
    /**
     * 获取计划复核申请列表
     * @return 列表信息
     */
    @RequestMapping(value = "/app/lists",method = RequestMethod.GET)
    public List<Map<String,String>> getPlans(HttpServletRequest request, HttpServletResponse response, String publicData, String privateData){
//    	System.out.println("publicData :" + publicData);
//    	System.out.println("privateData :" + privateData);
//    	
//    	Enumeration it = request.getParameterNames();
//		while(it.hasMoreElements()){
//			String name = it.nextElement().toString();
//			String value = request.getParameter(name);
//			
//			System.out.println("name:"+name + ", value:"+ value);
//		}
		
    	return this.planService.getPlanApplys("", "", "");
    }

    /**
     * 测试代码
     * @param reqModel
     * @return
     */
    @RequestMapping(value = "/app/lists1",method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseModel getPlans(RequestModel reqModel){
        System.out.println(reqModel.getPublicCtx());
    	System.out.println(reqModel.getPrivateData());
    	System.out.println(reqModel.getPrivateCtx().get("pim"));
//    	Map<String, String> rets = new HashMap<>();
//    	rets.put("flag", "0");
//    	rets.put("msg", "success");
//    	//return this.planService.getPlanApplys("", "", "");
//        Arrays.asList(rets);

        List<List<String>> rets = new ArrayList<>();
        List<String> tmp1 = new ArrayList<>();
        tmp1.add("000001");
        tmp1.add("OPM职业年金1");
        rets.add(tmp1);

        List<String> tmp2 = new ArrayList<>();
        tmp2.add("000002");
        tmp2.add("OPM职业年金2");
        rets.add(tmp2);
    	return new ResponseModel(ResponseModel.State.SUCC, "OK", rets);
    }
//    
    /**
     * 获取计划申请明细
     * @param appNo 申请号
     * @return 申请的明细信息
     */
    @RequestMapping(value = "/app/det/{appNo}",method = RequestMethod.GET)
    public PlanAppInfModel getPlanAppDet(@PathVariable long appNo){
    	return this.planService.getPlanAppDet(appNo);
    }
    

    /**
     * 接口
     * 传入当前计划编码，返回关联计划列表，含计划编码，是否主受托人。
     * @param planId 计划编码
     * @return 计划信息
     */
    @RequestMapping(value = "/planInf/{planId}",method = RequestMethod.GET)
    public PlanBasicInfoModel getPlanInfByPlanId(@PathVariable("planId") String planId){
    	return this.planService.getPlanInfByPlanId(planId);
    }

    /**
     * 处理PIM过来的请求
     *
     * @param reqModel 请求数据
     * @return 请求机构可以操作的计划信息[计划编码、计划名称]
     */
    @RequestMapping(value = "/branch/plans", method = {RequestMethod.POST})
    public ResponseModel getOpmPlansByBranchId(RequestModel reqModel) {
        // TODO 需要实现真实的逻辑
        System.out.println(reqModel.getPublicCtx());
        System.out.println(reqModel.getPrivateData());

        List<List<String>> rets = new ArrayList<>();
        List<String> tmp1 = new ArrayList<>();
        tmp1.add("000001");
        tmp1.add("OPM职业年金1");
        rets.add(tmp1);

        List<String> tmp2 = new ArrayList<>();
        tmp2.add("000002");
        tmp2.add("OPM职业年金2");
        rets.add(tmp2);

        return new ResponseModel(ResponseModel.State.SUCC, "OK", rets);
    }
}
