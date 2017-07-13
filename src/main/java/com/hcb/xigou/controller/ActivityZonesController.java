package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.util.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("activityZones/")
public class ActivityZonesController extends BaseController{

	@Autowired
	IActivityZonesService activityZonesService;
	
	@RequestMapping("insert")
	@ResponseBody
	public String insert(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("title")==null||
			bodyInfo.get("image") == null||bodyInfo.get("store_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		ActivityZones activity = new ActivityZones();
		String activityUuid = "";
		try {
			activityUuid = MD5Util.md5Digest(bodyInfo.getString("store_uuid") + System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		activity.setActivityUuid(activityUuid);
		activity.setTitle(bodyInfo.getString("title"));
		activity.setCreateDatetime(new Date());
		activity.setImage(bodyInfo.getString("image"));
		activity.setType("coupon");
		activity.setStoreUuid(bodyInfo.getString("store_uuid"));
		activity.setIsStop("2");
		int rs = 0;
		rs = activityZonesService.insertSelective(activity);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加优惠活动成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加优惠活动失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("title")==null||bodyInfo.get("activity_uuid")==null||
			bodyInfo.get("image") == null||bodyInfo.get("store_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		ActivityZones activity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if(activity !=null){
			UUID uuid = UUID.randomUUID();
			String activityUuid= uuid.toString();
			activity.setActivityUuid(activityUuid);
			activity.setTitle(bodyInfo.getString("title"));
			activity.setUpdateDatetime(new Date());
			activity.setImage(bodyInfo.getString("image"));
			activity.setType("coupon");
			activity.setStoreUuid(bodyInfo.getString("store_uuid"));
			activity.setIsStop("2");
			int rs = 0;
			rs = activityZonesService.updateByActivityUuid(activity);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "编辑优惠活动成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "编辑优惠活动失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "没有此优惠活动，请重试");
			return buildReqJsonObject(json);
		}
		
	}
	
	
	@RequestMapping("isStop")
	@ResponseBody
	public String isStop(){
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
		ActivityZones activity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if(activity !=null){
			activity.setIsStop("1");
			activity.setUpdateDatetime(new Date());
			int rs = 0;
			rs = activityZonesService.updateByActivityUuid(activity);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "展示优惠活动成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "展示优惠活动失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "查询不到此优惠活动，请重试");
			return buildReqJsonObject(json);
		}
		
	}
	
	
	
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
		if (bodyInfo.get("banner_uuid") == null) {
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
			map.put("activityUuids", activityUuids);
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = activityZonesService.deleteByActivityUuids(map);
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
	
	@RequestMapping("detail")
	@ResponseBody
	public String detail(){
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
		ActivityZones activity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if(activity!=null){
			json.put("result", 0);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			json.put("activity", activity);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到activity信息");
			return buildReqJsonObject(json);
		}
	}
	
	
	@RequestMapping("search")
	@ResponseBody
	public String search(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();

		List<ActivityZones> list = new ArrayList<ActivityZones>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			/*if(bodyInfo.get("bannerName") == null){
				map.put("bannerName", bodyInfo.getString("bannerName"));
			}*/
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			
			list = activityZonesService.searchActivityByMap(map);
			Integer count = 0;
			count = activityZonesService.countActivityByMap(map);
			if (count % pageSize == 0) {
				Integer total = count / pageSize;
				Integer sign = 0;
				if (!total.equals(sign)) {
					if (pageIndex > total) {
						json.put("result", "1");
						json.put("description", "操作失败，请求页数大于总页数");
						return buildReqJsonObject(json);
					}
				}
				model.put("total", total);
				model.put("page", pageIndex);
			} else {
				Integer total = count / pageSize + 1;
				if (pageIndex > total) {
					json.put("result", "1");
					json.put("description", "操作失败，请求页数大于总页数");
					return buildReqJsonObject(json);
				}
				model.put("total", total);// 页码总数
				model.put("page", pageIndex);
			}
		}
		
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("activityList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
}
