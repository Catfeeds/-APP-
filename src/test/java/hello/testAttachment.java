package hello;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class testAttachment {
	
	public static void main(String[] args){  
        try{  
            String boundary = "20160601"; //Could be any string  
            String Enter = "\r\n";
           
            File xml = new File("C:/Users/Administrator/Desktop/6515.jpg");  
            FileInputStream fis = new FileInputStream(xml);  
              
            URL url = new URL("http://120.26.64.40:8080/living/media/file_upload?user_uuid=b78576329192051ed87a3178311afadb&password=eff8fc6036adcdb99e2bc34bef0aa9ff");  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setRequestMethod("POST");  
            conn.setUseCaches(false);  
            conn.setInstanceFollowRedirects(true);  
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);   
            conn.connect();  

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());  

            String part1 =  "--" + boundary + Enter  
                    + "Content-Type:image/png" + Enter  
                    + "Content-Disposition: form-data; filename=\""+xml.getName()+"\"; name=\"file\"" + Enter + Enter;  

            String part2 = Enter  
                    + "--" + boundary + Enter  
                    + "Content-Type: text/plain" + Enter  
                    + "Content-Disposition: form-data; name=\"dataFormat\"" + Enter + Enter  
                    + "hk" + Enter  
                    + "--" + boundary + "--";  
            
            byte[] xmlBytes = new byte[fis.available()];  
            fis.read(xmlBytes);  
 
            dos.writeBytes(part1);  
            dos.write(xmlBytes);  
            dos.writeBytes(part2);  

            dos.flush();  
            dos.close();  
            fis.close();  
            System.out.println("status code: "+conn.getResponseCode());  
            conn.disconnect();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
    }  
}
