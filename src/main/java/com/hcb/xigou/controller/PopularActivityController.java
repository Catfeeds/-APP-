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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.pojo.GoodsWithBLOBs;
import com.hcb.xigou.service.GoodsService;
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
				json.put("result",0);
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
		SecondCategorys second = secondCategorysService.selectAll();
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
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("unit_price")==null||bodyInfo.get("title") == null||
			bodyInfo.get("second_uuid") == null||bodyInfo.get("first_uuid") == null||
			bodyInfo.get("category_name") == null||bodyInfo.get("description") == null||
			bodyInfo.get("photos") == null||bodyInfo.get("cover") == null||
			bodyInfo.get("poster") == null) {
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
		
		String goodUuid = "";
		try {
			goodUuid = MD5Util.md5Digest(bodyInfo.getString("amount") + 
					System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		GoodsWithBLOBs goods =new GoodsWithBLOBs();
		goods.setGoodUuid(goodUuid);
		goods.setSecondCategoryName(bodyInfo.getString("category_name"));
		goods.setCreateDatetime(createTime);
		goods.setUpdateDatetime(createTime);
		goods.setFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setSecondUuid(bodyInfo.getString("second_uuid"));
		goods.setCover(bodyInfo.getString("cover"));
		BigDecimal unitPrice=new BigDecimal(bodyInfo.getString("unit_price"));
		goods.setUnitPrice(unitPrice);
		goods.setDescription(bodyInfo.getString("description"));
		goods.setTitle(bodyInfo.getString("title"));
		
		FirstCategorys first = firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setFirtCategoryName(first.getCategoryName());
		
		int rs = 0;
		rs = goodsService.insertSelective(goods);
		
		String activityUuid = "";
		try {
			activityUuid = MD5Util.md5Digest(bodyInfo.getString("amount") + 
					System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		PopularActivity popAct = new PopularActivity();
		popAct.setActivityUuid(activityUuid); 
		popAct.setGoodUuid(goodUuid);
		popAct.setImage(bodyInfo.getString("cover"));
		popAct.setTitle(bodyInfo.getString("title"));
		popAct.setCreateDatetime(createTime);
		popAct.setUpdateDatetime(createTime);
		int  i =popularActivityService.insertActivity(popAct);
		if(rs == 1 && i == 1){
			json.put("result", 0);
			json.put("description", "添加商品成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加商品失败，请重试");
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
			bodyInfo.get("cover") == null
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
		
		String goodUuid = "";
		try {
			goodUuid = MD5Util.md5Digest(bodyInfo.getString("amount") + 
					System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		GoodsWithBLOBs goods =new GoodsWithBLOBs();
		goods.setGoodUuid(goodUuid);
		goods.setSecondCategoryName(bodyInfo.getString("category_name"));
		goods.setCreateDatetime(createTime);
		goods.setUpdateDatetime(createTime);
		goods.setFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setSecondUuid(bodyInfo.getString("second_uuid"));
		goods.setCover(bodyInfo.getString("cover"));
		BigDecimal unitPrice=new BigDecimal(bodyInfo.getString("unit_price"));
		goods.setUnitPrice(unitPrice);
		goods.setDescription(bodyInfo.getString("description"));
		goods.setTitle(bodyInfo.getString("title"));
		
		FirstCategorys first = firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setFirtCategoryName(first.getCategoryName());
		
		int rs = 0;
		rs = goodsService.insertSelective(goods);
		
		String activityUuid = "";
		try {
			activityUuid = MD5Util.md5Digest(bodyInfo.getString("amount") + 
					System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		PopularActivity popAct = new PopularActivity();
		popAct.setActivityUuid(activityUuid); 
		popAct.setGoodUuid(goodUuid);
		popAct.setImage(bodyInfo.getString("cover"));
		popAct.setTitle(bodyInfo.getString("title"));
		popAct.setCreateDatetime(createTime);
		popAct.setUpdateDatetime(createTime);
		int  i =popularActivityService.insertActivity(popAct);
		if(rs == 1 && i == 1){
			json.put("result", 0);
			json.put("description", "添加商品成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加商品失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	
	
	
}
