package com.opm.acct.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class CommonUtils {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommonUtils.class);
	
	public static long NANO_ONE_SECOND = 1000;
    public static long NANO_ONE_MINUTE = 60 * NANO_ONE_SECOND;
    public static long NANO_ONE_HOUR = 60 * NANO_ONE_MINUTE;
    public static long NANO_ONE_DAY = 24 * NANO_ONE_HOUR;
    
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_FORMAT_SHORT = "yyyyMMdd";
    public static String TIME_FORMAT = "hhmmss";

	
	/**
	 * 比较日期   date1 大于 date2 返回1,等于返回0,小于返回-1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String compareDate (String date1, String date2) {
		try {
			Date t1 = parser(date1,DATE_FORMAT);
			Date t2 = parser(date2,DATE_FORMAT);
			
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			
			c1.setTime(t1);
			c2.setTime(t2);
			
			int result = c1.compareTo(c2);
			if (result > 0) {
				return CommonConstant.COMPARE_GREAT;
			} else if (result == 0) {
				return CommonConstant.COMPARE_EQUAL;
			} else {
				return CommonConstant.COMPARE_LESS;
			}
			
		} catch (ParseException e) {
			LOGGER.error("compareDateGreater 日期比较异常:date1="+date1+",date2="+date2,e);
			return  CommonConstant.RETMSG_ERROR;
		}
	
	}
	
	
	/**
	 * 时间比较 time1 大于 time2 返回1,等于返回0,小于返回-1
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static String compareTime (String time1, String time2) {
		try {
			Date d1 = parser(time1,TIME_FORMAT);
			Date d2 = parser(time2,TIME_FORMAT);
			
			int t1 = Integer.parseInt(d1.toString());
			int t2 = Integer.parseInt(d2.toString());
			
			if (t1 > t2) {
				return CommonConstant.COMPARE_GREAT;
			} else if (t1 == t2) {
				return CommonConstant.COMPARE_EQUAL;
			} else {
				return CommonConstant.COMPARE_LESS;
			}
			
		} catch (ParseException e) {
			LOGGER.error("compareTime 时间比较异常:date1="+time1+",date2="+time2,e);
			return  CommonConstant.RETMSG_ERROR;
		}
	
	}


	/**
	 * 获取当前日期所在月份最后一天日期
	 * @param date
	 * @return
	 */
	public static String getLastDateOfMonth (String date) {
		String endDate = "";
		try {
			Date t1 = parser(date,DATE_FORMAT);
			Calendar cal = Calendar.getInstance();
			cal.setTime(t1);
			
			int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
			endDate = t1.toString().substring(0, 8) + day;
            return endDate;
			
		} catch (ParseException e) {
			LOGGER.error("获取当前日期所在月份最后一天日期异常:date="+date);
			return  CommonConstant.RETMSG_ERROR;
		}
	
	}
	
	/**
     * 计算两个日期之间相差的天数
     * 
     * @param startDateStr 
     * @param endDateStr 
     * @return
     */
    public static String getDaysBetween2Date(String startDateStr, String endDateStr) {
    	String mills = getDifferenceMillis(startDateStr, endDateStr, DATE_FORMAT);
    	if (!CommonConstant.RETMSG_ERROR.equals(mills)) {
    		return new Long(Long.parseLong(mills)
                    / (NANO_ONE_DAY)).intValue() + "";
    	}
    	return mills;
        
    }

    /**
     * 计算两个日期之间相差的的毫秒数
     * 
     * @param startDateStr
     * @param endDateStr
     * @param dateFormat
     * @return
     */
    public static String getDifferenceMillis(String startDateStr, String endDateStr,
            String dateFormat) {
        try {
            return getDifferenceMillis(parser(startDateStr, dateFormat),
                    parser(endDateStr, dateFormat));
        } catch (ParseException e) {
        	LOGGER.error("计算两个日期之间相差的的毫秒数异常:startDateStr="+startDateStr+" ,endDateStr="+endDateStr,e);
            return  CommonConstant.RETMSG_ERROR;
        }
    }
    
    /**
     * 计算两个日期之间相差的的毫秒数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDifferenceMillis(Date startDate, Date endDate) {
        return Math.abs(endDate.getTime() - startDate.getTime())+"";
    }
    /**
     * 将一个符合指定格式的字串解析成日期型
     * 
     * @param aDateStr
     * @param formatter
     * @return
     * @throws ParseException
     */
    public static Date parser(String aDateStr, String formatter) throws ParseException {
        if (StringUtils.isEmpty(aDateStr)) {
        	 return null;
        }
        Date date = null;
        if ("-".indexOf(aDateStr) < 0) {
        	
        	date = new SimpleDateFormat(DATE_FORMAT_SHORT).parse(aDateStr);
        } else {
        	date = new SimpleDateFormat(formatter).parse(aDateStr);
        }
        //统一为"YYYY-MM-DD"格式，再返回Date对象
        String dateStr = new SimpleDateFormat(formatter).format(date);
        return new SimpleDateFormat(formatter).parse(dateStr);

    }
   
    /**
     * 为一个日期加上或减去指定天数
     * 
     * @param date
     * @param dayCounts 天数
     * @return
     */
    public static String addDays(String date, int dayCounts) {
         try {
    	        return new SimpleDateFormat(DATE_FORMAT).format(
    	        		addTime(new SimpleDateFormat(DATE_FORMAT).parse(date),
    	        				Calendar.DAY_OF_YEAR, dayCounts));
    	        
		} catch (ParseException e) {
			LOGGER.error("为一个日期加上或减去指定天数异常:date="+date+" ,amount="+dayCounts,e);
            return CommonConstant.RETMSG_ERROR;
		}
    }
    
    /**
     * 为指定日期，指定日期格式，加上或减去指定天数
     * @param aDate
     * @param timeType
     * @param amount
     * @return
     */
    public static Date addTime(Date aDate, int timeType, int amount) {
        if (aDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(timeType, amount);
        return cal.getTime();
    }
 
    /**
     * 封装返回信息
     * @param retCode
     * @param retMsg
     * @return
     */
    public static List<Object> getRespList(String retCode, String retMsg) {
    	List<Object> resp = new ArrayList<Object>();
    	resp.add(retCode);
    	resp.add(retMsg);
    	return resp;
   }
    
    /**
     * 获取受托户科目的所属类型（资产or 负债....）
     * subjectClass 1 表示资产
                    2 表示负债
                    3 表示收入
                    4 表示费用
                    5 表示所有者权益
     * @param mdtSubjectType
     * @return
     */
    public static String getMdtSubjectClass(String mdtSubjectType) {
    	if (StringUtils.isEmpty(mdtSubjectType)) {
    		LOGGER.error("获取受托户科目的所属类型（资产or 负债....）失败:mdtSubjectType为空,mdtSubjectType="+mdtSubjectType);
    		return CommonConstant.RETMSG_ERROR;
    	}
    	if ("1002".equals(mdtSubjectType) || "1204".equals(mdtSubjectType)) {
    		return "1";
    	} else if ("2207".equals(mdtSubjectType) || "2210".equals(mdtSubjectType) || 
    			"2211".equals(mdtSubjectType) || "2221".equals(mdtSubjectType) ||
    			"2241".equals(mdtSubjectType.substring(0, 4))) {
    		return "2";
    	} else if ("6011".equals(mdtSubjectType)) {
    		return "3";
    	} else if ("6404".equals(mdtSubjectType) || "6405".equals(mdtSubjectType) || 
    			"6605".equals(mdtSubjectType) ) {
    		return "4";
    	} else if ("4001".equals(mdtSubjectType) || "4103".equals(mdtSubjectType) || 
    			"4104".equals(mdtSubjectType) ) {
    		return "5";
    	} else {
    		LOGGER.error("获取受托户科目的所属类型（资产or 负债....）失败:未找到所属类型,mdtSubjectType="+mdtSubjectType);
    		return CommonConstant.RETMSG_ERROR;
    	}
   }
    /**
     * 获取当前日期 yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }
    /**
     * 为一个日期加上指定月数
     * 
     * @param aDate
     * @param amount 月数
     * @return
     */
    public static final String addMonths(String date, int amount) {
        try {
        	 return new SimpleDateFormat(DATE_FORMAT).format(
 	        		addTime(new SimpleDateFormat(DATE_FORMAT).parse(date),
 	        				Calendar.MONTH, amount));
			//return addTime(parser(date,DATE_FORMAT), Calendar.MONTH, amount).toString();
		} catch (ParseException e) {
			LOGGER.error("为一个日期加上指定月数异常:date="+date+" ,amount="+amount,e);
            return CommonConstant.RETMSG_ERROR;
		}
    }

    public static final boolean isLastDayOfMon (String date) {
    	if (CommonConstant.COMPARE_EQUAL.equals(
				CommonUtils.compareDate(date, 
						CommonUtils.getLastDateOfMonth(date)))) {
			return true;
		}
    	return false;
    }
    

    /**
     * 获取输入日期所在年份天数
     * 
     * @param aDate
     * @return
     */
    public static final int getDaysInYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            //闰年的判断规则   
            return 366;
        } else {
            return 365;
        }
    }
    

    /**
     * 字符串转数组
     * @param tmpStr
     * @param separator
     * @return
     */
    public static final List<String> getArrayFromStr(String tmpStr, String sep) {
    	List<String> objData = new ArrayList<String>();
    	if (StringUtils.isEmpty(tmpStr)) {
    		return objData;
    	}
    	int i = 0;
    	int pos = 0;
    	
    	i = tmpStr.indexOf(sep);
    	if (i < 0) {
    		objData.add(tmpStr);
    		return objData;
    	}
    	objData.add(tmpStr.substring(pos, i));
    	pos = i + 3;
    	while (tmpStr.indexOf(sep,pos) > 0) {
    		i = tmpStr.indexOf(sep, pos);
    		objData.add(tmpStr.substring(pos, i));
    		pos = i+3;
    	}
    	objData.add(tmpStr.substring( pos, tmpStr.length() ));
    	return objData;
    }

}
