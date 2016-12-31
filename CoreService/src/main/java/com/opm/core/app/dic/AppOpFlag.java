package com.opm.core.app.dic;

public enum AppOpFlag {
	SAVE("SAVE"),SUBMIT("SUBMIT"),AGREE("AGREE"), DENY("DENY"), CANCEL("CANCEL");
	
	/**
     * 状态对应的值
     */
    private String val;

    /**
     * 默认构造函数
     *
     * @param val 对应的值
     */
	AppOpFlag(String val) {
        this.val = val;
    }

    /**
     * 获取状态值
     *
     * @return 状态的值[参考类型描述]
     */
    public String getVal() {
        return this.val;
    }
    
    @Override
    public String toString() {
    	return this.val;
    }
    
}
