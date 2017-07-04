package yuntongxun;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

public class SendTemplateSMS {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Random random = new Random();
	    HashMap<String, Object> result = null;
		String flag="";
		for(int i=0;i<6;i++){
			flag+=random.nextInt(10);
		}
		
		String phone = "";
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");
		restAPI.setAccount("aaf98f89488d0aad0148ab8e790f0d1c", "fda1ca24f48c49daa959a0f5f095de53");
		restAPI.setAppId("8a48b5515493a1b70154bc62a97926c0");// 鍒濆鍖栧簲鐢↖D
		result=   restAPI.sendTemplateSMS("15958520686","86916", new String[]{flag});

    	System.out.println("SDKTestSendTemplateSMS result=" + result);
		if("000000".equals(result.get("statusCode"))){
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}
}
