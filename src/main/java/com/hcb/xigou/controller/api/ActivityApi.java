package com.hcb.xigou.controller.api;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.RandomStringGenerator;
import com.hcb.xigou.util.StringToDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("activity")
public class ActivityApi extends BaseController{

	
	@Autowired
	IActivityZonesService activityZonesService;
	
	
	@RequestMapping(value = "selling/create" , method = RequestMethod.POST)
	public String sellingCreate(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("store_uuid")==null || bodyInfo.get("banner") == null || bodyInfo.get("title") == null||
				bodyInfo.get("good_uuid") == null || bodyInfo.get("description") == null || bodyInfo.get("rule_one") == null ||
				bodyInfo.get("rule_two") == null || bodyInfo.get("start_time") == null || bodyInfo.get("end_time") == null){
			json.put("result", 1);
			json.put("description", "请输入标题、轮播图、活动规则、满减条件等信息");
			return buildReqJsonObject(json);
		}
		String groups = StringToDate.sellingOfGroup("D", Timestamp.from(Instant.now()));
		JSONArray array = JSONArray.fromObject(bodyInfo.getString("good_uuid"));
		for (int i = 0; i < array.size(); i++) {
			if(array.get(i).toString() != null && !array.get(i).toString().equals("")){
				ActivityZones activity = new ActivityZones();
				activity.setCreateDatetime(Timestamp.from(Instant.now()));
				activity.setGoodUuid(array.get(i).toString());
				activity.setTitle(bodyInfo.getString("title"));
				activity.setBanner(bodyInfo.getString("banner"));
				activity.setDescription(bodyInfo.getString("description"));
				activity.setRuleOne(bodyInfo.getString("rule_one"));
				activity.setRuleTwo(bodyInfo.getString("rule_two"));
				activity.setStartTime(StringToDate.stringToDateStart(bodyInfo.getString("start_time")));
				activity.setEndTime(StringToDate.stringToDateStart(bodyInfo.getString("end_time")));
				activity.setStoreUuid(headInfo.getString("store_uuid"));
				activity.setType("selling");
				activity.setGroups(groups);
				try {
					activity.setActivityUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			    activityZonesService.insertSelective(activity);
			}
		}
		json.put("result", 0);
		json.put("description", "新建热销精选成功");
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "selling/change" , method = RequestMethod.POST)
	public String sellingChange(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("groups") == null){
			json.put("result", 1);
			json.put("description", "请输如必填参数");
			return buildReqJsonObject(json);
		}
	    String banner = null;
		String title = null;
		String description = null;
		String rule_one = null;
		String rule_two = null;
		Date start_time = null;
		Date end_time = null;
		if(bodyInfo.get("banner") != null){
			banner = bodyInfo.getString("banner");
		}
		if(bodyInfo.get("title") != null){
		    title = bodyInfo.getString("title");			
		}
		if(bodyInfo.get("description") != null){
			description = bodyInfo.getString("description");
		}
		if(bodyInfo.get("rule_one") != null){
			rule_one = bodyInfo.getString("rule_one");
		}
		if(bodyInfo.get("rule_two") != null){
			rule_two = bodyInfo.getString("rule_two");
		}
		if(bodyInfo.get("start_time") != null){
			start_time = StringToDate.stringToDateStart(bodyInfo.getString("start_time"));
		}
		if(bodyInfo.get("end_time") != null){
			end_time = StringToDate.stringToDateStart(bodyInfo.getString("end_time"));
		}
		String activityUuid = null;
		Map<String, Object> map = new HashMap<>();
		map.put("groups", bodyInfo.getString("groups"));
		List<ActivityZones> activitys = activityZonesService.selectBySellingOfGroups(map);
		for (ActivityZones zone : activitys) {
			if(zone != null){
				activityUuid = zone.getActivityUuid();
				zone.setBanner(banner);
				zone.setTitle(title);
				zone.setDescription(description);
				zone.setRuleOne(rule_one);
				zone.setRuleTwo(rule_two);
				zone.setStartTime(start_time);
				zone.setEndTime(end_time);
				activityZonesService.updateByPrimaryKeySelective(zone);
			}
		}
		if(bodyInfo.get("good_uuid") != null){
			JSONArray array = JSONArray.fromObject(bodyInfo.getString("good_uuid"));
			ActivityZones activity = activityZonesService.selectByActivityUuid(activityUuid);
			for (int i = 0; i < array.size(); i++) {
				if(array.get(i).toString() != null && !array.get(i).toString().equals("")){
					ActivityZones zone = new ActivityZones();
					zone.setCreateDatetime(Timestamp.from(Instant.now()));
					zone.setGoodUuid(array.get(i).toString());
					zone.setTitle(activity.getTitle());
					zone.setBanner(activity.getBanner());
					zone.setDescription(activity.getDescription());
					zone.setRuleOne(activity.getRuleOne());
					zone.setRuleTwo(activity.getRuleTwo());
					zone.setStartTime(activity.getStartTime());
					zone.setEndTime(activity.getEndTime());
					zone.setStoreUuid(activity.getStoreUuid());
					zone.setType("selling");
					zone.setGroups(activity.getGroups());
					try {
						zone.setActivityUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
					} catch (Exception e) {
						e.printStackTrace();
					}
					activityZonesService.insertSelective(zone);
				}
			}
		}
		json.put("result", 0);
		json.put("description", "编辑成功");
		return buildReqJsonObject(json);
	}
}
