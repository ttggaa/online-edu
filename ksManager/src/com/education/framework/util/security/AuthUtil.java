package com.education.framework.util.security;

import java.util.HashMap;
import java.util.Map;


public class AuthUtil {

	public static Map<String,String> TOKEN_MAP = new HashMap<String,String>();
	
	public static boolean checkToken(String token){
		if(null == token) return false;
		return TOKEN_MAP.containsKey(token);
	}

}
