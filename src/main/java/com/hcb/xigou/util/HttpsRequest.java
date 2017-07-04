package com.hcb.xigou.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public  class HttpsRequest {

	 public  String testPost(String urlStr , String xmlObj) {
		  String result = "";
       try {
           URL url = new URL(urlStr);
           URLConnection con = url.openConnection();
           con.setDoOutput(true);
        //   con.setRequestProperty("Pragma:", "no-cache");
         //  con.setRequestProperty("Cache-Control", "no-cache");
           con.setRequestProperty("Content-Type", "text/xml");

           OutputStreamWriter out = new OutputStreamWriter(con
                   .getOutputStream());    
           String xmlInfo = xmlObj;
          // System.out.println("urlStr=" + urlStr);
           System.out.println("xmlInfo=" + xmlInfo);
           out.write(new String(xmlInfo.getBytes("UTF-8")));
           out.flush();
           out.close();
           BufferedReader br = new BufferedReader(new InputStreamReader(con
                   .getInputStream()));
           String line = "";
           for (line = br.readLine(); line != null; line = br.readLine()) {
           	result += line;
           	
              System.out.println(line);
           }
          
       } catch (MalformedURLException e) {
           e.printStackTrace();
          
       } catch (IOException e) {
           e.printStackTrace();
           
       }
       return result;
   }
}
