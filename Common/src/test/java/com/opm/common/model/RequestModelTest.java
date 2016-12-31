package com.opm.common.model;

/**
 * Created by kfzx-hanxh on 2016/12/22.
 */
public class RequestModelTest {
    public static void main(String[] args) {
//        RequestModel req = new RequestModel();
//        req.setPublicData("{\"callSrvId\": \"\", \"callInfId\": \"\", \"serialId\": \"\"}");
//        req.setPrivateData("{\"a\":\"b\"}");
//        System.out.println(req);
//        String reqStr = "{privateData='{\"a\":\"b\"}', publicData='{\"callSrvId\": \"\", \"callInfId\": \"\", \"serialId\": \"\"}'}";
//        System.out.println(reqStr);

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            System.out.println(mapper.writeValueAsString(req));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        JsonParser parser = new JsonParser();
//        JsonObject obj = parser.parse(reqStr).getAsJsonObject();
//        JsonObject obj = UtilTools.parseToJson(reqStr).isPresent();

//        Optional<JsonObject> op = UtilTools.parseToJson(reqStr);
//        if(op.isPresent()){
//            System.out.println("is");
//        }else {
//            System.out.println("not");
//        }

//        String reqStr = "";
//        Map<String, Object> map = new HashMap<>();
//        Map<String, String> pubMap = new HashMap<>();
//        pubMap.put("callSrvId", "core");
//        pubMap.put("callInfId", "plan");
//        map.put("publicData", pubMap);
//
//        Map<String, Object> priMap = new HashMap<>();
//        priMap.put("appNos", Arrays.asList("123","456"));
//        map.put("privateData", priMap);
//
//        reqStr = new Gson().toJson(map);
//        System.out.println(reqStr);

        //{"publicData":"{\"cal_srv_id\":\"123\"}","privateData":"{\"appNo\":\"456\"}"}
        String publicData = "{\"cal_srv_id\":\"123\"}";
        String privateData = "{\"appNo\":456, \"planId\"=[\"1\", \"2\"]}";
        RequestModel req = new RequestModel();
//        req.setPublicData("{callInfId:plan, callSrvId:core}");
//        req.setPrivateData("{appNos:[123, 456]}");
        req.setPrivateData(privateData);
        req.setPublicData(publicData);

        System.out.println(req.getPrivateCtx().get("appNo"));
        System.out.println(req.getPublicCtx().get("cal_srv_id"));

        System.out.println("appNo :" + req.getStringValue("appNo"));
        System.out.println("planId :" + req.getListValue("planId"));

//        String reqStr = req.getPrivateData();
//
//        Map<String, Object> rets = req.getPrivateMap();
//        for (String key : rets.keySet()) {
//            System.out.println(key + ":" + rets.get(key));
//        }

//        String json = "{callInfId:plan, callSrvId:core}";
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObj = null;
//        try {
//            jsonObj = parser.parse(json).getAsJsonObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(jsonObj.get("callInfId"));
//        System.out.println(jsonObj.get("callSrvId"));
    }
}
