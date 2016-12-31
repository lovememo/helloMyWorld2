package com.opm.core.common.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckTool {


	/**
	 * ��Ϊnull�Ҳ�Ϊ��ֵ���򷵻�true
	 * @param s
	 * @return
	 */
	public static boolean isNotNull(String s){
		return s!=null && !"".equals(s.trim());
	}
	
	/**
	 * ��Ϊnull�Ҳ�Ϊ��ֵ���򷵻�true
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s){
		return s==null || "".equals(s.trim());
	}

	/**
	 * 
	 * @param date
	 * @param nullable �Ƿ��Ϊ��
	 * @return
	 */
	public static boolean isDate(String date,boolean nullable){
		if(!nullable && isNull(date)){
			return false;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {
			dateFormat.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	public static boolean isDate(String date){
		return isDate(date,true);
	}
}
