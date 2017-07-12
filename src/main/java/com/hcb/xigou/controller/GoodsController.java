package com.hcb.xigou.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.pojo.Goods;
import com.hcb.xigou.pojo.GoodsWithBLOBs;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.ISecondCategorysService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("goods/")
public class GoodsController extends BaseController{
	
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
		if (bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("good_uuid");
		List<String> goodUUids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			goodUUids.add(jsonArray.getString(i));
		}
		if(goodUUids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("goodUUids", "goodUUids");
			map.put("deleteAt", new Date());
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = goodsService.deleteByGoodUuids(map);
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
	
	@RequestMapping("select")
	@ResponseBody
	public String selectGoodid(){
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
		Goods goods = goodsService.selectGoodByGoodUuid(bodyInfo.getString("good_uuid"));
		if(goods!=null){
			json.put("result", 0);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			json.put("goods", goods);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到商品信息");
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
		List<Goods> list = new ArrayList<Goods>();
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
			
			if(bodyInfo.getString("good_name")!=null&&!"".equals(bodyInfo.getString("good_name"))){
				map.put("goodName",bodyInfo.getString("good_name"));
			}
			if(bodyInfo.getString("firt_category_name")!=null&&!"".equals(bodyInfo.getString("firt_category_name"))){
				map.put("firtCategoryName",bodyInfo.getString("firt_category_name"));
			}
			if(bodyInfo.getString("good_status")!=null&&!"".equals(bodyInfo.getString("good_status"))){
				map.put("goodStatus",bodyInfo.getString("good_status"));
			}
			if(bodyInfo.getString("minPrice")!=null&&!"".equals(bodyInfo.getString("minPrice"))){
				map.put("minPrice",bodyInfo.getString("minPrice"));
			}
			if(bodyInfo.getString("maxPrice")!=null&&!"".equals(bodyInfo.getString("maxPrice"))){
				map.put("maxPrice",bodyInfo.getString("maxPrice"));
			}
			
			list = goodsService.searchGoodsByMap(map);
			Integer count = 0;
			count = goodsService.countGoodsByMap(map);
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
		model.put("goodsList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
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
		UUID uuid = UUID.randomUUID();
		String goodUuid= uuid.toString();
		GoodsWithBLOBs goods =new GoodsWithBLOBs();
		goods.setGoodUuid(goodUuid);
		goods.setSecondCategoryName(bodyInfo.getString("category_name"));
		goods.setCreateDatetime(createTime);
		goods.setFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setSecondUuid(bodyInfo.getString("second_uuid"));
		goods.setCover(bodyInfo.getString("cover"));
		BigDecimal unitPrice=new BigDecimal(bodyInfo.getString("unit_price"));
		goods.setUnitPrice(unitPrice);
		goods.setPhotos(bodyInfo.getString("photos"));
		goods.setPoster(bodyInfo.getString("poster"));
		goods.setTitle(bodyInfo.getString("title"));
		
		FirstCategorys first = firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setFirtCategoryName(first.getCategoryName());
		
		int rs = 0;
		rs = goodsService.insertSelective(goods);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加商品成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加商品失败，请重试");
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
		if (bodyInfo.get("unit_price")==null||bodyInfo.get("title") == null||
			bodyInfo.get("second_uuid") == null||bodyInfo.get("first_uuid") == null||
			bodyInfo.get("category_name") == null||bodyInfo.get("description") == null||
			bodyInfo.get("photos") == null||bodyInfo.get("cover") == null||
			bodyInfo.get("poster") == null||bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date updateTime = new Date();
		try {
			String updateAt=null;
			updateTime = format.parse(updateAt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GoodsWithBLOBs goods =(GoodsWithBLOBs) goodsService.selectByGoodUuid(bodyInfo.getString("good_uuid"));
		goods.setSecondCategoryName(bodyInfo.getString("category_name"));
		goods.setUpdateDatetime(updateTime);
		goods.setFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setSecondUuid(bodyInfo.getString("second_uuid"));
		goods.setCover(bodyInfo.getString("cover"));
		BigDecimal unitPrice=new BigDecimal(bodyInfo.getString("unit_price"));
		goods.setUnitPrice(unitPrice);
		goods.setPhotos(bodyInfo.getString("photos"));
		goods.setPoster(bodyInfo.getString("poster"));
		goods.setTitle(bodyInfo.getString("title"));
		
		FirstCategorys first = firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setFirtCategoryName(first.getCategoryName());
		
		int rs = 0;
		rs = goodsService.updateByGoodsUuid(goods);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "编辑商品成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "编辑商品失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	
}
