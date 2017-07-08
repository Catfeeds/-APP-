package com.hcb.xigou.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Managers;
import com.hcb.xigou.service.IManagersService;

import net.sf.json.JSONObject;

@Controller
public class LoginController extends BaseController{
	
	@Autowired
	IManagersService managersService;
	
	@RequestMapping("login")
	@ResponseBody
	public String loginIndex() {
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("nickname") == null || bodyInfo.get("password") == null|| bodyInfo.get("grade") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		
		if(bodyInfo.getString("grade").equals("3")){
			if (bodyInfo.get("store_uuid") == null) {
				json.put("result", 1);
				json.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonObject(json);
			}
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("nickname", bodyInfo.getString("nickname"));
		map.put("grade", bodyInfo.getString("grade"));
		map.put("password", bodyInfo.getString("password"));
		
		Managers managers = managersService.selectBynicknameAndGrade(map);
		if (managers==null) {
			json.put("result", 1);
			json.put("description", "登录失败，用户名或密码错误");
			return buildReqJsonObject(json);
		}else{
			if(bodyInfo.getString("grade").equals("3")){
				if(managers.getStoreUuid().equals(bodyInfo.getString("store_uuid"))){
					json.put("result", 0);
					json.put("description", "登录成功");
					json.put("store_uuid", managers.getStoreUuid());
					return buildReqJsonObject(json);
				}else{
					json.put("result", 1);
					json.put("description", "登录失败，用户名或密码错误");
					return buildReqJsonObject(json);
				}
			}else{
				json.put("result", 0);
				json.put("description", "登录成功");
				return buildReqJsonObject(json);
			}
		}
	}
}
