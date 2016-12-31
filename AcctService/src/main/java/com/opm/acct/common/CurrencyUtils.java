package com.opm.acct.common;

import java.math.BigDecimal;

public class CurrencyUtils {

	/**
	   * double ���
	   * 
	   * @param d1
	   * @param d2
	   * @return
	   */
	  public static double sumDouble(String d1, String d2) {
	    BigDecimal bd1 = new BigDecimal(d1);
	    BigDecimal bd2 = new BigDecimal(d2);
	    return bd1.add(bd2).doubleValue();
	  }
	  /**
	   * double ���
	   * 
	   * @param d1
	   * @param d2
	   * @return
	   */
	  public static double subDouble(String d1, String d2) {
	    BigDecimal bd1 = new BigDecimal(d1);
	    BigDecimal bd2 = new BigDecimal(d2);
	    return bd1.subtract(bd2).doubleValue();
	  }

	  /**
	   * double �˷�
	   * 
	   * @param d1
	   * @param d2
	   * @return
	   */
	  public static double mulDouble(String d1, String d2) {
	    BigDecimal bd1 = new BigDecimal(d1);
	    BigDecimal bd2 = new BigDecimal(d2);
	    return bd1.multiply(bd2).doubleValue();
	  }

	  /**
	   * double ����
	   * 
	   * @param d1
	   * @param d2
	   * @param scale �������� С����λ��
	   * @return
	   */
	  public static double divDouble(String d1, String d2, int scale) {
	    //  ��Ȼ�ڴ�֮ǰ����Ҫ�жϷ�ĸ�Ƿ�Ϊ0��   
	    //  Ϊ0����Ը���ʵ����������Ӧ�Ĵ��� 
	    BigDecimal bd1 = new BigDecimal(d1);
	    BigDecimal bd2 = new BigDecimal(d1);
	    return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	  }

}
