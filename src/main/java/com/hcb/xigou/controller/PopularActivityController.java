package com.hcb.xigou.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.dto.SecondCategorys;

import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.ISecondCategorysService;
import com.hcb.xigou.service.PopularActivityService;
import com.hcb.xigou.util.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("popularActivity/")
public class PopularActivityController extends BaseController{
	
	@Autowired
	IActivityZonesService activityZonesService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ISecondCategorysService secondCategorysService;
	@Autowired
	IFirstCategorysService firstCategorysService;
	@Autowired
	PopularActivityService popularActivityService;
	
	@RequestMapping("search")
	@ResponseBody
	public String search(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确2");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整3");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确4");
			return buildReqJsonObject(json);
		}
		
		ModelMap model = new ModelMap();
		List<PopularActivity> list = new ArrayList<PopularActivity>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			list = popularActivityService.searchPopularByMap(map);
			int count = 0;
			count = activityZonesService.countPopularByMap(map);
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
				model.put("count", count);
			}
		}
		
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("popularActivityList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
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
			int rs = activityZonesService.deleteByPopular(map);
			if(rs >= 1){
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
	public String updateActivityStatus() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null || bodyInfo.get("is_stop") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		ActivityZones  activityZones = new ActivityZones();
		
		activityZones.setActivityUuid( bodyInfo.getString("activity_uuid"));
		if("1".equals(bodyInfo.getString("is_stop"))){
			activityZones.setIsStop("2");
		}
		if("2".equals(bodyInfo.getString("is_stop"))){
			activityZones.setIsStop("1");
		}
		int rs = 0;
		/*int num1 = activityZonesService.selectByActivityCount();
		int num2 = activityZonesService.selectByActivitySellingCount();
		if((num1+num2)>4){
			json.put("result", -1);
			json.put("descrsiption", "修改活动状态失败，超过可上架活动数量限制");
			return buildReqJsonObject(json);
		}else{*/
			rs = activityZonesService.updateByPrimaryKeySelective(activityZones);
			if (rs >= 1) {
				json.put("result", 0);
				json.put("description", "更改Activity状态成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "更改Activity状态失败，请重试");
				return buildReqJsonObject(json);
			}
		//}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String updatePopular() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null || bodyInfo.get("title") == null
				||bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		ActivityZones activityZones = new ActivityZones();
		activityZones.setActivityUuid( bodyInfo.getString("activity_uuid"));
		activityZones.setTitle(bodyInfo.getString("title"));
		activityZones.setGoodUuid(bodyInfo.getString("good_uuid"));
		int rs = 0;
		rs = activityZonesService.updateByPrimaryKeySelective(activityZones);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "更改Activity成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "更改Activity失败");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public String selectPopularActivityId(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		ActivityZones popularActivity = activityZonesService.selectByPopularId(bodyInfo.getString("activity_uuid"));
		if(popularActivity!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("popularActivity", popularActivity);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到popularActivity信息");
			return buildReqJsonObject(json);
		}
	}
	
	
	@RequestMapping("selectgood")
	@ResponseBody
	public String selectgood(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		List<SecondCategorys> second = secondCategorysService.selectAll();
		if(second!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("second", second);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到商品信息");
			return buildReqJsonObject(json);
		}
	}
	
	
	@RequestMapping("insert")
	@ResponseBody
	public String insert(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("title") == null||bodyInfo.get("good_uuid") == null||
				headInfo.get("store_uuid")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}else{
			String activityUuid = "";
			try {
				activityUuid = MD5Util.md5Digest(bodyInfo.getString("title") + 
						System.currentTimeMillis() + RandomStringUtils.random(8));
			} catch (Exception e) {
			e.printStackTrace();
			}
			
			ActivityZones  activityZones = new ActivityZones();
			activityZones.setActivityUuid(activityUuid);
			activityZones.setGoodUuid(bodyInfo.getString("good_uuid"));
			activityZones.setTitle(bodyInfo.getString("title"));
			activityZones.setType("host");
			activityZones.setStoreUuid(headInfo.getString("store_uuid"));
			
			int rs = 0;
			rs = activityZonesService.insertSelective(activityZones);
			if (rs >= 1) {
				json.put("result", 0);
				json.put("description", "添加Activity成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "添加Activity失败，请重试");
				return buildReqJsonObject(json);
			}
		}
		
	}
	
}
