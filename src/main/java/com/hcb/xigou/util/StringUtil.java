package com.hcb.xigou.util;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;


public class StringUtil extends StringUtils {

	/**
	  * 判断字符串是否是整数
	  */
	 public static boolean isInteger(String value) {
	  try {
	   Integer.parseInt(value);
	   return true;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

	 /**
	  * 判断字符串是否是浮点数
	  */
	 public static boolean isDouble(String value) {
	  try {
	   Double.parseDouble(value);
	   if (value.contains("."))
	    return true;
	   return false;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

	 /**
	  * 判断字符串是否是数字
	  */
	 public static boolean isNumber(String value) {
	  return isInteger(value) || isDouble(value);
	 }
	 
	 /**
	  * 判断数字是否为空
	  * @param value
	  * @return
	  */
	 public static boolean isNullOrEmptyString(Number value){
		 if(null == value || "".equals(value)){
			 return false;
		 }else{
			 return true;
		 }
	 }
	 
	 /**
	  * 根据规则截取字符串
	  * @param str1 目标字符串
	  * @param str2 匹配的字符串
	  * @param str3 前缀字符串
	  * @param str4 后缀字符串
	  * @return
	  */
	 public static String returnSub(String str1,String str2,String str3,String str4){
		 if(StringUtil.isNotEmpty(str1)){
			 str2=str3+str2+str4;
			 if(str1.indexOf(str2)!=-1){
				 return str1.substring(str1.indexOf(str2)+str2.length(), str1.indexOf("|",str1.indexOf(str2)+1));
			 }else{
				 return null;
			 }
		 }else{
			 return null;
		 }
	 }
	 
	 /**
	  * 根据规则截取字符串
	  * @param str1 目标字符串
	  * @param str2 匹配的字符串
	  * @return
	  */
	 public static String returnSub(String str1,String str2){
		 return returnSub(str1,str2,"|","_");
	 }
	 
	public static boolean isJsonArray(String args)  {
		try {
			JSONArray jsonArray = JSONArray.fromObject(args);
			for (int i = 0; i < jsonArray.size(); i++) {
				String a =  (String) jsonArray.get(i);
			}
		} catch (JSONException e) {// 抛错 说明JSON字符不是数组或根本就不是JSON
				return false;
		}
		return true;
	}
	 
}
