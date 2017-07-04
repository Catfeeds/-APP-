package com.hcb.xigou.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;


public class CookieUtil {
	

	public static String cookie(String name,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}			
		}
		return null;
	}
	

	public static void cookie(String name, String value,HttpServletResponse response) {
		Cookie c = new Cookie(name,value);
		c.setMaxAge(60*60*24*365);
		//String host = request.getServerName();
		//c.setDomain(".tc.com");
		c.setPath("/");
		response.addCookie(c);
	}
	
	/**
	 * 60*60*24*365
	 * @param name
	 * @param value
	 * @param cookieSaveTime
	 */
	public static void cookie(String name, String value,int cookieSaveTime,HttpServletResponse response) {
		Cookie c = new Cookie(name,value);
		c.setMaxAge(cookieSaveTime);
		//String host = request.getServerName();
		//c.setDomain(".tc.com");
		c.setPath("/");
		response.addCookie(c);
	}
}
