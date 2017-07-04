package com.hcb.xigou.util;

import java.io.IOException;
import java.net.URLEncoder;

public class GetCodeByOsc {
	
	   public static String getCodeByOsc(String qrData, int size) throws IOException {
	        qrData = URLEncoder.encode(qrData, "utf-8");
	        return "http://tool.oschina.net/action/qrcode/generate?data="+qrData
	                +"&output=image%2Fgif&error=Q&type="+size+"&margin=8&size=4";
	    }
}
