package com.hcb.xigou.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
public class JsonMap {

	
	  public static Map toMap(String jsonString) throws JSONException {

	        JSONObject jsonObject = JSONObject.fromObject(jsonString);
	        Map result = new HashMap();
	        Iterator iterator = jsonObject.keys();
	        String key = null;
	        String value = null;
	        
	        while (iterator.hasNext()) {

	            key = (String) iterator.next();
	            value = jsonObject.getString(key);
	            result.put(key, value);

	        }
	        return result;

	    }
	  
		public static String getContent(Map<String, String> sortedParams) {
			StringBuffer content = new StringBuffer();
			List<String> keys = new ArrayList<String>(sortedParams.keySet());
			Collections.sort(keys);
			int index = 0;
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				String value = sortedParams.get(key);
				if (StringUtils.areNotEmpty(key, value)) {
					content.append((index == 0 ? "" : ",") + "\""+key+"\"" + ":" + "\""+value+"\"");
					index++;
				}
			}
			return content.toString();
		}
	
}




