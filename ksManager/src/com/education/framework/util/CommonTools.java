package com.education.framework.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JTextField;

import com.education.framework.util.calendar.CalendarUtil;

public class CommonTools {	
	public static String getClientIP(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 

	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	} 
	
	/**
	 * 判断输入的字符串是否为null，如果是null则返回"",否则，返回原字符串。
	 */
	public static String null2String(String str) {
		if (isNullString(str)) {
			return "";
		} else {
			return str;
		}
	}
	
	public static String null2String(Date d, String patten) {
		if (null == d) {
			return "";
		} else {
			return CalendarUtil.dateToString(d, patten);
		}
	}
	
	/**
	 * 读取4为数字的随机码
	 * @return String 4位
	 */
	public static String getRandCode(){
		String result = "";
		result = String.valueOf(Math.round(Math.random()*10000));
		while(result.length() < 4){//不足4位使用0补全
			result = "0"+result;
		}
		if(result.length() > 4){//超过4位时取前4位
			result = result.substring(0, 4);
		}
		return result;
	}
	/**
	 * 根据数据的字母 ，输出相应位置的应为数字
	 * @return String 1位
	 */
	public static String getShuZi(String zimuid){
		String str="";
		       if(zimuid.trim().equals("A")){
			      str="1";
			   }else if(zimuid.trim().equals("B")){
			      str="2";
			   }else if(zimuid.trim().equals("C")){
			      str="3";
			   }else if(zimuid.trim().equals("D")){
			      str="4";
			   }else if(zimuid.trim().equals("E")){
			      str="5";
			   }else if(zimuid.trim().equals("F")){
			      str="6"; 
			   }else if(zimuid.trim().equals("G")){
			      str="7";
			   }else if(zimuid.trim().equals("H")){
			      str="8";
			   }else if(zimuid.trim().equals("I")){
			      str="9";
			   }else if(zimuid.trim().equals("J")){
			      str="10";
			   }
		return str;
	}
	
	
	/**
	 * 根据数据的数字 ，输出相应位置的应为字母
	 * @return String 1位
	 */
	public static String getZiMu(String zimuid){
		     String str="";
		       if(zimuid.trim().equals("1")){
			      str="A";
			   }else if(zimuid.trim().equals("2")){
			      str="B";
			   }else if(zimuid.trim().equals("3")){
			      str="C";
			   }else if(zimuid.trim().equals("4")){
			      str="D";
			   }else if(zimuid.trim().equals("5")){
			      str="E";
			   }else if(zimuid.trim().equals("6")){
			      str="F"; 
			   }else if(zimuid.trim().equals("7")){
			      str="G";
			   }else if(zimuid.trim().equals("8")){
			      str="H";
			   }else if(zimuid.trim().equals("9")){
			      str="I";
			   }else if(zimuid.trim().equals("10")){
			      str="J";
			   }else if(zimuid.trim().equals("11")){
			      str="K";
			   }else if(zimuid.trim().equals("12")){
			      str="L";
			   }else if(zimuid.trim().equals("13")){
			      str="M";
			   }else if(zimuid.trim().equals("14")){
			      str="N";
			   }else if(zimuid.trim().equals("15")){
			      str="O";   
			   }else if(zimuid.trim().equals("16")){
			      str="P";
			   }else if(zimuid.trim().equals("17")){
			      str="Q";   
			   }else if(zimuid.trim().equals("18")){
			      str="R";
			   }else if(zimuid.trim().equals("19")){
			      str="S";
			   }else if(zimuid.trim().equals("20")){
			      str="T";
			   }else if(zimuid.trim().equals("21")){
			      str="U";
			   }else if(zimuid.trim().equals("22")){
			      str="V";
			   }else if(zimuid.trim().equals("23")){
			      str="W";
			   }else if(zimuid.trim().equals("24")){
			      str="X";
			   }else if(zimuid.trim().equals("25")){
			      str="Y";
			   }else if(zimuid.trim().equals("26")){
			      str="Z";
			   }

		return str;
	}
	/**
	 * 格式化字符串为标准日期
	 * @param str  字符串
	 * @return  返回格式
	 */
	public static boolean isDate(String str){
		boolean breturn=false;
		if(!CommonTools.isNullString(str)){
			try{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");     
				dateFormat.setLenient(false);     
				dateFormat.parse(str);     
				breturn = true;     
			}catch (Exception e) {   
				breturn = false;
			} 
		}
		return  breturn;
	}
	
	
	public static boolean isLastLine(String[] line){
		boolean breturn=true;
		for(int i=0;i<line.length;i++){
			if("*****".equals(line[i]) || CommonTools.isNullString(line[i])){
				continue;
			}else{
				breturn=false;
				break;
			}
		}		
		return  breturn;
	}	
	
