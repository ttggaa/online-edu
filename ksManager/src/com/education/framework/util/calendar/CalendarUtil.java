/**
 * CalendarUtil.java
 *
 * 类 名 称：CalendarUtil
 * 功    能：日期操作类
 * 
 * 版 本		   创 建 日 期		  作 者		   部 门		   创 建 类 容    
 * Ver1.0      July 7, 2009       lidy        Edufe:TD       新 建
 * 
 * Copyright(c) 2009---2014 DongCaiKeJi All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.education.framework.util.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 类名：CalendarUtil
 * 功能: 日期操作类
 *  
 * @author   lidy
 * @Version  Ver1.0
 */
public class CalendarUtil {

	public CalendarUtil(){
		
	}
	
	public static String getMonth(){
		return dateToString(new Date(),"yyyy-MM");
	}
	/**
	 * 日期转化成星期 
	 * @return  返回星期几
	 */    
	public static String getWeek(String date){
		String week= "";//星期
		if(null==date && "".equals(date)){
			return week;
		}
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		week = dateToString(getMonthWeek(year,month,day));
		return week;
	}
	/**
	 * 日期转化成星期 
	 * @return  返回星期几
	 */    	
	public static String getWeekDate(String date){
		String week= "";//星期
		if(null == date && "".equals(date)){
			return week;
		}
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		week = dateToStringOther(getMonthWeek(year,month,day));
		return week;
	}
	
