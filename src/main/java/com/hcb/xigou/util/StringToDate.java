package com.hcb.xigou.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {

	public static Date stringToDate(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 2012-02-24
		date = java.sql.Date.valueOf(str);

		return date;
	}

	public static String dateToString(Date date, String type) {
		String str = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (type.equals("SHORT")) {
			// 07-1-18
			format = DateFormat.getDateInstance(DateFormat.SHORT);
			str = format.format(date);
		} else if (type.equals("MEDIUM")) {
			// 2007-1-18
			// format = DateFormat.getDateInstance(DateFormat.MEDIUM);
			str = format.format(date);
		} else if (type.equals("FULL")) {
			// 2007年1月18日 星期四
			format = DateFormat.getDateInstance(DateFormat.FULL);
			str = format.format(date);
		}
		return str;
	}

	public static String dateToStringTime(Date date) {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = null;
		str = time.format(date);
		return str;
	}

	public static Date stringtoDateTime(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 2016-02-24 14:25:25
		date = java.sql.Date.valueOf(str);
		return date;
	}

	public static Date stringToDateStart(String str) {

		Date rcreate = new Date();
		java.text.SimpleDateFormat newstr = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			rcreate = newstr.parse(str);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return rcreate;

	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String str = null;
		str = time.format(date);
		return str;
	}
}
