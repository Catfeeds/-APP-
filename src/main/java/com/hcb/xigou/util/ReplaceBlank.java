package com.hcb.xigou.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ReplaceBlank {

	 public static String replaceBlank(String str) {
		  String dest = "";
		  if (str!=null) {
		   Pattern p = Pattern.compile("\"");
		   Matcher m = p.matcher(str);
		   dest = m.replaceAll("");
		  }
		  return dest;
		 }        
}
