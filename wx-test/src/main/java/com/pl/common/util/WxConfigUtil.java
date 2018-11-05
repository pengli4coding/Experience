package com.pl.common.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class WxConfigUtil {
	
	private static Configuration config = null;
	
	public static void init(String xmlPath) {
		System.out.println("init, xmlPath="+xmlPath);
		try {
			config = new XMLConfiguration(xmlPath);
		} catch (ConfigurationException e) {
			System.out.println("init dmcconfig error.[path:" + xmlPath + "]");
		}
	}
	
	public static String getString(String key) {
		return config.getString(key);
	}
	
	public static int getInt(String key) {
		return config.getInt(key);
	}
	
	public static long getLong(String key) {
		return config.getLong(key);
	}
	
	public static String getAppId() {
		return getString("appid");
	}
	
	public static String getSecret() {
		return getString("secret");
	}
	
	public static String getFormId() {
		return getString("formIds.formId1");
	}
	
}
