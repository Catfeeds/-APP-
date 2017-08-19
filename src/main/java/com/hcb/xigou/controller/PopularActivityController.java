package com.hcb.xigou.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.pojo.Goods;
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
	PopularActivityService  popularActivityService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ISecondCategorysService secondCategorysService;
	@Autowired
	IFirstCategorysService firstCategorysService;
	@Autowired
	IActivityZonesService activityZonesService; 
	
	
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

		List<PopularActivity> list = new ArrayList<PopularActivity>();
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
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			list = popularActivityService.searchPopularActivityByMap(map);
			Integer count = 0;
			count = popularActivityService.countPopularActivityByMap(map);
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
		ActivityZones popularActivity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if (popularActivity != null) {
			popularActivity.setIsStop(bodyInfo.getString("is_stop"));
			Integer rs = activityZonesService.updateByPrimaryKeySelective(popularActivity);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "更改成功");
			}else{
				json.put("result", 1);
				json.put("description", "更改失败");
				return buildReqJsonObject(json);
			}
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String updateOfPopular() {
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
	
	
	@RequestMapping("update_PopularActivity")
	@ResponseBody
	public String updatePopular(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		//category_name分类类名称
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("unit_price")==null||bodyInfo.get("title") == null||
			bodyInfo.get("category_name") == null||bodyInfo.get("description") == null||
			bodyInfo.get("cover") == null||bodyInfo.get("good_uuid")==null
			) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime = new Date();
		try {
			String createAt=null;
			createTime = format.parse(createAt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Goods good = new Goods();
		good.setGoodUuid(bodyInfo.getString("good_uuid"));
		good.setTitle(bodyInfo.getString("title"));
		good.setCover(bodyInfo.getString("cover"));
		good.setSecondCategoryName(bodyInfo.getString("category_name"));
		good.setDescription(bodyInfo.getString("description"));
		good.setUpdateDatetime(createTime);
		BigDecimal unitPrice=new BigDecimal(bodyInfo.getString("unit_price"));
		good.setUnitPrice(unitPrice);
		int rs = 0;
		rs = goodsService.updateByPrimaryKeySelective(good);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "修改品成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "修改商品失败，请重试");
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
			if(bodyInfo.get("currentIndex") != null){
				activityZones.setCurrentIndex(bodyInfo.getInt("currentIndex"));
			}
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
	
	@RequestMapping(value = "host/list/{good_name}/{good_code}/{is_stop}" , method = RequestMethod.POST)
	@ResponseBody
	public String hostList(@PathVariable String good_name , @PathVariable String good_code , @PathVariable String is_stop){
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
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else{
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			if(!good_name.equals("1")){
				map.put("goodname", good_name);
			}
			if(!good_code.equals("1")){
				map.put("goodcode", good_code);
			}
			if(!is_stop.equals("0")){
				map.put("isStop", is_stop);
			}
			List<Map<String, Object>> list = activityZonesService.selectByPaging(map);
			model.put("list", list);
			Integer count = 0;
			count = activityZonesService.totalCount(map);
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
		model.put("result", "0");
		return buildReqJsonObject(model);
	}
}