	/**
	 * 根据第几周 得到本周第一天和最后一天的日期
	 * @return  返回一周的第一天和 一周的最后一天
	 */  
	public static String[] getMondaySunDay(int year,int week) {
		Calendar   calendar=Calendar.getInstance(); 
		String[] dateStr = new String[2];
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week); 
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		dateStr[0] = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week+1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dateStr[1] = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return dateStr;
	}
	
	/**
	 * 根据 周数和星期 得到日期
	 * @return  返回周数的日期
	 */ 	
	public static String getDateByWeek(int year,int week,int weekDay){
		Calendar   calendar=Calendar.getInstance(); 
		if(weekDay == 8){
			week = week +1;
			weekDay = 1;
		}
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week); 
		calendar.set(Calendar.DAY_OF_WEEK, weekDay);//weekDay 0,1,2,3,4,5,6
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	
	/**
	 * 判断是年的第几周
	 * @return  返回日期是一年中的第几周
	 */ 		
	public static int getWeekOfYear(String year,String month,String day){
		int weekOfYear = 1;
		Calendar calendar=Calendar.getInstance();   
		calendar.set(Calendar.YEAR, Integer.parseInt(year)); //
		calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		calendar.set(Calendar.DATE, Integer.parseInt(day));
		weekOfYear=calendar.get(Calendar.WEEK_OF_YEAR);
		return weekOfYear;
	}
	
	/**
	 * 周List，指定年共有多少周，每周周一数据List
	 * @throws Exception 
	 */
	public static List<String> getYearWeekList(String year) {
		List<String> list = new ArrayList<String>();
		int tYear;
		tYear = strToInt(year);
		int maxDay = getYearMaxDay(tYear);
		for(int i=1;i<=maxDay;i+=7){
			String weekStr = getWeekArr(tYear+"-01-"+i)[0];//周一
			list.add(weekStr);
		}
		return list;
	}
	
	/**
	 * 
	 * 功能说明: 取指定 年月 的最大天数
	 * 
	 * @param :
	 *           
	 * @return: int:
	 */
	@SuppressWarnings("static-access")
	public static int getYearMaxDay(int year) {
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, year); 
		return cpcalendar.getActualMaximum(cpcalendar.DAY_OF_YEAR);
	}
	
	/**
	 * 
	 * 功能说明: 取得当前日期
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	

	/**
	 * 
	 * 功能说明: 取得当前日期
	 */
	public static String getCurrentDateAll() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 
	 * 功能说明: 按指定格式 取得当前日期
	 */
	public static String getCurrentDate(String pattern) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 功能说明： 字符串转日期类型
	 * @param date
	 * @param pattren
	 * @return
	 */
	public static Date dateFormat(String date,String pattren){
		if(null == date || "".equals(date) || "null".equalsIgnoreCase(date)) return null;
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattren);
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public static int compareNow(String date,String pattren){
		if(null == date || "".equals(date) || "null".equalsIgnoreCase(date)) return -1;
		Date d = null;
		Date now = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattren);
		try {
			d = sdf.parse(date);
			now = sdf.parse(getCurrentDate(pattren));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return d.compareTo(now);
	}
	/**
	 * 
	 * 功能说明:
	 *		取得周LIST数据
	 *@param :
	 *@return:
	 *@throws:
	 *@since :
	 */
	public static List<CalendarBO> getWeekList(int tYear,int tMonth, int tDay) {
		int week = getMonthWeek(tYear,tMonth,tDay);
		int day = 0;
		List<CalendarBO> list = new ArrayList<CalendarBO>();
		CalendarBO bo = null;
		for(int i=1;i<=7;i++){
			if(week >= i){
				day = tDay-(week - i) + 1;
			}else{
				day = tDay + (i - week) + 1;
			}
			bo = new CalendarBO();
			bo.setDate(getDay(tYear,tMonth,day));
			bo.setWeek(String.valueOf(getMonthWeek(tYear,tMonth,day)));
			list.add(bo);
		}
		return list;
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得周LIST数据
	 *@param :
	 *@return:
	 *@throws:
	 *@since :
	 */
	public static List<CalendarBO> getWeekList(String year,String Month, String Day) {
		int tYear = strToInt(year);
		int tMonth = strToInt(Month);
		int tDay = strToInt(Day);
		int week = getMonthWeek(tYear,tMonth,tDay);
		int day = 0;
		if(week==1) tDay = tDay-7;
		List<CalendarBO> list = new ArrayList<CalendarBO>();
		CalendarBO bo = null;
		for(int i=1;i<=7;i++){
			if(week >= i){
				day = tDay-(week - i) + 1;
			}else{
				day = tDay + (i - week) + 1;
			}
			bo = new CalendarBO();
			//String daystr = getDay(tYear,tMonth,day);
//			String tempDaystr = daystr.substring(0, 4) + "<br>" + daystr.substring(6, 10);
			bo.setDate(getDay(tYear,tMonth,day));
//			bo.setDate(tempDaystr);
			bo.setWeek(String.valueOf(getMonthWeek(tYear,tMonth,day)));
			list.add(bo);
		}
		return list;
	}
	
	/**
	 * 
	 * 功能说明:取得周LIST数据
	 *@param :具体的某一天 格式2008-7-29
	 *@return:周列表
	 *@throws:
	 *@since :
	 */
	public static List<CalendarBO> getWeekList(String strDate) {
		String [] dateArray = strDate.split("-");
		return getWeekList(dateArray[0],dateArray[1],dateArray[2]);
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得周一与周日 日期[]
	 *@param :
	 *@return:
	 *@throws:
	 *@since :
	 */
	public static String[] getWeekArr(String strDate) {
		int tYear;
		int tMonth;
		int tDay;
		String[] s = strDate.split("-");
		tYear = strToInt(s[0]);
		tMonth = strToInt(s[1]);
		tDay = strToInt(s[2]);
		int week = getMonthWeek(tYear,tMonth,tDay);
		String[] arr = new String[2];
		if(week==1)tDay=tDay-7;
		arr[0] = getDay(tYear,tMonth,tDay-(week -1)+1);
		arr[1] = getDay(tYear,tMonth,tDay + (7 - week) + 1);
		return arr;
	}
	
	public static String[] getWeekArr2(String strDate) {
		int tYear;
		int tMonth;
		int tDay;
		String[] s = strDate.split("-");
		tYear = strToInt(s[0]);
		tMonth = strToInt(s[1]);
		tDay = strToInt(s[2]);
		int week = getMonthWeek(tYear,tMonth,tDay);
		String[] arr = new String[2];
		if(week==1)tDay=tDay-7;
		
		String[] zmArr = getDay(tYear,tMonth,tDay + (7 - week) + 1).split("-");
		if(Integer.parseInt(zmArr[2]) == strToInt(s[2])){
			arr[0] = getDay(tYear,tMonth,tDay-(week -1) + 7).replaceAll("-", "");
			arr[1] = getDay(tYear,tMonth,tDay + (7 - week) +7).replaceAll("-", "");
		}else{
			arr[0] = getDay(tYear,tMonth,tDay-(week -1)).replaceAll("-", "");
			arr[1] = getDay(tYear,tMonth,tDay + (7 - week)).replaceAll("-", "");
		}
		return arr;
	}
	
	/**
	 * 
	 * 功能说明:
	 *		将传入的日期字符串分割成数组[]
	 *@param :strDate
	 *@return:arr
	 */
	public static String[] getDateArr(String strDate) {
		String[] arr = strDate.split("-");
		return arr;
	}
	
	/**
	 * 是否为yyyy-MM-dd格式日期字符串
	 * @param strDate
	 * @return
	 */
	public static boolean isDateStr(String strDate) {
		String[] arr = strDate.split("-");
		if(null != arr && arr.length == 3){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否为yyyy-MM-dd格式日期字符串
	 * @param strDate
	 * @return
	 */
	public static boolean isDateTimeStr(String strDate) {
		try{
			Date d = dateFormat(strDate, "yyyy-MM-dd hh:mm:ss");
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean isDateTimeStr(String strDate,String pattern) {
		try{
			Date d = dateFormat(strDate, pattern);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * 功能说明:
	 *		取得月LIST数据
	 *@param :
	 *@return:
	 *@throws:
	 *@since :
	 */
	public static List<List<CalendarBO>> getMonthList(int tYear,int tMonth) throws Exception {
		int week = 0;
		int monthMaxDay = getMonthMaxDay(tYear,tMonth,1);
		List<List<CalendarBO>> list = new ArrayList<List<CalendarBO>>(); 
		List<CalendarBO> boList = new ArrayList<CalendarBO>();
		
		CalendarBO bo = null;
		int row = 1;
		for(int i=1;i<=monthMaxDay;i++){
			week = getMonthWeek(tYear,tMonth,i);
			if(i == 1){
				for(int j=1;j<week;j++){
					bo = new CalendarBO();
					bo.setDate(getDay(tYear,tMonth,1-(week-j)));
					bo.setWeek(String.valueOf(getMonthWeek(tYear,tMonth,1-(week-j))));
					bo.setDispFlag("1");
					boList.add(bo);
				}
			}
			bo = new CalendarBO();
			bo.setDate(getDay(tYear,tMonth,i));
			bo.setWeek(String.valueOf(week));
			boList.add(bo);
			if(week == 7){
				list.add(boList);
				boList=new ArrayList<CalendarBO>();
				row++;
			}else if(i==monthMaxDay){
				int n = getMonthWeek(tYear,tMonth,monthMaxDay);
				for(int j=1;j<=7-n;j++){
					bo = new CalendarBO();
					bo.setDate(getDay(tYear,tMonth,monthMaxDay+j));
					bo.setWeek(String.valueOf(j+n));
					bo.setDispFlag("1");
					boList.add(bo);
				}
				list.add(boList);
			}
		}
		return list;
	}


	/**
	 * 
	 * 功能说明: 取得指定日期所在的星期
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return:int
	 */
	public static int getMonthWeek(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year); //
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		return n;
	}
	
	/**
	 * 
	 * 功能说明: 取得指定日期所在的星期
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return:int
	 */
	public static int getMonthWeek(String year, String month, String day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, strToInt(year)); //
		calendar.set(Calendar.MONTH, strToInt(month) - 1);
		calendar.set(Calendar.DATE, strToInt(day));
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		return n;
	}

	/**
	 * 
	 * 功能说明: 取得指定年月日 日期
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @param week
	 * @return:String
	 */
	public static String getDay(int year, int month, int day) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, year); //
		cpcalendar.set(Calendar.MONTH, month - 1);
		cpcalendar.set(Calendar.DATE, day);
		return getCalendar(cpcalendar.getTimeInMillis());
	}

	/**
	 * 
	 * 功能说明: 取得指定年月日 日期
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @param week
	 * @return:String
	 */
	public static String getDay(String year, String month, String day) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, strToInt(year)); //
		cpcalendar.set(Calendar.MONTH, strToInt(month) - 1);
		cpcalendar.set(Calendar.DATE, strToInt(day));
		return getCalendar(cpcalendar.getTimeInMillis());
	}
	
	/**
	 * 
	 * 功能说明: 取指定 年月 的最大天数
	 * 
	 * @param :
	 *            月最后一天日期 (格式为: yyyy-MM-dd)
	 * @return: int:月最大天数
	 */
	public static int getMonthMaxDay(int year, int month, int day) throws Exception {
		String monthLastDay = getMonthLastDay(year,month,day);
		if (null == monthLastDay)
			throw new NullPointerException();
		if (monthLastDay.length() != 10) {
			throw new Exception("月最后一天日期参数格式不正确,请核对!!!");
		}
		int monthMaxDay = strToInt(monthLastDay.substring(8));
		return monthMaxDay;
	}
	
	/**
	 * 
	 * 功能说明: 取指定 年月 的最大天数
	 * 
	 * @param :
	 *            月最后一天日期 (格式为: yyyy-MM-dd)
	 * @return: int:月最大天数
	 */
	public static int getMonthMaxDay(String year2, String month2, String day2) throws Exception {
		int year,month,day;
		year = strToInt(year2);
		month = strToInt(month2);
		day = strToInt(day2);
		String monthLastDay = getMonthLastDay(year,month,day);
		if (null == monthLastDay)
			throw new NullPointerException();
		if (monthLastDay.length() != 10) {
			throw new Exception("月最后一天日期参数格式不正确,请核对!!!");
		}
		int monthMaxDay = strToInt(monthLastDay.substring(8));
		return monthMaxDay;
	}

	/**
	 * 
	 * 功能说明: 取指定 年 月 日 的最后一天
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return: String
	 */
	public static String getMonthLastDay(int year, int month, int day) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, year); //
		cpcalendar.set(Calendar.MONTH, month - 1);
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
		cpcalendar.add(Calendar.MONTH, 1);
		cpcalendar.add(Calendar.DATE, -1);
		return getCalendar(cpcalendar.getTimeInMillis());
	}
	
	/**
	 * 
	 * 功能说明: 取指定 年 月 日 的最后一天
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return: String
	 */
	public static String getMonthLastDay(String year, String month, String day) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, strToInt(year)); //
		cpcalendar.set(Calendar.MONTH, strToInt(month) - 1);
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
		cpcalendar.add(Calendar.MONTH, 1);
		cpcalendar.add(Calendar.DATE, -1);
		return getCalendar(cpcalendar.getTimeInMillis());
	}
	/**
	 * 
	 * 功能说明: 取指定 年 的最后一天
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return: String
	 */
	public static String getYearLastDay(String year) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, strToInt(year)); //
		cpcalendar.set(Calendar.MONTH, strToInt("11"));
		cpcalendar.set(Calendar.DATE,  strToInt("31"));
		return getCalendar(cpcalendar.getTimeInMillis());
	}
	/**
	 * 
	 * 功能说明: 取指定 年 月 日 的最后一天
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return: String
	 */
	public static String getYearFirstDay(String year) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.YEAR, strToInt(year)); //
		cpcalendar.set(Calendar.MONTH, strToInt("0"));
		cpcalendar.set(Calendar.DATE,  strToInt("1"));
		return getCalendar(cpcalendar.getTimeInMillis());
	}
	/**
	 * 
	 * 功能说明: 取指定 年 月 日 的第一天
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return: String
	 */
