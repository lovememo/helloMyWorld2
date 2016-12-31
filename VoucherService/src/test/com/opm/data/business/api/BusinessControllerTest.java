package com.opm.data.business.api;

import com.opm.common.business.param.RetResult;
import com.opm.common.business.param.TaskParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kfzx-liuyz1 on 2016/12/1.
 */
public class BusinessControllerTest {

    public void sendPost(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            //String requestBody = "{\"statename\":\"" + "aaa" + "\"}";
            TaskParam taskParam = new TaskParam("aaa","bbb");
            HttpEntity request = new HttpEntity(taskParam, headers);
            RetResult result = restTemplate.postForObject("http://localhost:8084/testbusiness", request, RetResult.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendPost1(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            //String requestBody = "{\"statename\":\"" + "aaa" + "\"}";
            TaskParam taskParam = new TaskParam("aaa","bbb");
            HttpEntity request = new HttpEntity(taskParam, headers);
            RetResult result = restTemplate.postForObject("http://localhost:8084/test", request, RetResult.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        new BusinessControllerTest().sendPost();
    }
}
