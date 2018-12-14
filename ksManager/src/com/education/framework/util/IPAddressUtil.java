package com.education.framework.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class IPAddressUtil {
	private String ip;
	private String mac;

	public IPAddressUtil(String ip) {
		this.ip = ip;
	}

	public IPAddressUtil() {
		this.ip = "0.0.0.0";
	}

	public String getMac() {
		try {
			Process p = Runtime.getRuntime().exec("arp -a " + this.ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream(),"utf-8");
			LineNumberReader input = new LineNumberReader(ir);
			p.waitFor();
			boolean flag = true;
			String ipStr = this.ip;
			String str = input.readLine();
			System.out.println(str);
			if (str != null) {
				if (str.indexOf(ipStr) > 1) {
					int temp = str.indexOf("at");
					this.mac = str.substring(temp + 3, temp + 20);
				}
			} else{
				flag = false;
			}
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		return this.mac;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public static void main(String[] args) {
		IPAddressUtil ip = new IPAddressUtil("172.16.0.2");
		System.out.println("----------" + ip.getMac());
	}
}