	public static String formatStrDate(String date){
	    
	    String returnFormatDate = "";
	    if(date.length()>1){
	        returnFormatDate = "to_date('"+date+"','yyyy-mm-dd')";
	    }else{
	        returnFormatDate = "null";
	    }
	    return returnFormatDate;
	}
	
	/**
	 * 将字符串转化为日期格式，字符串的格式为：yyyy-mm-dd，<br>
	 * @param date date的格式为：yyyy-mm-dd
	 * @return Date - 如果无法转换，则返回null
	 *
	 */
	public static Date str2Date(String date)
	{		
		java.sql.Date return_Date = null;
		try
		{
			return_Date = java.sql.Date.valueOf(date);
		}
		catch(Exception e)
		{
			return null;
		}
		
		return return_Date;
	}
	
	/**
	 * 将日期转化为字符格式，字符串的格式为：yyyy-mm-dd
	 * @param date 要转化的date
	 * @return String 返回的格式为：yyyy-mm-dd
	 *
	 */
	public static String date2String(Date date){	
		if (date == null){
			return "";
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		}
	}
	
	/**
	 * 将日期转化为字符格式，字符串的格式为：yyyy-mm-dd
	 * @param date 要转化的date
	 * @return String 返回的格式为：yyyy-mm-dd
	 *
	 */
	public static String dateToStringForWebService(Date date){	
		if (date == null){
			return "";
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			return dateFormat.format(date);
		}
	}
	
	/**
	 * 将日期转化为字符格式，字符串的格式为：yyyy-mm-dd
	 * @param date 要转化的date
	 * @return String 返回的格式为：yyyy-mm-dd
	 *
	 */
	public static String time2String(Date date){	
		if (date == null){
			return "";
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return dateFormat.format(date);
		}
	}
	
	public static String time2String2(Date date){	
		if (date == null){
			return "";
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			return dateFormat.format(date);
		}
	}
	
	/**
	 * 将日期转化为只有年度的字符格式，字符串的格式为：yyyy
	 * @param date
	 * @return
	 */
	public static String date2Year(Date date)
	{
		if (date == null){
			return "";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			return sdf.format(date);
		}
	}
	
	public static java.sql.Date util2Sql(java.util.Date date)
	{
		if(date == null){
			return null;
		}
		else{
		    return new java.sql.Date(date.getTime());
		}
	}
    
    
	/**
	 * 删除文件夹，当该文件夹下有文件或者文件夹的时候不能删除该文件夹
	 * @param path
	 * @return boolean
	 *
	 */
	public static boolean deleteFile(String path)
	{
	    System.out.println("删除文件：" + path);
		File file = new File(path);
		boolean returnBoolean = file.delete();
		System.out.println("删除" + returnBoolean);
		return returnBoolean;
	}
	
	/**
	 * 在字符串上加CDATA。
	 * @param string
	 * @return String
	 *
	 */
	public static String addCDATA(String string)
	{
		String returnStr = "<![CDATA[";
		returnStr += string;
		returnStr += "]]>";
		return returnStr;
	}
    
