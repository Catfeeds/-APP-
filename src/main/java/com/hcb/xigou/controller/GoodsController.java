package com.hcb.xigou.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.pojo.Goods;
import com.hcb.xigou.pojo.GoodsWithBLOBs;
import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.dto.Skus;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.ISecondCategorysService;
import com.hcb.xigou.service.ISkusService;
import com.hcb.xigou.util.MD5Util;

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
	@Autowired
	ISkusService skusService;
	

	@RequestMapping("delete")
	@ResponseBody
	public String delete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("good_uuid");
		List<String> goodUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			goodUuids.add(jsonArray.getString(i));
		}
		if(goodUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("goodUuids",goodUuids);
			if(headInfo.get("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = goodsService.deleteByGoodUuids(map);
			if(rs >= 1){
				json.put("result", 0);
				json.put("description", "删除成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "请检查参数格式是否正确或者参数是否完整3");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整4");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("select")
	@ResponseBody
	public String selectGoodid(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		Goods goods = goodsService.selectGoodByGoodUuid(bodyInfo.getString("good_uuid"));
		if(goods!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("goods", goods);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到商品信息4");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("search")
	@ResponseBody
	public String search(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		if (sign == 2) {
			json.put("result", 2);
			json.put("description", "验证失败，user_uuid或密码不正确2");
			return buildReqJsonInteger(2, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否完整3");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否正确4");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		List<GoodsWithBLOBs> list = new ArrayList<GoodsWithBLOBs>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", 1);
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			
			if(bodyInfo.get("good_name")!=null){
				if(!"".equals(bodyInfo.getString("good_name"))){
					map.put("goodName",bodyInfo.getString("good_name"));
				}
			}
			if(bodyInfo.get("firt_category_name")!=null){
				if(!"".equals(bodyInfo.getString("firt_category_name"))){
					map.put("firtCategoryName",bodyInfo.getString("firt_category_name"));
				}
			}
			if(bodyInfo.get("good_status")!=null){
				if(!"".equals(bodyInfo.getString("good_status"))){
					map.put("goodStatus",bodyInfo.getString("good_status"));
				}
			}
			if(bodyInfo.get("minPrice")!=null){
				if(!"".equals(bodyInfo.getString("minPrice"))){
					map.put("minPrice",bodyInfo.getString("minPrice"));
				}
			}
			if(bodyInfo.get("maxPrice")!=null){
				if(!"".equals(bodyInfo.getString("maxPrice"))){
					map.put("maxPrice",bodyInfo.getString("maxPrice"));
				}
			}
			list = goodsService.searchGoodsByMap(map);
			int count = 0;
			count = goodsService.countGoodsByMap(map);
			if (count % pageSize == 0) {
				Integer total = count / pageSize;
				Integer sign = 0;
				if (!total.equals(sign)) {
					if (pageIndex > total) {
						json.put("result", 1);
						json.put("description", "操作失败，请求页数大于总页数");
						return buildReqJsonObject(json);
					}
				}
				model.put("total", count);
				model.put("page", pageIndex);
				model.put("count", count);
			} else {
				Integer total = count / pageSize + 1;
				if (pageIndex > total) {
					json.put("result", 1);
					json.put("description", "操作失败，请求页数大于总页数");
					return buildReqJsonObject(json);
				}
				model.put("total", count);// 页码总数
				model.put("page", pageIndex);
				model.put("count", count);
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
	
	@RequestMapping("searchselect")
	@ResponseBody
	public String searchSelect(){
		JSONObject json = new JSONObject();
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否完整3");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否正确4");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		List<GoodsWithBLOBs> list = new ArrayList<GoodsWithBLOBs>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		pageIndex = 1;
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", 1);
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			
			if(bodyInfo.get("good_name")!=null){
				if(!"".equals(bodyInfo.getString("good_name"))){
					map.put("goodName",bodyInfo.getString("good_name"));
				}
			}
			if(bodyInfo.get("firt_category_name")!=null){
				if(!"".equals(bodyInfo.getString("firt_category_name"))){
					map.put("firtCategoryName",bodyInfo.getString("firt_category_name"));
				}
			}
			if(bodyInfo.get("good_status")!=null){
				if(!"".equals(bodyInfo.getString("good_status"))){
					map.put("goodStatus",bodyInfo.getString("good_status"));
				}
			}
			if(bodyInfo.get("minPrice")!=null){
				if(!"".equals(bodyInfo.getString("minPrice"))){
					map.put("minPrice",bodyInfo.getString("minPrice"));
				}
			}
			if(bodyInfo.get("maxPrice")!=null){
				if(!"".equals(bodyInfo.getString("maxPrice"))){
					map.put("maxPrice",bodyInfo.getString("maxPrice"));
				}
			}
			list = goodsService.searchGoodsByMap(map);
			int count = 0;
			count = goodsService.countGoodsByMap(map);
			if (count % pageSize == 0) {
				Integer total = count / pageSize;
				Integer sign = 0;
				if (!total.equals(sign)) {
					if (pageIndex > total) {
						json.put("result", 1);
						json.put("description", "操作失败，请求页数大于总页数");
						return buildReqJsonObject(json);
					}
				}
				model.put("total", total);
				model.put("page", pageIndex);
				model.put("count", count);
			} else {
				Integer total = count / pageSize + 1;
				if (pageIndex > total) {
					json.put("result", 1);
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
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整111111111");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		List<SecondCategorys> second = new ArrayList<SecondCategorys>();
		second=	secondCategorysService.selectAll();
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
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (headInfo.get("store_uuid")==null||bodyInfo.get("unit_price")==null||bodyInfo.get("good_name") == null||
			bodyInfo.get("second_uuid") == null||bodyInfo.get("first_uuid") == null||
			bodyInfo.get("firt_category_name") == null||bodyInfo.get("description") == null||
			bodyInfo.get("photos") == null||bodyInfo.get("cover") == null||
			bodyInfo.get("poster") == null||
			bodyInfo.get("second_category_name") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		String goodUuid = "";
		try {

			goodUuid = MD5Util.md5Digest(bodyInfo.getString("good_name") + System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			e.printStackTrace();
		}	

		GoodsWithBLOBs goods =new GoodsWithBLOBs();
		goods.setGoodUuid(goodUuid);
		goods.setSecondCategoryName(bodyInfo.getString("second_category_name"));
		goods.setFirtCategoryName(bodyInfo.getString("firt_category_name"));
		goods.setFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setSecondUuid(bodyInfo.getString("second_uuid"));
		goods.setCover(bodyInfo.getString("cover"));
		BigDecimal unitPrice=new BigDecimal(bodyInfo.getString("unit_price"));
		goods.setUnitPrice(unitPrice);
		goods.setDescription(bodyInfo.getString("description"));
		goods.setPhotos("0"+bodyInfo.getString("photos"));
		goods.setPoster("0"+bodyInfo.getString("poster"));
		goods.setGoodName((bodyInfo.getString("good_name")));
		goods.setStoreUuid(headInfo.getString("store_uuid"));
		if(bodyInfo.get("skus") != null){
			goods.setSkus(bodyInfo.getString("skus"));
		}
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
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("unit_price")==null||bodyInfo.get("good_name") == null||
			bodyInfo.get("second_uuid") == null||bodyInfo.get("first_uuid") == null||
			bodyInfo.get("firt_category_name") == null||bodyInfo.get("description") == null||
			bodyInfo.get("photos") == null||bodyInfo.get("cover") == null||
			bodyInfo.get("poster") == null||bodyInfo.get("good_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		GoodsWithBLOBs goods = new GoodsWithBLOBs();
		goods.setSecondCategoryName(bodyInfo.getString("second_category_name"));
		goods.setFirtCategoryName(bodyInfo.getString("firt_category_name"));
		goods.setFirstUuid(bodyInfo.getString("first_uuid"));
		goods.setSecondUuid(bodyInfo.getString("second_uuid"));
		goods.setCover(bodyInfo.getString("cover"));
		BigDecimal unitPrice = new BigDecimal(bodyInfo.getString("unit_price"));
		goods.setUnitPrice(unitPrice);
		goods.setPhotos("0" + bodyInfo.getString("photos"));
		goods.setPoster("0" + bodyInfo.getString("poster"));
		goods.setGoodName((bodyInfo.getString("good_name")));
		goods.setGoodUuid(bodyInfo.getString("good_uuid"));
		int rs = 0;
		rs = goodsService.updateByGoodsUuid(goods);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "编辑商品成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "编辑商品失败，请重试");
			return buildReqJsonObject(json);
		}

	}

	@RequestMapping("update_status")
	@ResponseBody
	public String updateStatus(){
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null||
				bodyInfo.get("good_status") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("good_uuid");
		List<String> goodUuids = new ArrayList<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i=0;i<jsonArray.size();i++){
			goodUuids.add(jsonArray.getString(i));
		}
		if(bodyInfo.getString("good_status").equals("up")){
			map.put("goodStatus","down");
		}
		if(bodyInfo.getString("good_status").equals("down")){
			map.put("goodStatus","up");
		}
		map.put("goodUuids",goodUuids);
		int rs = 0;
		rs = goodsService.updateByGoodsStatus(map);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "修改状态成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "修改商品失败，请重试");
			return buildReqJsonObject(json);
		}

	}
	
	@RequestMapping(value = "/add/sku" , method = RequestMethod.POST)
	@ResponseBody
	public String  goodAddSku(){
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("good_uuid") == null || bodyInfo.get("skus") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		GoodsWithBLOBs good = goodsService.selectGoodByGoodUuid(bodyInfo.getString("good_uuid"));
		if(good == null){
			json.put("result", 1);
			json.put("description", "未查詢到商品信息");
			return buildReqJsonObject(json);
		}
		good.setSkus(bodyInfo.getString("skus"));
		Integer rs = goodsService.updateByGoodsUuid(good);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加成功");
		}else{
			json.put("result", 1);
			json.put("description", "添加失敗");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "category" , method = RequestMethod.POST)
	@ResponseBody
	public String searchCategory(){
		JSONObject json = new JSONObject();
		ModelMap model = new ModelMap();
		List<Skus> list = new ArrayList<Skus>();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		list= skusService.selectAllCategory(map);
		if(list == null){
			json.put("result", 1);
			json.put("description", "查不到类别");
			return buildReqJsonObject(json);
		}else{
			model.put("categoryList", list);
		}
		model.put("description", "查询成功");
		model.put("result",0);
		return buildReqJsonObject(model);
		
	}
	
	@RequestMapping(value = "parameter" , method = RequestMethod.POST)
	@ResponseBody
	public String searchParameter(){
		JSONObject json = new JSONObject();
		ModelMap model = new ModelMap();
		List<Skus> list = new ArrayList<Skus>();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("category") == null ) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		list = skusService.selectAllParameter(bodyInfo.getString("category"));
		if(list == null){
			json.put("result", 1);
			json.put("description", "查不到类别");
			return buildReqJsonObject(json);
		}else{
			model.put("parameterList", list);
		}
		model.put("description", "查询成功");
		model.put("result",0);
		return buildReqJsonObject(model);
		
	}
}
