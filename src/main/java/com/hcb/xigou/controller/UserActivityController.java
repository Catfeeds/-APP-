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
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.pojo.Goods;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.ISecondCategorysService;
import com.hcb.xigou.service.UserActivityService;
import com.hcb.xigou.util.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("userActivity/")
public class UserActivityController extends BaseController{
	
	@Autowired
	IActivityZonesService activityZonesService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	IFirstCategorysService firstCategorysServiceImpl;
	@Autowired
	ISecondCategorysService secondCategorysService;
	@Autowired
	GoodsService goodsService;
	
	
	/*
	 * 一级分类
	 * */
	@RequestMapping("searchfirstUuid")
	@ResponseBody
	public String firstUuid(){
		JSONObject json = new JSONObject();
		List<FirstCategorys> firstUuidList = new ArrayList<FirstCategorys>();
		firstUuidList = firstCategorysServiceImpl.firstUuid();
		if(firstUuidList!=null){
			json.put("result", 0);
			json.put("description", "查詢成功");
			json.put("firstUuidList", firstUuidList);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到goods信息");
			return buildReqJsonObject(json);
		}
	}

	/*
	 * 二级分类
	 */
	@RequestMapping("searchsecondUuid")
	@ResponseBody
	public String secondUuid() {
		JSONObject json = new JSONObject();
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		List<Goods> secondUuidList = new ArrayList<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstUuid", bodyInfo.getString("first_uuid"));
		secondUuidList = secondCategorysService.secondUuid(map);
		if (secondUuidList != null) {
			json.put("result", 0);
			json.put("description", "查詢成功");
			json.put("secondUuidList", secondUuidList);
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "未查询到goods信息");
			return buildReqJsonObject(json);
		}
	}
	
	/*
	 * 查询二级分类下的商品
	 * 
	 * */
	@RequestMapping("searchGoodUuid")
	@ResponseBody
	public String searchGoodUuid(){
		JSONObject json = new JSONObject();
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		List<Goods> searchGoodUuidList = new ArrayList<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(bodyInfo.get("second_uuid")!=null){
			if(!"".equals(bodyInfo.getString("second_uuid"))){
				map.put("secondUuid", bodyInfo.getString("second_uuid"));
				searchGoodUuidList = goodsService.searchGoodUuid(map);
				if(searchGoodUuidList!=null){
					json.put("result", 0);
					json.put("description", "查詢成功");
					json.put("searchGoodUuidList", searchGoodUuidList);
					return buildReqJsonObject(json);
				}else{
					json.put("result", 1);
					json.put("description", "未查询到goods信息");
					return buildReqJsonObject(json);
				}
			}else{
				json.put("result", 1);
				json.put("description", "未查询到goods信息");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "请输入正确参数");
			return buildReqJsonObject(json);
		}
		
	}
	
	/*
	 *添加活动内商品
	 */
	@RequestMapping("insertactivity")
	@ResponseBody
	public String insertActivity(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("store_uuid")==null||bodyInfo.get("banner") == null||
				bodyInfo.get("title") == null||bodyInfo.get("good_uuid") == null||
				bodyInfo.get("groups")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		
		JSONArray jsonArray = bodyInfo.getJSONArray("good_uuid");
		List<ActivityZones> listAct = new ArrayList<ActivityZones>();
		
		
		for (int i = 0; i < jsonArray.size(); i++) {
			ActivityZones activityZones = new ActivityZones();
			activityZones.setGoodUuid(jsonArray.getString(i));
			listAct.add(activityZones);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("groups",bodyInfo.getString("groups"));
		map.put("listAct", listAct);
		int num = userActivityService.selectByActiviAndGood(map);
		if(num>0){
			json.put("result", -1);
			json.put("description", "请勿添加重复的商品");
			return buildReqJsonObject(json);
		}
		
		for (int i = 0; i < jsonArray.size(); i++) {
			ActivityZones activityZones = new ActivityZones();
			String activityUuid = "";
			try {
				activityUuid = MD5Util.md5Digest(
						bodyInfo.getString("banner") + System.currentTimeMillis() + RandomStringUtils.random(8));
			} catch (Exception e) {
				e.printStackTrace();
			}
			activityZones.setGroups(bodyInfo.getString("groups"));
			activityZones.setTitle(bodyInfo.getString("title"));
			activityZones.setActivityUuid(activityUuid);
			activityZones.setGoodUuid(jsonArray.getString(i));
			activityZones.setBanner(bodyInfo.getString("banner"));
			activityZones.setStoreUuid(headInfo.getString("store_uuid"));
			listAct.add(activityZones);
		}

		int rs = userActivityService.insertByActivityUuids(listAct);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "添加成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整3");
			return buildReqJsonObject(json);
		}
	}
	
	
	
	
	/**
	 * 添加活动
	 * @return
	 */
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
		if (headInfo.get("store_uuid")==null||bodyInfo.get("banner") == null||
				bodyInfo.get("good_uuid") == null||bodyInfo.get("title") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("good_uuid");
		List<ActivityZones> listAct = new ArrayList<ActivityZones>();

		UserActivity userActivity = userActivityService.selectGroupsMax();

		String str = userActivity.getGroups();
		String num = (Integer.parseInt(str) + 1) + "";
		for (int i = 0; i < jsonArray.size(); i++) {
			ActivityZones activityZones = new ActivityZones();
			String activityUuid = "";
			try {
				activityUuid = MD5Util.md5Digest(
						bodyInfo.getString("banner") + System.currentTimeMillis() + RandomStringUtils.random(8));
			} catch (Exception e) {
				e.printStackTrace();
			}
			activityZones.setGroups(num);
			activityZones.setTitle(bodyInfo.getString("title"));
			activityZones.setActivityUuid(activityUuid);
			activityZones.setGoodUuid(jsonArray.getString(i));
			activityZones.setBanner(bodyInfo.getString("banner"));
			activityZones.setStoreUuid(headInfo.getString("store_uuid"));
			listAct.add(activityZones);
		}
		int rs = userActivityService.insertByActivityUuids(listAct);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "添加成功"+bodyInfo.getString("title"));
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整3");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("search")
	@ResponseBody
	public String search(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整66666");
			return buildReqJsonInteger(1, json);
		}
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整111111111111");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确22222222222");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		List<UserActivity> list = new ArrayList<UserActivity>();
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
			map.put("storeUuid", headInfo.getString("store_uuid"));
			list = userActivityService.searchUserActivityByMap(map);
			Integer count = 0;
			count = userActivityService.countUserActivityByMap(map);
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
		model.put("userActivityList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping("searchActivityGood")
	@ResponseBody
	public String searchActivityGood(){
		JSONObject json = new JSONObject();
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		ModelMap model = new ModelMap();
		List<Goods> ActivityGoodList = new ArrayList<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(bodyInfo.get("groups")!=null){
			if(!"".equals(bodyInfo.getString("groups"))){
				map.put("groups",bodyInfo.getString("groups"));
				ActivityGoodList = goodsService.searchActivityGood(map);
				if(ActivityGoodList!=null&&!"".equals(ActivityGoodList)){
					model.put("description", "查询成功");
					model.put("result",0);
					model.put("ActivityGoodList", ActivityGoodList);
					String a = buildReqJsonObject(model);
					a = a.replace("\"[", "[");
					a = a.replace("]\"", "]");
					return a;
				}else{
					json.put("result", 1);
					json.put("description", "未查询到goods信息");
					return buildReqJsonObject(json);
				}
			}else{
				json.put("result", 1);
				json.put("description", "未查询到goods信息");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "请输入正确参数");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public String delete() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("groups") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整111111");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groups", bodyInfo.getString("groups"));
		if (headInfo.getString("store_uuid") != null && !"".equals(headInfo.getString("store_uuid"))) {
			map.put("storeUuid", headInfo.getString("store_uuid"));
		}
		int rs = userActivityService.deleteByActivityUuid(map);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "删除成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "删除失败，该活动已经删除或者没有改活动");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("deletegood")
	@ResponseBody
	public String deletegood() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整111111");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodUuid", bodyInfo.getString("good_uuid"));
		if (headInfo.getString("store_uuid") != null && !"".equals(headInfo.getString("store_uuid"))) {
			map.put("storeUuid", headInfo.getString("store_uuid"));
		}
		map.put("groups", bodyInfo.getString("groups"));
		int rs = userActivityService.deleteByActivityGoodUuid(map);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "删除成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "删除活动商品失败");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("update_status")
	@ResponseBody
	public String updateActivityStatus() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("is_stop") == null
				|| bodyInfo.get("groups") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("isStop", bodyInfo.getInt("is_stop"));
		map.put("groups", bodyInfo.getString("groups"));
		int rs = 0;
		/*int num1 = activityZonesService.selectByActivityCount();
		int num2 = activityZonesService.selectByActivitySellingCount();
		if((num1+num2)>4){
			json.put("result", -1);
			json.put("descrsiption", "修改活动状态失败，超过可上架活动数量限制");
			return buildReqJsonObject(json);
		}else{*/
			rs = userActivityService.updateByActivityAndGoods(map);
			if (rs > 0) {
				json.put("result", 0);
				json.put("description", "更改Activity状态成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "更改Activity状态失败,请重试");
				return buildReqJsonObject(json);
			}
		//}
		
	}

	@RequestMapping("detail")
	@ResponseBody
	public String selectActivityid(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整11111");
			return buildReqJsonObject(json);
		}
		UserActivity userActivity =  userActivityService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if(userActivity!=null){
			json.put("result", 0);
			json.put("description", "查詢成功");
			json.put("userActivity", userActivity);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到Activity信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("updateActivity")
	@ResponseBody
	public String updateActivity(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("title") == null||bodyInfo.get("banner")==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		UserActivity userActivity = new UserActivity();
		int rs = 0;
		userActivity.setGroups(bodyInfo.getString("groups"));
		userActivity.setTitle(bodyInfo.getString("title"));
		userActivity.setBanner(bodyInfo.getString("banner"));
		rs = userActivityService.updateByActivity(userActivity);
		if (rs > 0) {
			json.put("result", 0);
			json.put("description", "更改Activity成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "更改Activity失败");
			return buildReqJsonObject(json);
		}
	}
}	

