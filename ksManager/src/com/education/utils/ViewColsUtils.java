package com.education.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.education.contants.ModelConstants;
import com.education.framework.cookie.CookieHelper;
import com.education.framework.util.CommonTools;


public class ViewColsUtils {
	//code ,name ,defaultViewFlag, width
	public static List<String[]> getViewColsList(String cookieKey ,String[][] viewcolsTemp,HttpServletRequest request,HttpServletResponse response) {
		String[] viewColsCheckbox = request.getParameterValues("viewColsCheckbox");
		String[] cookieViewColsCheckbox = CookieHelper.getCookieValueViewCols(cookieKey, request.getCookies() ,viewColsCheckbox);
		
		List<String[]> list = new ArrayList<String[]>();
		String[][] viewcols = ModelConstants.getViewCols(viewcolsTemp);
		for(String[] arr : viewcols){
			if(null != cookieViewColsCheckbox && cookieViewColsCheckbox.length > 0){
				arr[2] = "0";
				for(int j=0;j<cookieViewColsCheckbox.length;j++){
					if(arr[0].equals(cookieViewColsCheckbox[j])){
						arr[2] = "1";
						break;
					}
				}
			}
			
			list.add(arr);
		}
		if(null != cookieViewColsCheckbox){
			String s = CommonTools.arrayToString(cookieViewColsCheckbox);
			response.addCookie(CookieHelper.buildCookie(cookieKey, s));
		}
		return list;
	}
	
	public static List<String[]> getExpColsList(String cookieKey ,String[][] expcolsTemp,HttpServletRequest request,HttpServletResponse response) {
		String[] expColsCheckbox = request.getParameterValues("expColsCheckbox");
		String[] cookieExpColsCheckbox = CookieHelper.getCookieValueViewCols(cookieKey, request.getCookies() ,expColsCheckbox);
		
		List<String[]> list = new ArrayList<String[]>();
		String[][] expcols = ModelConstants.getViewCols(expcolsTemp);
		for(String[] arr : expcols){
			if(null != cookieExpColsCheckbox && cookieExpColsCheckbox.length > 0){
				arr[2] = "0";
				for(int j=0;j<cookieExpColsCheckbox.length;j++){
					if(arr[0].equals(cookieExpColsCheckbox[j])){
						arr[2] = "1";
						break;
					}
				}
			}
			
			list.add(arr);
		}
		if(null != cookieExpColsCheckbox){
			String s = CommonTools.arrayToString(cookieExpColsCheckbox);
			response.addCookie(CookieHelper.buildCookie(cookieKey, s));
		}
		return list;
	}
}
