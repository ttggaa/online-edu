package com.education.test.httpClientTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientTest {

	public static void main(String[] args) {
		HttpClientTest httpClient = new HttpClientTest();
		try {
			String url = "http://210.76.67.52:8083/gdkjJXJY/Actions/Net/External/NetService/jxjy/teleeduOrgStuScoreAction.do?method=compProve&userID=393034356139666138313331303632636431633739613762386332393362376432633639636336386561303332353161&userPwd=66303130613633383663383336313462&cardNumber=393034356139666138313331303632636431633739613762386332393362376432633639636336386561303332353161&compId=393034356139666138313331303632636431633739613762386332393362376432633639636336386561303332353161&compName=333039316530626330356164363665326532646130653966346563373735303332303232633861316466643132323061&areaName=666665303638353761653635316337336239313833633231363539346131633966386331313863373637623234633030616232643437366563313037613365656339343666366336633030633565656139636666363537653039303064313636";
			String s = httpClient.executeGet(url);
			System.out.println(s);
//			参数：
//		    word: "歌曲名", //歌曲名 encodeURI
//		 
//		    format: "json", //返回数据格式，xml | json，默认xml
//		 
//		    callback: "Pub.music.searchResult", //固定值，返回jsonp格式
//			String word = URLEncoder.encode("心太软", "utf-8");
//			String gsName = URLEncoder.encode("任贤齐", "utf-8");
//			String url = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=" + word + "$$"+gsName+"$$$$";
////			String url = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=" + word + "$$"+gsName+"$$$$";
////			String url = "http://mp3.baidu.com/dev/api/?tn=getinfo&ct=0&word=" + word + "&ie=utf-8&format=xml";
//			String s = httpClient.executeGet(url);
//			System.out.println(s);
			
//			url = "http://box.zhangmen.baidu.com/bdlrc/391/39122.lrc";
//			String s2 = httpClient.executeGet(url);
//			System.out.println(s2);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String executeGet(String url) throws Exception {
		BufferedReader in = null;

		String content = null;
		try {
			// 定义HttpClient
			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"gbk"));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}
}
