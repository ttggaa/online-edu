package com.edufe.framework.common;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	public static String toJson(Object obj){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "";
	}
	
	public static Object toObject(String jsonData, Class c) throws JsonParseException, JsonMappingException, IOException{
		Object o = new ObjectMapper().readValue(jsonData, c);
		return o;
	}
}
