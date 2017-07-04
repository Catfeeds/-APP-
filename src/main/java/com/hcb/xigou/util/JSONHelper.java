package com.hcb.xigou.util;

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONHelper {
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;
	public static void getJSON(){
		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
			jsonGenerator.writeObject("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
