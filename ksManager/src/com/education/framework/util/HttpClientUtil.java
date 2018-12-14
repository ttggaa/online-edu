package com.education.framework.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static String httpGet(String urlPath) {
		String ret = "";
		// 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();  
        // 创建Get方法实例     
        HttpGet httpgets = new HttpGet(urlPath);    
        HttpResponse response;
		try {
			response = httpclient.execute(httpgets);
		
	        HttpEntity entity = response.getEntity();    
	        if (entity != null) {    
	            ret = EntityUtils.toString(entity);
	            httpgets.abort();    
	        }  
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			ret += "[ClientProtocolException]";
		} catch (IOException e) {
			e.printStackTrace();
			ret += "[IOException]";
		}    
		return ret;
	}
}
