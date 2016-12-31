package com.opm.core.common.enumdict;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
public enum ReviewType {

    Agree("ͬ��"),
    Save("����"),
    Review("�����");

    private String desc;
    private ReviewType(String desc){
        this.desc = desc;
    }
    
    public String getDesc(){
    	return this.desc;
    }
}
