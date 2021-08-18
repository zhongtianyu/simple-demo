package com.sz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * @author wangBo
 * @date 2018年10月11日 上午10:10:44
 */
public class DateUtils {
	
	private static final String CRON_DATE_FORMAT_DAY = "ss mm HH * * ?";
	
	private static final String CRON_DATE_FORMAT_MONTH = "ss mm HH dd * ?";

	private static final String DAY = "day";
	
	private static final String MONTH = "month";
	
	/**
	 * 获取之前月份yyyyMM
	 * @param i 
	 * @return
	 */
	public static String getLastMonth(int i){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(new Date());
		 calendar.add(Calendar.MONTH,-i);
	     Date date= calendar.getTime();
		 return sdf.format(date);
	}
	
	/**
	 * 日期格式校验
	 * @param date
	 * @return
	 */
	public static boolean isDateRule(String date,String dateFormat){
		boolean dateflag=true;
		SimpleDateFormat format=new SimpleDateFormat(dateFormat);
		try
		{ 
			format.setLenient(false);
		    @SuppressWarnings("unused")	   
			Date dateTime = format.parse(date);
		} catch (ParseException e){
		     dateflag=false;
		}
		return dateflag;
	}

	/**
	 * 
	 * @param cron  cron表达式
	 * @param period 执行周期类型 day & month & other
	 * @param interval cron表达式的间隔时间
	 * @return 新的cron表达式
	 */
	public static String cronAddInterval(String cron,String period,long interval)throws Exception{
		
		if(DAY.equals(period)){
			return fmtDateToCron(new Date(fmtCronToDate(cron,CRON_DATE_FORMAT_DAY).getTime()+interval), CRON_DATE_FORMAT_DAY);
    	}else if(MONTH.equals(period)){
    		return fmtDateToCron(new Date(fmtCronToDate(cron,CRON_DATE_FORMAT_MONTH).getTime()+interval), CRON_DATE_FORMAT_MONTH);
    	}else{
    		return cron;
    	}
		
	}
    
    /**
     * cron转日期
     * @param cron
     * @param dtFormat
     * @return
     * @throws ParseException 
     * @throws Exception 
     */
    public static Date fmtCronToDate(final String cron,String dtFormat) throws ParseException{
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
        return sdf.parse(cron);
    }
    
    /**
     * 格式转换 日期-字符串 
     */
    public static String fmtDateToCron(Date date, String dtFormat) throws Exception{
      
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
    }


	/**
	 * 获得指定账期月最后一天
	 * @param settleMonth，格式："201805"
	 * @return
	 */
	public static String getLastDayOfMonth(String settleMonth){
		int month = Integer.parseInt(settleMonth.substring(5));
		int year = Integer.parseInt(settleMonth.substring(0,4));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month-1);

		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);

		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}

	/**
	 * 获得指定账期月第一天
	 * @param settleMonth，格式："201805"
	 * @return
	 */
	public static String getFirstDayOfMonth(String settleMonth){
		int month = Integer.parseInt(settleMonth.substring(5));
		int year = Integer.parseInt(settleMonth.substring(0,4));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month-1);
		//获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);

		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String firstDayOfMonth = sdf.format(cal.getTime());

		return firstDayOfMonth;
	}

	/**
	 * 获取指定月份的所有日期
	 * 如果传入8位日期，则返回该日期
	 * @param month 例如：201806
	 * @return
	 */
	public static List<String> getSettleDateOfMonth(String month){
		List<String> list = new ArrayList<String>();
		if(month.length() == 8)
			list.add(month);
		else{
			String firstDay = getFirstDayOfMonth(month);
			String lastDay = getLastDayOfMonth(month);
			int days = Integer.parseInt(lastDay)-Integer.parseInt(firstDay)+1;

			for(int i = 0 ;i<days;i++){
				int day = Integer.parseInt(firstDay)+i;
				list.add(day+"");
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description: 在当前日期基础上，增加daysToAdd天， 返回yyyyMMdd格式的日期
	 * @author yaoQingCan
	 * @date 2019年10月18日 下午3:20:39
	 * @param daysToAdd 需要增加的天数
	 * @return 增加daysToAdd天之后的日期
	 */
	public static String getPlusDaysWithCurrentDate(long daysToAdd) {
		
		return LocalDate.now().plusDays(daysToAdd)
				.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * 
	 * @Description: 在date日期之后增加daysToAdd天后的日期
	 * @author yaoQingCan
	 * @date 2019年10月18日 下午3:17:56
	 * @param date 当期日期
	 * @param daysToAdd 需求增加的天数
	 * @return 在date日期之后增加daysToAdd天后的日期
	 */
	public static String getPlusDaysByDate(String date,long daysToAdd) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate.plusDays(daysToAdd).format(formatter);
	}
	
	/**
	 * 
	 * @Description:  在当前日期基础上，增加monthsToAdd月， 返回yyyyMMdd格式的日期
	 * @author yaoQingCan
	 * @date 2019年10月18日 下午3:21:30
	 * @param monthsToAdd 需要增加的月数
	 * @return 增加monthsToAdd月之后的月份 
	 */
	public static String getPlusMonthsWithCurrentMonth(long monthsToAdd) {
		
		return LocalDate.now().plusMonths(monthsToAdd)
				.format(DateTimeFormatter.ofPattern("yyyyMM"));
	}
	
	
	/**
	 * 
	 * @Description: 在date日期之后增加monthsToAdd月后的月份
	 * @author yaoQingCan
	 * @date 2019年10月18日 下午3:28:22
	 * @param yyyyMMFormatValue 当期月份
	 * @param monthsToAdd  需要增加的月数
	 * @return
	 * @throws ParseException 
	 */
	public static String getPlusMonthsByMonth(String yyyyMMFormatValue,long monthsToAdd) throws ParseException {
		
		LocalDate localDate =  parseToyyyyMM(yyyyMMFormatValue);
		return localDate.plusMonths(monthsToAdd)
				.format(DateTimeFormatter.ofPattern("yyyyMM"));

	}
	

	/**
	 * 
	 * @Description: 将yyyyMMdd格式字符串转为LocalDate
	 * @author yaoQingCan
	 * @date 2019年10月21日 上午10:24:12
	 * @param yyyyMMddFormatValue yyyyMMdd格式字符串
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate parseToyyyyMMdd(String yyyyMMddFormatValue) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate =LocalDate.parse(yyyyMMddFormatValue, formatter);
		return localDate;

	}
	
	/**
	 * 
	 * @Description: 将yyyyMM格式字符串转为LocalDate
	 * @author yaoQingCan
	 * @date 2019年10月21日 上午10:24:52
	 * @param yyyyMMFormatValue yyyyMM格式字符串
	 * @return
	 * @throws ParseException
	 */
	public static LocalDate parseToyyyyMM(String yyyyMMFormatValue) throws ParseException {
		
		StringBuilder stringBuilder =new StringBuilder(yyyyMMFormatValue);
		return parseToyyyyMMdd(stringBuilder.append("01").toString());

	}
	
		/**
	 * 获取非当前时间
	 * 
	 * @param date
	 *            指定日期，为null时，则取当前日期
	 * @param pastDays
	 *            当前时间相隔天数，可为正数可为负数
	 * @param format
	 *            时间格式
	 * @return String
	 */
	public static String getNonCurrentDate(Date date, int pastDays, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		if (null == date){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, pastDays);
		date = calendar.getTime();
		return df.format(date);
	}
}
