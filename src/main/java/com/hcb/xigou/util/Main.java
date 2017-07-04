package com.hcb.xigou.util;


import com.iflytek.voicecloud.client.LfasrClient;
import com.iflytek.voicecloud.model.LfasrType;
import com.iflytek.voicecloud.model.Message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Main {
	public static void main(String[] args) throws Exception {
		//上传音频文件
		//获取转写文字结果
		String  taskid = upload("C:/Users/Administrator/Desktop/1.wav"); 
		System.out.println("taskid:"+taskid);
		try {
			Thread.sleep(5000);
			String onebest = result(taskid);
			System.out.println(onebest);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String upload(String file) throws Exception {
		String appid =  "58635133";       //您的appid，如"5848d773"
		String secret_key = "5adfc9d287374d8e7159f1c2ee0bc299";  //您的secret_key，如"b849c87a8bc2c7ww68b6dfbddee6dc35"
		LfasrType type = LfasrType.LFASR_STANDARD_RECORDED_AUDIO;
		LfasrClient client = LfasrClient.InitClient(appid, secret_key, type);
		Message message = client.lfasr_upload(file);  //需要转写的音频文件的路径，如"E:\\20160716am-as-shenzhen-Alex.mp3"
		JSONObject map = JSONObject.fromObject(message);
		String data = map.getString("data");
		return data;
	}

	public static String result(String taskid) throws Exception {
		String appid = "58635133";       //您的appid，如"5848d773"
		String secret_key = "5adfc9d287374d8e7159f1c2ee0bc299";  //您的secret_key，如"b849c87a8bc2c7ww68b6dfbddee6dc35"
		LfasrType type = LfasrType.LFASR_STANDARD_RECORDED_AUDIO;
		LfasrClient client = LfasrClient.InitClient(appid, secret_key, type);
		Message message = client.lfasr_get_result(taskid);
		StringBuffer onebests = new StringBuffer();
		System.out.println("+++message+++"+message);
		JSONObject map = JSONObject.fromObject(message);
		int ok = map.getInt("ok");
		if(ok == 0){
			String map1 = map.getString("data");
			JSONArray arrays = JSONArray.fromObject(map1);
			for (int i = 0; i < arrays.size(); i++) {
				JSONObject data = JSONObject.fromObject(arrays.get(i));
				String onebest = data.getString("onebest");
				onebests.append(onebest);
			}
			return onebests.toString();
		}else{
			String failed = map.getString("failed");
			return failed;
		}
	}
}
