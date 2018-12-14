package com.education.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtils {
	
	// 进行加法运算
	
	public static double add(String v1, String v2) {
		if(null == v1) v1 = "0";
		if(null == v2) v2 = "0";
		if("".equals(v1)) v1 = "0";
		if("".equals(v2)) v2 = "0";
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	// 进行减法运算
	public static double sub(String v1, String v2) {
		if(null == v1) v1 = "0";
		if(null == v2) v2 = "0";
		if("".equals(v1)) v1 = "0";
		if("".equals(v2)) v2 = "0";
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}

	// 进行加法运算

	public static double mul(String d1, String d2) { // 进行乘法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}

	// 进行除法运算

	public static double div(String d1, String d2, int len) {// 进行除法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		if(b2.doubleValue() == 0) return 0;
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// 进行四舍五入操作

	public static double round(String d, int len) { // 进行四舍五入操作
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static String parsetStr(double d){
		if(isDecimal(String.valueOf(d))){
			DecimalFormat df = new DecimalFormat("##########0.00");  
			return df.format(d); 
		}else{
			DecimalFormat df = new DecimalFormat("##########0");  
			return df.format(d); 
		}
	}
	
	public static String parsetStr2(String d){
		if(null == d) return "";
		if(isDecimal(d)){
			DecimalFormat df = new DecimalFormat("##########0.00");  
			return df.format(Double.parseDouble(d)); 
		}else{
			return d; 
		}
	}
	
	public static boolean isDecimal(String num){
        boolean isdecimal = false;
        if(null == num) return isdecimal; 
        if (num.contains(".")) {
        	String[] arr = num.split("\\.");
        	if(arr.length == 2){
        		if(Double.parseDouble(arr[1]) >= 0){
        			isdecimal=true;
        		}
        	}
        }else{
        	try{
        		double temp = Double.parseDouble(num);
        		isdecimal=true;
        	}catch(Exception ex){
        		isdecimal=false;
        	}
        }
        return isdecimal;
    }
}