	/**
	 * 判断输入框中的内容是否为空
	 * @param textField - 输入框
	 * @return - true 表示是空，否则，表示不为空
	 */
	public static boolean isInputNull(JTextField textField)
	{
	    if(textField.getText().equals(""))
	    {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * 将char的高四位去掉，再合并成字符串。<br>
	 * 目的是要解决度dbf文件的字段名时，中文出现乱码问题
	 * @param str
	 * @return
	 */
    public static String getStrByCharToByte(String str){
        byte[] temp = new byte[str.length()];
        for(int i = 0; i < str.length(); i ++){
            temp[i] = (byte)(str.charAt(i));
        }
        return new String(temp);
    }
    
    public static boolean isNullString(String str) {
    	if(str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("NULL"))
    		return true;
    	else
    		return false;
    }
    
    public static String convertorQuote(String str){
    	if (isNullString(str))
			return "";
		return str.replaceAll("'","''");
    }
    
    public static String convertorLike(String str){
    	if (isNullString(str)){
			return "%%";
    	}else{
    		str = str.replaceAll("'","''");
    		if(str.indexOf("_") >= 0 || str.indexOf("%") >= 0 || str.indexOf("/") >= 0){
    			str = str.replaceAll("/", "//");
    			str = str.replaceAll("_", "/_");
    			str = str.replaceAll("%", "/%");
    			return "%"+str + "%' ESCAPE '/";
    		}else{
    			return "%"+str+"%";
    		}
    	}
    }
    
    public static String toChinese(String strvalue){
	    try{
		    if(strvalue==null)
		    	return null;
		    else{
			    strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
			    return strvalue;
		    }
	    }catch(Exception e){
		    return null;
		}
    } 

    public static String gBK2ISO_8859_1(String str){
    	try {
			return new String(str.getBytes("GBK"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
    }
    
    public static String utf8ISO_8859_1(String str) {
    	try {
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
    }
    
    public static String iSO_8859_12GBK(String str){
    	try {
			return new String(str.getBytes("ISO-8859-1"),"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
    }
    /** 将数组转换成字串 ","分隔*/
    public static String array2String(Object [] array){
    	StringBuffer str = new StringBuffer();
    	for(int i = 0;i < array.length;i ++)
    		str.append((String)array[i]).append(",");
    	if(str.length() != 0)
    		str = new StringBuffer(str.substring(0,str.length() - 1));
    	return str.toString();
    }
    /**
     * 月份判断
     * @param month
     * @return
     */
    public static String monthValidate(int month){
    	String str = "";
    	switch(month){
    	case 1:case 3:case 5:case 7:case 8:case 10:case 12:str = "l";break;
    	case 4:case 6:case 9:case 11:str = "s";break;
    	case 2:str = "m";break;
    	default:str = "err";
    	}
    	return str;
    }
    
    public static void println(Object obj){
    	System.out.println("[ CommonTools DEBUG : ]"+obj);
    }
    /**
     * 将字符串转换为UTF8编码。
     * @param str 未经过编码的字符串。
     * @return UTF8编码的字符串。
     */
    public static String toUtf8String(String str){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
		    char c = str.charAt(i);
		    if (c >= 0 && c <= 255) {
		    	sb.append(c);
		    } 
		    else{
				byte[] b;
				try{
				    b = Character.toString(c).getBytes("utf-8");
				} 
				catch (Exception ex){
				    System.out.println(ex);
				    b = new byte[0];
				}
				for (int j = 0; j < b.length; j++){
				    int k = b[j];
				    if (k < 0) k += 256;
				    sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
		    }
		}
		return sb.toString();
    }
	
	public static String getCurrentYearAndMonth(){
		GregorianCalendar lgc = new GregorianCalendar();
		int year = lgc.get(Calendar.YEAR);
		int month = lgc.get(Calendar.MONTH);
		if(month == 0){
			year = year - 1; 
			month = 12;
		}
		if(month < 10){
			return String.valueOf(year) + "-0" + String.valueOf(month);
		}else{
			return String.valueOf(year) + "-" + String.valueOf(month);
		}
	}
	
	public static String getCurrentYearAndMonth(int spanMonth){
		GregorianCalendar lgc = new GregorianCalendar();
		int year = lgc.get(Calendar.YEAR);
		int month = lgc.get(Calendar.MONTH) + 1 + spanMonth;
		if(month > 12){
			year = year + 1; 
			month = month - 12;
		}else if(month <= 0){
			year = year - 1; 
			month = month + 12;
		}
		if(month < 10){
			return String.valueOf(year) + "-0" + String.valueOf(month);
		}else{
			return String.valueOf(year) + "-" + String.valueOf(month);
		}
	}
	
    /**
     * 计算年度列表
     * @param span 跨度：当前系统年度的前后跨度
     * @return
     */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static List getYear(int span){
		GregorianCalendar lgc = new GregorianCalendar();
        String year = String.valueOf(lgc.get(Calendar.YEAR));
        List list = new ArrayList();
        Map map = null;
        for(int i = Integer.parseInt(year) - span; i <= Integer.parseInt(year) + span;i ++){
        	map = new HashMap();
        	map.put("value",String.valueOf(i));
        	list.add(map);
        }
        return list;
	}
	
	/**
     * 计算年度列表
     * @param span 跨度：当前系统年度的前后跨度
     * @param flag 标识 0：系统时间前 1：系统时间后 其他：系统时间前后
     * @return
     */
	@SuppressWarnings("unchecked")
	public static List getYears(int span,int flag){
		GregorianCalendar lgc = new GregorianCalendar();
        String year = String.valueOf(lgc.get(Calendar.YEAR));
        List list = new ArrayList();
        Map map = null;
        if(flag == 0)
	        for(int i = Integer.parseInt(year) - span; i <= Integer.parseInt(year);i ++){
	        	map = new HashMap();
	        	map.put("value",String.valueOf(i));
	        	list.add(map);
	        }
        else if(flag == 1)
        	for(int i = Integer.parseInt(year); i <= Integer.parseInt(year) + span;i ++){
	        	map = new HashMap();
	        	map.put("value",String.valueOf(i));
	        	list.add(map);
	        }
        else
        	for(int i = Integer.parseInt(year) - span; i <= Integer.parseInt(year) + span;i ++){
	        	map = new HashMap();
	        	map.put("value",String.valueOf(i));
	        	list.add(map);
	        }
        return list;
	}
	
    /**
     * 为编号左填充指定字符
     * @param src 源字符
     * @param cha 填充字符
     * @param len 填充需要达到的长度
     * @return String 填充后的
     */
	public static String supplyChar(String src,String cha, int len) {
	    String strSrc = src;
		while (strSrc.length() < len) {
			strSrc = cha + strSrc;
		}
		return strSrc;
	}
	
	/**
     * 系统日期取得.  <BR>
     *
     * @author  李晓强
     * @return  yyyymmdd格式
     * @see
     * @since   1.0
     *
     */
    public static String getSysdate() {
        GregorianCalendar lgc = new GregorianCalendar();
        String year = String.valueOf(lgc.get(Calendar.YEAR));
        String month = String.valueOf(lgc.get(Calendar.MONTH) + 1);
        if (month.length() == 1)  month = "0" + month;
        String date = String.valueOf(lgc.get(Calendar.DATE));
        if (date.length() == 1) date = "0" + date;
        return year+ "-" + month + "-" + date;
    }

    /**
     * 系统时间取得.  <BR>
     *
     * @author  李晓强
     * @return  HHMISS
     * @see
     * @since   1.0
     *
     */
    public static String getSystime() {
        GregorianCalendar lgc = new GregorianCalendar();
        String hour = String.valueOf(lgc.get(Calendar.HOUR_OF_DAY));
        if (hour.length() == 1) hour = "0" + hour;
        String minute = String.valueOf(lgc.get(Calendar.MINUTE));
        if (minute.length() == 1) minute = "0" + minute;
        String second = String.valueOf(lgc.get(Calendar.SECOND));
        if (second.length() == 1) second = "0" + second;
        return hour + ":" + minute + ":" + second;
    }
    
    
    
    public static void Debug(Object obj)
    {
    	System.out.println(obj.toString());
    }
    
    public static String FormatDate(java.util.Date date)
    {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);    	
    }
    
    public static String FormatDate(java.util.Date date,String format)
    {
    	SimpleDateFormat simpledateformat=new SimpleDateFormat(format);
		return simpledateformat.format(date);    	
    }
    
    public static String formatStrDate2(String date){
	    
	    String returnFormatDate = "";
	    if(date.length()>1){
	        returnFormatDate = "to_date('"+date+"','yyyy-mm-dd')";
	    }else{
	        returnFormatDate = "";
	    }
	    return returnFormatDate;
	}
    
    /**
	 * 将字符串数组转化为整数进行相加，然后将结果作为字符串返回
	 * 
	 * @param String[]
	 *            字符串数组
	 * @return String 字符串数组之和
	 */
	public static String StringArray2String(Object[] stringArray) {
		long sum = 0;
		for (int i = 0; i < stringArray.length; i++) {
			try {
				sum += Long.parseLong((String)stringArray[i]);
			}catch(Exception e){
				sum += 0;
			}
			
		}
		return String.valueOf(sum);
	}

	/**
	 * 将字符串转化为整数进行拆分，拆分为2的n次幂的字符串数组
	 * 
	 * @param String
	 *            字符串数组之和
	 * @return String[] 字符串数组
	 */
	public static String[] String2StringArray(String string) {
		StringBuffer buf = new StringBuffer();
		String binaryString = Long.toBinaryString(Long.parseLong(string));//转为2进制
		//System.out.println(binaryString);
		for (int i = 0; i < binaryString.length(); i++) {
			if (binaryString.charAt(i) == '1') {
				buf.append(String.valueOf((long) Math.pow((double) 2, (double) binaryString.length()-1-i)));
			}
			if(i!=binaryString.length()-1){
				buf.append(", ");
			}
		}
		
		StringTokenizer st = new StringTokenizer(buf.toString(), ", ");
		
		int count=st.countTokens();
		String[] s = new String[count];//判断数组个数
		for (int i = 0; i <count ; i++) {
			s[i] = st.nextToken();
			
		}

		return s;
	}
	
	/**
	 * 将字符串转化为整数进行拆分，拆分为2的n次幂的字符串，中间用逗号分隔
	 * 
	 * @param String
	 *            字符串数组之和
	 * @return String 用逗号分隔的拆分后的字符串
	 */
	public static String String2StringList(String string) {
		StringBuffer buf = new StringBuffer();
		String binaryString = Long.toBinaryString(Long.parseLong(string));//转为2进制
		//System.out.println(binaryString);
		for (int i = 0; i < binaryString.length(); i++) {
			if (binaryString.charAt(i) == '1') {
				buf.append(
						String.valueOf((long) Math.pow((double) 2, (double) binaryString.length()-1-i)));
			}
			if(i!=binaryString.length()-1){
				buf.append(",");
			}
		}
		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	public static String uniteString(String str1,String str2){
		if(CommonTools.isNullString(str1))
			str1 = "0";
		if(CommonTools.isNullString(str2))
			str2 = "0";
		Set set = new HashSet();
		String [] tmp = String2StringArray(str1);
		for(int i = 0;i < tmp.length;i ++){
			set.add((String)tmp[i]);
		}
		tmp = String2StringArray(str2);
		for(int i = 0;i < tmp.length;i ++){
			set.add((String)tmp[i]);
		}
		return StringArray2String(set.toArray());
	}
    
	public static String [] split(String str){
		return str.split(",");
	}
	
	public static boolean isInt(String character) {
		char chars[] = character.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int ascii = getAscii(chars[i]);
			if (ascii >= 48 && ascii <= 57)
				return true;
		}

		return false;
	}

	public static int getAscii(char cn) {
		byte bytes[] = String.valueOf(cn).getBytes();
		if (bytes.length == 1)
			return bytes[0];
		if (bytes.length == 2) {
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte) - 0x10000;
			return ascii;
		} else {
			return 0;
		}
	}
	
	public static String[] getMondaySunDay(int year,int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(4);
		String[] dateStr = new String[2];
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		dateStr[0] = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dateStr[1] = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return dateStr;
	}
	
	public static int getWeekOfYear(String date) {
		int weekOfYear = 1;
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(4);
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0,4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5,7)) - 1);
		calendar.set(Calendar.DATE, Integer.parseInt(date.substring(8,10)));
		weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		return weekOfYear;
	}
	
	public static int getMonthWeek(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(4);
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0,4))); 
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5,7)) - 1);
		calendar.set(Calendar.DATE, Integer.parseInt(date.substring(8,10)));
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		return n;
	}
	
	public static int compareDate(String date1, String date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setFirstDayOfWeek(Calendar.MONDAY);
		calendar1.setMinimalDaysInFirstWeek(4);
		calendar1.set(Calendar.YEAR, Integer.parseInt(date1.substring(0,4)));
		calendar1.set(Calendar.MONTH, Integer.parseInt(date1.substring(5,7)) - 1);
		calendar1.set(Calendar.DATE, Integer.parseInt(date1.substring(8,10)));
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setFirstDayOfWeek(Calendar.MONDAY);
		calendar2.setMinimalDaysInFirstWeek(4);
		calendar2.set(Calendar.YEAR, Integer.parseInt(date2.substring(0,4)));
		calendar2.set(Calendar.MONTH, Integer.parseInt(date2.substring(5,7)) - 1);
		calendar2.set(Calendar.DATE, Integer.parseInt(date2.substring(8,10)));
		return calendar1.compareTo(calendar2);
	}
	
	public static String addDays(String date, int span){
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0,4))); 
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5,7)) - 1);
		calendar.set(Calendar.DATE, Integer.parseInt(date.substring(8,10)));
		calendar.add(Calendar.DATE, span);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	
	}
    
	public static String list2String(List list,String st) {
		StringBuffer str = new StringBuffer("");
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){
				str.append((String)((HashMap)list.get(i)).get(st));
			}
			else {
				str.append((String)((HashMap)list.get(i)).get(st)+",");
			}
		}
		return str.toString();
	}
	
	public String findFileName(String fileName){
		String temp = "";
		String[]tamp = fileName.split("/");
		temp = tamp[tamp.length];
		return temp;
	}
	
	/** 
	 * 得到单个字符在另一个字符串中出现的位置
	 *
	 * @param String mainString 主字符串
	 * @param String subString 被判断字符串
     * @return List<Integer> 所有出现的位置
	 */
	public static List<Integer> getSitesInString(String mainString,String subString){
		
		List<Integer> resultList = new ArrayList<Integer>();
		
		if ("".equals(mainString) || mainString == null ){
			return resultList;
		}
		if ("".equals(subString) || subString == null){
			return resultList;
		}
		
		 int tagLength = subString.length();//搜索的字符或者字符串长度
		  String[] temp = mainString.split(subString);//拆分text
		  int l = temp.length;//mainString拆分后得到的数组长度
		  //如果subString不是以subString结束，将l的值-1
		  if(!mainString.endsWith(subString)){
		    l = l-1;
		  }
		  //其实位置
		  int position = 0;
		  for(int i = 0; i < l; i++){
		   String s = temp[i];
		   position += s.length();
		   resultList.add(position);
		   position += tagLength;
		  }
		return resultList;
	}
	
	/** 
	 * 计算某天属于那一年的第几周。
	 */
	public static int getWeekOfYearWithCPlan(String date, Map<String,Integer> refValuemap) {
		Calendar c = new GregorianCalendar();
		// 星期第1天是星期一   
		c.setFirstDayOfWeek(Calendar.MONDAY);
		// 年和月算周，要4天及以上才算是1周
		c.setMinimalDaysInFirstWeek(4);
		c.set(Calendar.YEAR, Integer.parseInt(date.substring(0,4)));
		c.set(Calendar.MONTH, Integer.parseInt(date.substring(5,7)) - 1);
		c.set(Calendar.DATE, Integer.parseInt(date.substring(8,10)));
		int weeknum = c.get(Calendar.WEEK_OF_YEAR);
		int vyear = c.get(Calendar.YEAR);
		int vmonth = c.get(Calendar.MONTH)+1;
		// 当1月分首周不满一周时算为去年的周数，故要年号减1
		if(vmonth == 1 && weeknum>6) {
			vyear--;
		}
		// 当最后一周不满一周算为下一年的周数，故要年号加1
		if(vmonth == 12 && weeknum==1) {
			vyear++;
		}
		if(refValuemap!= null) {
			refValuemap.put("weeknum", weeknum);
			refValuemap.put("year", vyear);
		}
		return weeknum;   
	}
	
	/**
	 * 字符串中指定字符替换方法，将符合替换的字符替为空，其它的字符保留
	 * @param str 待替换原字符串
	 * @param op 替换字符
	 * @return 替换完成的字符串
	 */
	public static String replaceStrToNull(String str,String op){
    	if (isNullString(str))
			return "";
		return str.replaceAll(op,"");
    }
	
	/**
	 * 年份转为大写字符
	 * @param dateStr
	 * @return
	 */
	public static String yearUpperConvert(String dateStr) {
		if(null == dateStr || "".equals(dateStr)) return "";
		String[] strArray = {"〇","一","二","三","四","五","六","七","八","九"};
		StringBuffer buffer = new StringBuffer();
		for( int i = 0; i < dateStr.length(); i++ ) {
			try{
				String s = dateStr.substring(i, i+1);
				int num = Integer.valueOf(s);
				buffer.append(strArray[num]);
			}catch(Exception ex){
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 月份及日 转为大写字符
	 * @param dateStr
	 * @return
	 */
	public static String monthOrdayUpperConvert(String dateStr) {
		
		if(null == dateStr || "".equals(dateStr)) return "";
		
		if(dateStr.length() != 2) return "";
		String[] strArray = {"〇","一","二","三","四","五","六","七","八","九"};
		StringBuffer buffer = new StringBuffer();
		String lp = "";
		
		
		for( int i = 0; i < dateStr.length(); i++ ) {
			try{
				String s = dateStr.substring(i, i+1);
				int num = Integer.valueOf(s);
				buffer.append(lp);
				if( (num == 1 && i==0 ) || num==0 ){
					
				}else{
					buffer.append(strArray[num]);
				}
				if( num != 0 ) lp = "十";
			}catch(Exception ex){
			}
		}
		return buffer.toString();
	}
	/**
	 * 上传照片文件名称合法性验证
	 */
	public static boolean photoNameCheck(String photoName){
		if(null == photoName || "".equals(photoName)) return false;
		String[] pnArr = photoName.split("\\.");
		if(pnArr.length != 2) return false;
		if("jpg".equalsIgnoreCase(pnArr[1]) || "jpeg".equalsIgnoreCase(pnArr[1]) || "gif".equalsIgnoreCase(pnArr[1])){
			return true;
		}
		
		return false;
	}
	/**
	 * 将字符串转化为日期格式
	 * @param date date的格式为：yyyymmdd
	 * @return Date - 如果无法转换，则返回null
	 *
	 */
	public static Date formatSqlDate(String date)
	{		
		java.sql.Date return_Date = null;
		try
		{
			return_Date = util2Sql(CalendarUtil.dateFormat(date.replaceAll("-", ""),"yyyyMMdd"));
		}
		catch(Exception e)
		{
			return null;
		}
		
		return return_Date;
	}
	
	public static Date formatSqlDate(String date,String pattern)
	{		
		java.sql.Date return_Date = null;
		try
		{
			return_Date = util2Sql(CalendarUtil.dateFormat(date, pattern));
		}
		catch(Exception e)
		{
			return null;
		}
		
		return return_Date;
	}
	
	public static String convertGetMothodName(String s){
		if(null != s && s.length() >= 1){
			String headStr = s.substring(0,1);
			String endStr = s.substring(1,s.length());
			headStr = headStr.toUpperCase();
			return "get" + headStr + endStr;
		}
		return s;
	}
	
	public static int parseInt(String s){
		if(null != s && s.length() >= 1){
			try{
				return Integer.parseInt(s);
			}catch(Exception ex){
				return 0;
			}
		}
		return 0;
	}
	
	public static String getOrderNo(String segment, String sequence) {
		StringBuffer orderBuffer = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		String currentYear = String.valueOf(cal.get(Calendar.YEAR));
		orderBuffer.append(currentYear).append(segment);
		orderBuffer.append(leftFill(sequence, "0", 7));
		return orderBuffer.toString();
	}
	public static String leftFill(String sequence, String fill, int digit) {
		if (sequence == null || fill == null || "".equals(fill)) {
			return "";
		} else if (sequence.length() >= digit) {
			return sequence;
		}
		StringBuffer fillBuffer = new StringBuffer();
		fillBuffer.append(sequence);
		while (fillBuffer.length() < digit) {
			fillBuffer.insert(0, fill);
		}
		return fillBuffer.toString();
	}
	public static String formatAmountChinaPay(String amount) {
		String format = "";
		if (amount != null && !"".equals(amount)) {
			if(amount.indexOf(".") <= 0){
				amount += ".00";
			}
			format = leftFill(amount.replaceAll("\\".concat("."), ""), "0", 12);
		}
		return format;
	}
	public static String getStringDate(Calendar cal, String pattern) {
		String stringDate = "";
		if (cal != null && pattern != null && !"".equals(pattern)) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			stringDate = sdf.format(cal.getTime());
		}
		return stringDate;
	}
	
	/**
	 * double 保留两位小数
	 * @param f
	 * @return
	 */
	public static double doubleFormat(double f) {
		BigDecimal b = new BigDecimal(f);  
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	public static String arrayToString(String[] s){
		StringBuffer buff = new StringBuffer();
		String lp = "";
		for(String o : s){
			buff.append(lp).append(o.trim());
			lp = "#";
		}
		return buff.toString();
	}
	public static String[] strToArray(String s){
		if(null == s) return null;
		String[] arr = s.split("#");
		return arr;
	}
	
	/**
     * @see 验证手机号是否合法
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobile){  
        if (mobile.length() != 11)
        {
            return false;
        }else{
            /**
             * 移动号段正则表达式
             */
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
            /**
             * 联通号段正则表达式
             */
            String pat2  = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            /**
             * 电信号段正则表达式
             */
            String pat3  = "^((133)|(153)|(177)|(18[0,1,9])|(149)|(199))\\d{8}$";
            /**
             * 虚拟运营商正则表达式
             */
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            Pattern pattern1 = Pattern.compile(pat1);
            Matcher match1 = pattern1.matcher(mobile);
            boolean isMatch1 = match1.matches();
            if(isMatch1){
                return true;
            }
            Pattern pattern2 = Pattern.compile(pat2);
            Matcher match2 = pattern2.matcher(mobile);
            boolean isMatch2 = match2.matches();
            if(isMatch2){
                return true;
            }
            Pattern pattern3 = Pattern.compile(pat3);
            Matcher match3 = pattern3.matcher(mobile);
            boolean isMatch3 = match3.matches();
            if(isMatch3){
                return true;
            }
            Pattern pattern4 = Pattern.compile(pat4);
            Matcher match4 = pattern4.matcher(mobile);
            boolean isMatch4 = match4.matches();
            if(isMatch4){
                return true;
            }
            return false; 
        }
    }

    public static String getBirthdayByIdCard(String idCard) { 
    	if(null == idCard) return "";
        String idCardNumber = idCard.trim(); 
        int idCardLength = idCardNumber.length(); 
        String birthday = null; 
        if (idCardNumber == null || "".equals(idCardNumber)) { 
            return null; 
        } 
        if (idCardLength == 18) { 
            birthday = idCardNumber.substring(6, 10) + "-" 
                    + idCardNumber.substring(10, 12) + "-" 
                    + idCardNumber.substring(12, 14); 
        } 
        if (idCardLength == 15) { 
            birthday = "19" + idCardNumber.substring(6, 8) + "-" + idCardNumber.substring(8, 10) + "-" + idCardNumber.substring(10, 12); 
        } 
        return birthday; 
    } 
    
    public static String getArrToStr(Object[] arr) { 
        StringBuffer buff = new StringBuffer();
        String lp = "";
        for(Object s : arr){
        	buff.append(lp).append(String.valueOf(s));
        	lp = ",";
        }
        return buff.toString(); 
    }

 
	public static String getFileExtension(String originalFilename) {
		String[] arr =  originalFilename.split("\\.");
		if(arr.length>1){
			return arr[arr.length-1];
		}
		return "";
	} 
	
	public static String getUrlPrefix(String url) {
		String[] arr =  url.split("\\.");
		if(arr.length==4){
			return arr[0];
		}
		return "";
	} 
	
}