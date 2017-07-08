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
import com.hcb.xigou.pojo.Goods;
import com.hcb.xigou.service.GoodsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("goods/")
public class GoodsController extends BaseController{
	
	@Autowired
	GoodsService goodsService;
	
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
}
