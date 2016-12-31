package com.proxy;

import com.google.gson.Gson;
import com.opm.common.model.RequestModel;
import com.opm.common.model.ResponseModel;
import org.junit.Test;

/**
 * Created by kfzx-liuyz1 on 2016/12/28.
 */
public class CallbackEntityTest {

    @Test
    public void testCallbackEntity(){
        ResponseModel requestModel = new ResponseModel(ResponseModel.State.FAIL,"HELLO","HELLO");
        Gson gson = new Gson();
        String json = gson.toJson(requestModel);

        ResponseModel res = gson.fromJson(json,ResponseModel.class);
    }


}
