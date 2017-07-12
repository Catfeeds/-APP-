package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.service.PopularActivityService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("popularActivity/")
public class PopularActivityController extends BaseController{
	
	@Autowired
	PopularActivityService  popularActivityService;
	
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("activity_uuid");
		List<String> activityUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			activityUuids.add(jsonArray.getString(i));
		}
		if(activityUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("activityUuids",activityUuids);
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = popularActivityService.deleteByActivityUuids(map);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "删除成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("update_is_stop")
	@ResponseBody
	public String updateActivityStatus(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null||bodyInfo.get("good_status") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		PopularActivity popularActivity =  popularActivityService.selectByPopularActivityUuid(bodyInfo.getString("activity_uuid"));
		Map<String,Object> map = new HashMap<String,Object> ();
		if(popularActivity!=null){
			map.put("activityUuid", bodyInfo.getString("activity_uuid"));
			map.put("isStop",bodyInfo.getString("is_stop"));
			map.put("updateDatetime",new Date());
			int rs = 0;
			rs = popularActivityService.updateByPopularActivityIsSTop(map);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "更改Activity状态成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "更改Activity状态失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "更改Activity状态失败，未查询到Activity信息");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("detail")
	@ResponseBody
	public String selectPopularActivityId(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		PopularActivity popularActivity = popularActivityService.selectByPopularActivityId(bodyInfo.getString("activity_uuid"));
		if(popularActivity!=null){
			json.put("result", 0);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			json.put("popularActivity", popularActivity);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到popularActivity信息");
			return buildReqJsonObject(json);
		}
	}
}
