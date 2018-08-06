package com.connecticus.admin.util;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormatSymbols;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class AdminUtil {

	public static String[] getMonths(){
		String[] shortMonths = new DateFormatSymbols().getShortMonths();
		return shortMonths;
	}

	
	public static Properties loadPropertiesByFileName(String fileName) {
		

		Properties prop = new Properties();
		InputStream inputStream = AdminUtil.class.getResourceAsStream(fileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			System.out.println("IOException occured while loading " + fileName + e.getMessage());
		}
		return prop;
	}
	public static HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = 1L;

		};
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		return headers;
	}
}
