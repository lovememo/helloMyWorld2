package com.opm.common.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kfzx-hanxh on 2016/12/30.
 */
public class SimpleResponseModelTest {
    @Test
    public void checkSuccResponseByNormal() {
        SimpleResponseModel simpleResponseModel = new SimpleResponseModel();
        Assert.assertEquals(SimpleResponseModel.SUCC_FLAG, simpleResponseModel.getFlag());
    }

    @Test
    public void checkSuccResponseByGiveSucdValue() {
        SimpleResponseModel simpleResponseModel = new SimpleResponseModel(SimpleResponseModel.SUCC_FLAG);
        Assert.assertEquals(SimpleResponseModel.SUCC_FLAG, simpleResponseModel.getFlag());
    }

    @Test
    public void checkFailResponse() {
        SimpleResponseModel simpleResponseModel = new SimpleResponseModel(SimpleResponseModel.FAIL_FLAG);
        Assert.assertEquals(SimpleResponseModel.FAIL_FLAG, simpleResponseModel.getFlag());
    }

    @Test
    public void checkWarnResponse(){
        SimpleResponseModel simpleResponseModel = new SimpleResponseModel(SimpleResponseModel.WARN_FLG);
        Assert.assertEquals(SimpleResponseModel.WARN_FLG, simpleResponseModel.getFlag());
    }

    @Test
    public void checkJson() {
        SimpleResponseModel simpleResponseModel = new SimpleResponseModel(SimpleResponseModel.SUCC_FLAG, "hello world");
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(simpleResponseModel));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
