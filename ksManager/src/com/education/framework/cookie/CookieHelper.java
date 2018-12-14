package com.education.framework.cookie;


import javax.servlet.http.Cookie;

import com.education.framework.util.CommonTools;
import com.education.framework.util.Escape;

public class CookieHelper {
	//public final static String DOMAIL = "localhost";
	
	public final static int MAXAGE = 8 * 60 * 60;

	public static String getCookieValue(String key, Cookie[] cookies) {
		String value = "";
		for (Cookie cookie : cookies) {
			//cookie.setDomain(DOMAIL);
			cookie.setSecure(false);
			if (key.equals(cookie.getName())) {
				value = Escape.unescape(cookie.getValue());
				cookie.setMaxAge(MAXAGE);
				break;
			}
		}
		return value;
	} 

	public static Cookie buildCookie(String key, String value) {
		Cookie cookie = new Cookie(key, Escape.escape(value));
		//cookie.setDomain(DOMAIL);
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setMaxAge(MAXAGE);
		return cookie;
	}
	
	public static Cookie buildCookie(String key, String value, String callback) {
		Cookie cookie = new Cookie(key, Escape.escape(value));
		String domain = callback.substring(0,callback.indexOf(":"));
		cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setMaxAge(MAXAGE);
		return cookie;
	}
	
	public static Cookie removeCookie(Cookie cookie, String key) {
		if(cookie.getName().contains(key)){
			//cookie.setDomain(DOMAIL);
			cookie.setPath("/");
			cookie.setSecure(false);
			cookie.setMaxAge(0);
			return cookie;
		}
		return null;
	}
	
	public static String[] getCookieValueViewCols(String key, Cookie[] cookies ,String[] viewColsCheckbox) {
		String value = "";
		for (Cookie cookie : cookies) {
			//cookie.setDomain(DOMAIL);
			cookie.setSecure(false);
			if (key.equals(cookie.getName())) {
				value = Escape.unescape(cookie.getValue());
				cookie.setMaxAge(MAXAGE);
				break;
			}
		}
		
		if(value != null && !"".equals(value) && !"[null]".equals(value)){
			String[] cookieViewColsCheckbox = CommonTools.strToArray(value);
			return cookieViewColsCheckbox;
		}
		return viewColsCheckbox;
	} 

}
