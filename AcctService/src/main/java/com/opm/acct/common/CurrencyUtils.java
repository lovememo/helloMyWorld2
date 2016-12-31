package com.opm.acct.common;

import java.math.BigDecimal;

public class CurrencyUtils {

	/**
	   * double 相加
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
	   * double 相减
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
	   * double 乘法
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
	   * double 除法
	   * 
	   * @param d1
	   * @param d2
	   * @param scale 四舍五入 小数点位数
	   * @return
	   */
	  public static double divDouble(String d1, String d2, int scale) {
	    //  当然在此之前，你要判断分母是否为0，   
	    //  为0你可以根据实际需求做相应的处理 
	    BigDecimal bd1 = new BigDecimal(d1);
	    BigDecimal bd2 = new BigDecimal(d1);
	    return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	  }

}
