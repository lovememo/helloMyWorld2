package com.opm.core.common.enumdict;

/**
 * Created by kfzx-liuyz1 on 2016/10/24.
 */
public enum ReviewType {

    Agree("Õ¨“‚"),
    Save("±£¥Ê"),
    Review("¥˝…Û∫À");

    private String desc;
    private ReviewType(String desc){
        this.desc = desc;
    }
    
    public String getDesc(){
    	return this.desc;
    }
}
