package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.dto.Goods;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.ISecondCategorysService;
import com.hcb.xigou.service.UserActivityService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("userActivity/")
public class UserActivityController extends BaseController{
	
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	IFirstCategorysService firstCategorysServiceImpl;
	@Autowired
	ISecondCategorysService secondCategorysService;
	@Autowired
	GoodsService goodsService;
	
	
	@RequestMapping("selectAll")
	@ResponseBody
	public String selectAll(){
		JSONObject json = new JSONObject();
		List<UserActivity> firstUuidList = new ArrayList<UserActivity>();
		firstUuidList = userActivityService.selectAll();
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
	
	
	@RequestMapping("search")
	@ResponseBody
	public String search(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
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

		List<UserActivity> list = new ArrayList<UserActivity>();
		List<Map<String,Goods>> listGood = new ArrayList<Map<String,Goods>>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> mapGood = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			
			list = userActivityService.searchUserActivityByMap(map);
			//map.put(key, value)
			/*for(int i = 0;i<list.size();i++){
				listGood.add((Map<String,Goods>)mapGood.put(list.get(i).getActivityUuid(),goodsService.searchGood(list.get(i).getActivityUuid())));
			}*/
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
		//model.put("listGood", listGood);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
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
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstUuid", bodyInfo.getString("first_uuid"));*/
		secondUuidList = secondCategorysService.secondUuid(bodyInfo.getString(bodyInfo.getString("first_uuid")));
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
	}
	/*
	 *添加活动内商品
	 */
	@RequestMapping("insert")
	@ResponseBody
	public String insertActivity(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("store_uuid")==null||bodyInfo.get("banner") == null||
				bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("good_uuid");
		List<String> goodUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			goodUuids.add(jsonArray.getString(i));
		}
		if(goodUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("goodUuids", "goodUuids");
			map.put("banner",bodyInfo.getString("banner"));
			map.put("storeUuid",bodyInfo.getString("store_uuid"));
			
			int rs = userActivityService.insertByActivityUuids(map);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "添加成功");
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
	
	
/*
	@RequestMapping("searchGood")
	@ResponseBody
	public String searchGood(){
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
		ModelMap model = new ModelMap();
		List<Goods> list = new ArrayList<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(bodyInfo.get("phone")!=null){
			if(!"".equals(bodyInfo.getString("phone"))){
				map.put("phone",bodyInfo.getString("phone"));
			}
		}
		
		list =  goodsService.searchGood(bodyInfo.getString("activity_uuid"));
		if(list!=null){
			model.put("description", "查询成功");
			model.put("result",0);
			model.put("searchGoodList", list);
			String a = buildReqJsonObject(model);
			a = a.replace("\"[", "[");
			a = a.replace("]\"", "]");
			return a;
		}else{
			String a = buildReqJsonObject(model);
			a = a.replace("\"[", "[");
			a = a.replace("]\"", "]");
			return a;
		}
		
	}
	*/
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
			int rs = userActivityService.deleteByActivityUuid(map);
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
	
	@RequestMapping("update_status")
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
		UserActivity userActivity =  userActivityService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		Map<String,Object> map = new HashMap<String,Object> ();
		if(userActivity!=null){
			map.put("activityUuid", bodyInfo.getString("activity_uuid"));
			map.put("goodStatus",bodyInfo.getString("good_status"));
			map.put("updateDatetime",new Date());
			int rs = 0;
			rs = userActivityService.updateByActivityAndGoods(map);
			if(rs >= 1){
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
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
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
	
	
	
	
	
	
	
	
	
	@RequestMapping("update")
	@ResponseBody
	public String updateActivity(){
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
		UserActivity userActivity =  userActivityService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		Map<String,Object> map = new HashMap<String,Object> ();
		if(userActivity!=null){
			map.put("activityUuid", bodyInfo.getString("activity_uuid"));
			map.put("goodStatus",bodyInfo.getString("good_status"));
			map.put("updateDatetime",new Date());
			int rs = 0;
			rs = userActivityService.updateByActivityAndGoods(map);
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
	
	
}
