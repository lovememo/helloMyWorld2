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
     * ��ȡ�ƻ����������б�
     * @return �б���Ϣ
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
     * ���Դ���
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
        tmp1.add("OPMְҵ���1");
        rets.add(tmp1);

        List<String> tmp2 = new ArrayList<>();
        tmp2.add("000002");
        tmp2.add("OPMְҵ���2");
        rets.add(tmp2);
    	return new ResponseModel(ResponseModel.State.SUCC, "OK", rets);
    }
//    
    /**
     * ��ȡ�ƻ�������ϸ
     * @param appNo �����
     * @return �������ϸ��Ϣ
     */
    @RequestMapping(value = "/app/det/{appNo}",method = RequestMethod.GET)
    public PlanAppInfModel getPlanAppDet(@PathVariable long appNo){
    	return this.planService.getPlanAppDet(appNo);
    }
    

    /**
     * �ӿ�
     * ���뵱ǰ�ƻ����룬���ع����ƻ��б����ƻ����룬�Ƿ��������ˡ�
     * @param planId �ƻ�����
     * @return �ƻ���Ϣ
     */
    @RequestMapping(value = "/planInf/{planId}",method = RequestMethod.GET)
    public PlanBasicInfoModel getPlanInfByPlanId(@PathVariable("planId") String planId){
    	return this.planService.getPlanInfByPlanId(planId);
    }

    /**
     * ����PIM����������
     *
     * @param reqModel ��������
     * @return ����������Բ����ļƻ���Ϣ[�ƻ����롢�ƻ�����]
     */
    @RequestMapping(value = "/branch/plans", method = {RequestMethod.POST})
    public ResponseModel getOpmPlansByBranchId(RequestModel reqModel) {
        // TODO ��Ҫʵ����ʵ���߼�
        System.out.println(reqModel.getPublicCtx());
        System.out.println(reqModel.getPrivateData());

        List<List<String>> rets = new ArrayList<>();
        List<String> tmp1 = new ArrayList<>();
        tmp1.add("000001");
        tmp1.add("OPMְҵ���1");
        rets.add(tmp1);

        List<String> tmp2 = new ArrayList<>();
        tmp2.add("000002");
        tmp2.add("OPMְҵ���2");
        rets.add(tmp2);

        return new ResponseModel(ResponseModel.State.SUCC, "OK", rets);
    }
}
