package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Stores;
import com.hcb.xigou.dto.UserOrders;
import com.hcb.xigou.service.IStoresService;
import com.hcb.xigou.service.UserOrdersService;

import net.sf.json.JSONObject;

@Controller
@CrossOrigin
@RequestMapping("userOrders/")
public class UserOrdersController extends BaseController{
	
	@Autowired
	UserOrdersService userOrdersService;
	@Autowired
	IStoresService storesService;
	
	@RequestMapping("selectstore")
	@ResponseBody
	public String selectstore(){
		JSONObject json = new JSONObject();
		ModelMap model = new ModelMap();
		List<Stores> list = new ArrayList<>();
		list  = storesService.selectStoreAll();
		if(list!=null){
			model.put("description", "查询成功");
			model.put("result",0);
			model.put("storeList", list);
			String a = buildReqJsonObject(model);
			a = a.replace("\"[", "[");
			a = a.replace("]\"", "]");
			return a;
		}else{
			json.put("result", 1);
			json.put("description", "未查询到信息");
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
		List<UserOrders> list = new ArrayList<UserOrders>();
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
			if(bodyInfo.get("order_number")!=null){
				if(!"".equals(bodyInfo.getString("order_number"))){
					map.put("orderNumber",bodyInfo.getString("order_number"));
				}
			}
			/*if(bodyInfo.get("nickname")!=null){
				if(!"".equals(bodyInfo.getString("nickname"))){
					map.put("nickname",bodyInfo.getString("nickname"));
				}
			}*/
			if(bodyInfo.get("startTime")!=null){
				map.put("startTime", bodyInfo.getString("startTime"));
			}
			if(bodyInfo.get("endTime")!=null){
				if(!"".equals(bodyInfo.getString("endTime"))){
					map.put("endTime", bodyInfo.getString("endTime"));
				}
			}
			if(bodyInfo.get("store_uuid")!=null){
				if(!"".equals(bodyInfo.getString("store_uuid"))){
					map.put("storeUuid", bodyInfo.getString("store_uuid"));
				}
			}
			list = userOrdersService.searchUsersOrderByMap(map);

			for (int i = 0; i < list.size(); i++) {

				if(list.get(i).getStoreUuid()!=null){
					Stores store = storesService.selectByPrimaryKey(list.get(i).getStoreUuid());
					list.get(i).setStoreName(store.getStoreName());
				}
			}
			Integer count = 0;
			count = userOrdersService.countUsersOrderByMap(map);
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
		model.put("userOrdersList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping("searchselect")
	@ResponseBody
	public String searchSelect(){
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
		List<UserOrders> list = new ArrayList<UserOrders>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		pageIndex=1;
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			
			if(bodyInfo.get("order_number")!=null){
				if(!"".equals(bodyInfo.getString("order_number"))){
					map.put("orderNumber",bodyInfo.getString("order_number"));
				}
			}
			if(bodyInfo.get("nickname")!=null){
				if(!"".equals(bodyInfo.getString("nickname"))){
					map.put("nickname",bodyInfo.getString("nickname"));
				}
			}
			if(bodyInfo.get("startTime")!=null){
				map.put("startTime", bodyInfo.getString("startTime"));
			}
			if(bodyInfo.get("endTime")!=null){
				if(!"".equals(bodyInfo.getString("endTime"))){
					map.put("endTime", bodyInfo.getString("endTime"));
				}
			}
			if(bodyInfo.get("store_uuid")!=null){
				if(!"".equals(bodyInfo.getString("store_uuid"))){
					map.put("storeUuid", bodyInfo.getString("store_uuid"));
				}
			}
			
			list = userOrdersService.searchUsersOrderByMap(map);
			for (int i = 0; i < list.size(); i++) {

				if(list.get(i).getStoreUuid()!=null){
					Stores store = storesService.selectByPrimaryKey(list.get(i).getStoreUuid());
					list.get(i).setStoreName(store.getStoreName());
				}
			}
			Integer count = 0;
			count = userOrdersService.countUsersOrderByMap(map);
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
		model.put("userOrdersList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
}

