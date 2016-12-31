package com.opm.core.app.dic;

public enum AppOpFlag {
	SAVE("SAVE"),SUBMIT("SUBMIT"),AGREE("AGREE"), DENY("DENY"), CANCEL("CANCEL");
	
	/**
     * ״̬��Ӧ��ֵ
     */
    private String val;

    /**
     * Ĭ�Ϲ��캯��
     *
     * @param val ��Ӧ��ֵ
     */
	AppOpFlag(String val) {
        this.val = val;
    }

    /**
     * ��ȡ״ֵ̬
     *
     * @return ״̬��ֵ[�ο���������]
     */
    public String getVal() {
        return this.val;
    }
    
    @Override
    public String toString() {
    	return this.val;
    }
    
}
