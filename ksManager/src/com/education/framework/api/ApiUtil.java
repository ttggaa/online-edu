package com.education.framework.api;

import java.io.IOException;
import java.util.Map;

import org.jdom.JDOMException;

import com.education.framework.util.HttpClientUtil;
import com.education.framework.util.XMLUtil;

public class ApiUtil {

	/**
	 * 通过电话号查询归属地
	 * @return <!--  op运营商；p省；c城市；powered by apifree.net  -->
	 */
	public Map<String,String> findAddressByPhone(String phone){
		if(null == phone || "".equals(phone)) return null;
		StringBuffer url = new StringBuffer("http://www.apifree.net/mobile/");
		url.append(phone).append(".xml");
		String content = HttpClientUtil.httpGet(url.toString());
		Map<String, String> xml = null;
		try {
			xml = XMLUtil.doXMLParse(content);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xml;
	}
	/**
	 * ip地址查询归属地
	 * @return <!--  co国家或者公司；p省；c城市；powered by apifree.net  -->
	 */
	public Map<String,String> findAddressByIp(String ip){
		if(null == ip || "".equals(ip)) return null;
		StringBuffer url = new StringBuffer("http://www.apifree.net/ip/");
		url.append(ip).append(".xml");
		String content = HttpClientUtil.httpGet(url.toString());
		Map<String, String> xml = null;
		try {
			xml = XMLUtil.doXMLParse(content);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	public static void main(String[] args) {
		ApiUtil api = new ApiUtil();
		Map<String, String> xml = api.findAddressByPhone("13614093438");
		System.out.println(xml.get("c"));
	}
}