//	private static String getMonthFirstDay(int year, int month, int day) {
//		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar = Calendar.getInstance();
//		Calendar cpcalendar = (Calendar) calendar.clone();
//		cpcalendar.set(Calendar.YEAR, year); //
//		cpcalendar.set(Calendar.MONTH, month - 1);
//		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
//
//		return getCalendar(cpcalendar.getTimeInMillis());
//	}
//	
	/**
	 * 
	 * 功能说明: 取指定 年 月 日 的第一天
	 * 
	 * @param String strDate
	 * @return: String
	 */
	public static String getMonthFirstDay(String strDate) {
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		String[] temp = strDate.split("-");
		if(temp.length != 3){
			return "";
		}
		int year = strToInt(temp[0]);
		int month = strToInt(temp[1]);
		//int day = strToInt(temp[2]);
		cpcalendar.set(Calendar.YEAR, year); //
		cpcalendar.set(Calendar.MONTH, month - 1);
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);

		return getCalendar(cpcalendar.getTimeInMillis());
	}
	
	public static Date strToDate(String strDate) {
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		String[] temp = strDate.split("-");
		int year = strToInt(temp[0]);
		int month = strToInt(temp[1]);
		int day = strToInt(temp[2]);
		cpcalendar.set(Calendar.YEAR, year);
		cpcalendar.set(Calendar.MONTH, month - 1);
		cpcalendar.set(Calendar.DAY_OF_MONTH, day);

		return cpcalendar.getTime();
	}
	

	private static String getCalendar(long l) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date(l));
	}
	
	/**
	 * 
	 * 功能说明:
	 *		将传入的月份加1
	 *@param : year 年
	 *@param : month 月
	 *@param : day 日
	 *@return: string[]
	 */
	public static String[] addMonth(String year, String month,String day) {
		String date = CalendarUtil.getDay(strToInt(year), strToInt(month)+1, strToInt(day));
		return date.split("-");
	}
	
	/**
	 * 
	 * 功能说明:
	 *		将传入的月份减1
	 *@param : year 年
	 *@param : month 月
	 *@param : day 日
	 *@return: string[]
	 */
	public static String[] decMonth(String year, String month,String day) {
		String date = CalendarUtil.getDay(strToInt(year), strToInt(month)-1, strToInt(day));
		return date.split("-");		
	}
	
	/**
	 * 
	 * 功能说明:
	 *		将传入的日期加7天(加一周)
	 *@param : year 年
	 *@param : month 月
	 *@param : day 日
	 *@return: string[]
	 */
	public static String[] addWeek(String year, String month,String day) {
		String date = CalendarUtil.getDay(strToInt(year), strToInt(month), strToInt(day)+7);
		return date.split("-");
	}
	
	/**
	 * 
	 * 功能说明:
	 *		将传入的日期减7天(减一周)
	 *@param : year 年
	 *@param : month 月
	 *@param : day 日
	 *@return: string[]
	 */
	public static String[] decWeek(String year, String month,String day) {
		String date = CalendarUtil.getDay(strToInt(year), strToInt(month), strToInt(day)-7);
		return date.split("-");
	}
	
	public static String decWeekFirstDay(String strParamDate) {
		String[] days= strParamDate.split("-");
		
		String date = CalendarUtil.getDay(strToInt(days[0]), strToInt(days[1]), strToInt(days[2])-7);
		return getWeekArr(date)[0];
	}
	public static String lastWeekDay(String strParamDate) {
		String[] days= strParamDate.split("-");
		
		String date = CalendarUtil.getDay(strToInt(days[0]), strToInt(days[1]), strToInt(days[2])-7);
		return date;
	}
	public static String nextWeekDay(String strParamDate) {
		String[] days= strParamDate.split("-");
		
		String date = CalendarUtil.getDay(strToInt(days[0]), strToInt(days[1]), strToInt(days[2])+7);
		return date;
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得年份列表,当前年前后10年数据列表
	 *		
	 *@return: list<String>
	 */
	public static List<String> getYearList() {
		List<String> list = new ArrayList<String>();
		//取得当前日期
		String date = getCurrentDate();
		int currentYear = strToInt(date.substring(0,4));
		for(int i=currentYear-10;i<currentYear+10;i++){
			list.add(String.valueOf(i));
		}
		return list;
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得当前年份
	 *		
	 *@return: String year
	 */
	public static String getCurrentYear() {
		//取得当前日期
		String date = getCurrentDate();
		return date.substring(0,4);
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得当前月份
	 *		
	 *@return: String month
	 */
	public static String getCurrentMonth() {
		//取得当前月份
		String date = getCurrentDate();
		return date.substring(6,7);
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得当前月份
	 *		
	 *@return: String month
	 */
	public static String getTwoCurrentMonth() {
		//取得当前月份
		String date = getCurrentDate();
		return date.substring(5,7);
	}
	
	/**
	 * 
	 * 功能说明:
	 *		取得当前日
	 *		
	 *@return: String day
	 */
	public static String getCurrentDay() {
		//取得当前日
		String date = getCurrentDate();
		return date.substring(8,10);
	}
	
	/**
	 * 
	 * 功能说明:
	 *
	 *@param : String dateStr
	 *@return:String (yyyy-MM-dd)
	 */
	public static String formatDateStr(String dateStr) {
		if(null == dateStr || "".equals(dateStr)) return dateStr; 
		if(dateStr.length() >= 10){
			return dateStr.substring(0,10);
		}else{
			System.out.println("传入的日期长度小于10=" + dateStr);
			return "";
		}
	}

	/** ****************************************************************** */
	/**
	 * 
	 * 功能说明: 将字符串转换为数值类型
	 * 
	 * @param :
	 *            String
	 * @return: int
	 */
	public static int strToInt(String str) {
		int ret = 0;
		try {
			ret = Integer.parseInt(str);
		} catch (Exception ex) {
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * 
	 * 功能说明: 将int型日期转换为字符类型的日期
	 * 
	 * @param :
	 *            int week
	 * @return: String ret
	 */
	public static String dateToStringOther(int week) {
		String ret = "";
		switch (week) {
		case 1:
			ret = "星期日";
			break;
		case 2:
			ret = "星期一";
			break;
		case 3:
			ret = "星期二";
			break;
		case 4:
			ret = "星期三";
			break;
		case 5:
			ret = "星期四";
			break;
		case 6:
			ret = "星期五";
			break;
		case 7:
			ret = "星期六";
			break;
		}
		return ret;
	}

	/**
	 * 
	 * 功能说明: 将int型日期转换为字符类型的日期
	 * 
	 * @param :
	 *            int week
	 * @return: String ret
	 */
	public static String dateToString(int week) {
		String ret = "";
		switch (week) {
		case 1:
			ret = "周日";
			break;
		case 2:
			ret = "周一";
			break;
		case 3:
			ret = "周二";
			break;
		case 4:
			ret = "周三";
			break;
		case 5:
			ret = "周四";
			break;
		case 6:
			ret = "周五";
			break;
		case 7:
			ret = "周六";
			break;
		}
		return ret;
	}
	
	public static String dateDisFormat(String datestr){
		String s = datestr.substring(5, 10);
		return s;
	}
	/**
	 * 
	 * 功能说明: 将int型日期转换为字符类型的日期
	 * 
	 * @param :
	 *            String week
	 * @return: String ret
	 */
	public static String dateToString(String week2) {
		String ret = "";
		int week = strToInt(week2);
		switch (week) {
		case 1:
			ret = "周日";
			break;
		case 2:
			ret = "周一";
			break;
		case 3:
			ret = "周二";
			break;
		case 4:
			ret = "周三";
			break;
		case 5:
			ret = "周四";
			break;
		case 6:
			ret = "周五";
			break;
		case 7:
			ret = "周六";
			break;
		}
		return ret;
	}
	
	public static int dateToWeek(String week) {
		int ret = 0;
		if(week.equals("周日")) {
			ret = 1;
		} else if(week.equals("周一")) {
			ret = 2;
		} else if(week.equals("周二")) {
			ret = 3;
		} else if(week.equals("周三")) {
			ret = 4;
		} else if(week.equals("周四")) {
			ret = 5;
		} else if(week.equals("周五")) {
			ret = 6;
		} else if(week.equals("周六")) {
			ret = 7;
		}
		return ret;
	}
	
	/**
	 * 
	 * 功能说明:
	 *		 日期不足两位的在前端补0
	 *@param : day 日期
	 *@return: 补全后的日期
	 *@throws:
	 *@since :
	 */
	public static String fillDay(int day) throws Exception{
		String ret = String.valueOf(day);
		if(ret.length() < 2){
			ret = "0" + ret;
		}
		return ret;
	}
	
	/**
	 * 
	 * 功能说明:
	 *		 日期不足两位的在前端补0
	 *@param : day 日期
	 *@return: 补全后的日期
	 *@throws:
	 *@since :
	 */
	public static String fillDay(String day) throws Exception{
		String ret = day;
		if(ret.length() < 2){
			ret = "0" + ret;
		}
		return ret;
	}
	
	/**
	 * 返回指定日期所在周的第一天
	 */
	public static String dayToWeek(String year,String month,String day) throws Exception{
		String weekStr = getWeekArr(year+"-"+month+"-"+day)[0];
		return weekStr.substring(8, 10);
	}
	
	/**
	 * 返回指定日期所在周的第一天的日期
	 */
	public static String dayToWeek2(String year,String month,String day) throws Exception{
		String weekStr = getWeekArr(year+"-"+month+"-"+day)[0];
		return weekStr;
	}

	/**
	 * 周List，指定月份共有多少周，每周周一数据List
	 * @throws Exception 
	 */
	public static List<String> getMonthWeekList(String year,String month) throws Exception{
		List<String> list = new ArrayList<String>();
		int tYear;
		int tMonth;
		tYear = strToInt(year);
		tMonth = strToInt(month);
		int maxDay = getMonthMaxDay(tYear,tMonth,1);
		for(int i=1;i<=maxDay;i+=7){
			String weekStr = getWeekArr(tYear+"-"+tMonth+"-"+i)[0].substring(8, 10);  //周一
			list.add(weekStr);
		}
		return list;
	}
	
	/**
	 * 周List，指定月份共有多少周，每周周一数据List
	 * @throws Exception 
	 */
	public static List<String> getMonthWeekList2(String year,String month) throws Exception{
		List<String> list = new ArrayList<String>();
		int tYear;
		int tMonth;
		tYear = strToInt(year);
		tMonth = strToInt(month);
		int maxDay = getMonthMaxDay(tYear,tMonth,1);
		for(int i=1;i<=maxDay;i+=7){
			String weekStr = getWeekArr(tYear+"-"+tMonth+"-"+i)[0];  //周一
			list.add(weekStr);
		}
		return list;
	}
	
	/**
	 * 取得指定日期所在的是第几周
	 * @throws Exception 
	 */
	public static String getWeekDate(String year,String month,String day) throws Exception{
		List<String> list = getMonthWeekList2(year,month);
		String dateStr = getDay(year, month, day);
		for(int i=0;i<list.size();i++){
			String weekStr = list.get(i);  //周一
			if(dateStr.equals(weekStr)){
				return String.valueOf(i+1);
			}
		}
		return "1";
	}
	
	/**
	 * 取得strDate指定月份的第一天和最后一天
	 * @param strDate
	 * @return
	 */
	public static String[] getMonthArr(String strDate) {
		String[] d = strDate.split("-");
		String monthFirstDay = getWeekArr(strDate)[0];
		String monthLastDay = getWeekArr(getMonthLastDay(d[0],d[1],d[2]))[1];
		String[] temps = monthLastDay.split("-");
		String[] monthEndDay = decWeek(temps[0], temps[1], temps[2]);
		if(temps[1].equals(monthEndDay[1])){
			monthEndDay = temps;
		}
		String[] monthArr = {monthFirstDay,getDay(monthEndDay[0], monthEndDay[1], monthEndDay[2])};
		return monthArr;
	}
	
	/**
	 * 功能说明: 将日期转为指定的格式的字符串
	 * @param date,patten
	 * @return String
	 */
	public static String dateToString(Date date,String patten) {
		if(null == date) return "";
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		return sdf.format(date);
	}
	/**
	 * 返回时间差，移为单位
	 * @param beginDt
	 * @param endDt
	 * @return -1 表示失败
	 */
	public static Long dataDiff(String beginDt,String endDt){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try 
		{ 
		    Date dt1 = df.parse(endDt); 
		    Date dt2 = df.parse(beginDt); 
		    long diff = dt1.getTime() - dt2.getTime(); 
		    long days = diff / 1000; 
		    return days;
		} 
		catch (Exception e) 
		{ 
		} 
		return -1l;
	}
	/**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
	/**
	 * 返回 与当前时间的   时间差，移为单位
	 * @param beginDt
	 * @param endDt
	 * @return -1 表示失败
	 */
	public static String dataDiffByNow(String endDt){
		if(null == endDt || "".equals(endDt)) return "-1"; 
		String beginDt = getCurrentDateAll();
		return String.valueOf(dataDiff(beginDt,endDt));
	}

	/**
	 * 验证日期是否为本周
	 * @param strDate
	 * @return
	 */
	public static boolean isCurrWeek(String strDate) {
		try{
			String[] weekArr = CalendarUtil.getWeekArr2(CalendarUtil.getCurrentDate());
			String[] weekArr2 = CalendarUtil.getWeekArr2(strDate);
			if(weekArr[0].equals(weekArr2[0])) return true;
		}catch(Exception e){}
		return false;
	}
	
	public static int dateToWeekNum(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return w;
    }
	
	 /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     * @Description TODO
     * @param begin
     * @param end
     * @return
     * @author XuJD
     * @date 2017-4-1
     */
    public static List<Date> getBetweenDates(Date begin, Date end) {
            List<Date> result = new ArrayList<Date>();
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(begin);
            /* Calendar tempEnd = Calendar.getInstance();
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            tempEnd.setTime(end);
            while (tempStart.before(tempEnd)) {
                result.add(tempStart.getTime());
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }*/
         while(begin.getTime()<=end.getTime()){
             result.add(tempStart.getTime());
             tempStart.add(Calendar.DAY_OF_YEAR, 1);
             begin = tempStart.getTime();
         }
            return result;
    }
    
    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）, 按 week 设置的周取出日期列表
     * @Description TODO
     * @param begin
     * @param end
     * @return
     * @author yangchao
     */
    public static List<Date[]> getBetweenDatesForWeeks(String begin, String end, String week) {
    	List<Date[]> result = new ArrayList<Date[]>();
        Date beginDate = dateFormat(begin,"yyyy-MM-dd");
        Date endDate = dateFormat(end,"yyyy-MM-dd");
        List<Date> list = getBetweenDates(beginDate, endDate);
        List<Date> tempList = new ArrayList<Date>();
        if(null == week) return result;
        String[] weekArr = week.split(",");
        Map<String,String> weekMap = new HashMap<String,String>();
        for(String w : weekArr){
        	weekMap.put(w, w);
        }
        Date bDate = null;
        Date eDate = null;
        Date dt1 = null;
        Date dt2 = null;
        boolean flag = false;
        for(int i=0;i<list.size();i++){
        	String dt = dateToString(list.get(i),"yyyy-MM-dd");
        	int dw = dateToWeekNum(dt);
        	if(weekMap.containsKey(String.valueOf(dw))){
        		tempList.add(list.get(i));
        	}
        }
        
        for(int i=0;i<tempList.size();i++){
        	if(i+1>=tempList.size()) {
        		dt1 = tempList.get(i);
        		dt2 = tempList.get(i);
        	}else{
        		dt1 = tempList.get(i);
        		dt2 = tempList.get(i+1);
        	}
        	if(null == bDate){
    			bDate = tempList.get(i);
    			eDate = tempList.get(i);
    		}else{
    			eDate = tempList.get(i);
    		}
//        	String dateStr1 = dateToString(dt1,"yyyy-MM-dd");
//        	String dateStr2 = dateToString(dt2,"yyyy-MM-dd");
        	int day = differentDays(dt1,dt2);
			System.out.println("diff=" + day);
			if(day > 1){
				result.add(new Date[]{bDate,eDate});
				bDate = null;
		        eDate = null;
				flag = true;
			}else{
				flag = false;
			}
        }
        if(!flag && null != bDate && null != eDate){
        	result.add(new Date[]{bDate,eDate});
        	bDate = null;
	        eDate = null;
        }
        return result;
    }
    
    /**
     * 获取上午下午晚上的时间段
     * @param useTime  2上午 3下午 4晚上 
     * @return
     */
    public static String[] getUseTimeStr(String useTime){
    	String[] times = new String[2];
    	if("2".equals(useTime)){
			//上午
			times[0] = "00:00";
			times[1] = "12:59";
		}else if("3".equals(useTime)){
			//下午
			times[0] = "13:00";
			times[1] = "17:59";
		}else if("4".equals(useTime)){
			//晚上
			times[0] = "18:00";
			times[1] = "23:59";
		}
    	return times;
    }
    
    public static String getUseTimeStrView(String useTime){
    	if("2".equals(useTime)){
			//上午
			return "上午";
		}else if("3".equals(useTime)){
			//下午
			return "下午";
		}else if("4".equals(useTime)){
			//晚上
			return "晚上";
		}
    	return "";
    }
    
    public static void main(String[] args){
//		System.out.println("" + getCurrentDateAll());
//		
//		System.out.println(dateToString(new Date(),"yyMMddHHmm"));
//		String[] weekArr = getWeekArr2("2018-05-19");
//		System.out.println(weekArr);
		
		try {
			List<Date[]> list = getBetweenDatesForWeeks("2018-08-23", "2018-08-31" ,"5,6,0");
			for(Date[] d : list){
				System.out.println("" + CalendarUtil.dateToString(d[0], "yyyy-MM-dd") + "~" + CalendarUtil.dateToString(d[1], "yyyy-MM-dd"));
			}
			//List<List<CalendarBO>> list = getMonthList(2018,6);
			//System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
