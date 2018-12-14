package com.education.framework.util;

import java.io.IOException;
import java.util.Properties;


public class DealProperty {

	/**
	 * @param args
	 */
	private static Properties pro = null;

	public DealProperty() {
		setPro();
	}

	public DealProperty(String filename) {
		setPro(filename);
	}

	public static void setPro(String filename) {

		try {
			
			pro.load(Class.forName("com.education.framework.util.DealProperty")
					.getClassLoader().getResourceAsStream(filename));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Properties setPro() {

		try {
			if(null == pro){
				pro = new Properties();
				pro.load(Class.forName("com.education.framework.util.DealProperty")
						.getClassLoader()
						.getResourceAsStream("resource.properties"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}

	public static Properties getPro() {
		return pro;
	}

	public String getAttribute(String key, String defaultvalue) {
		return getPro().getProperty(key, defaultvalue);
	}
}
