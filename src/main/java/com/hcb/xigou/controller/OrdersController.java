package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Orders;
import com.hcb.xigou.dto.ShoppingCarts;
import com.hcb.xigou.service.IOrdersService;
import com.hcb.xigou.service.IShoppingCartsService;
import com.hcb.xigou.service.UserOrdersService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("order/")
public class OrdersController extends BaseController{
	@Autowired
	UserOrdersService userOrdersService;
	@Autowired
	IOrdersService ordersService;
	@Autowired
	IShoppingCartsService shoppingCartsService;
	
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
		List<Orders> list = new ArrayList<Orders>();
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
			if(bodyInfo.get("nickname")!=null){
				if(!"".equals(bodyInfo.getString("nickname"))){
					map.put("nickname",bodyInfo.getString("nickname"));
				}
			}
			if(bodyInfo.get("startTime")!=null){
				map.put("startTime", bodyInfo.getString("startTime"));
			}
			if(bodyInfo.get("endTime")!=null){
				map.put("endTime", bodyInfo.getString("endTime"));
			}
			if(bodyInfo.get("pay_status")!=null){
				map.put("payStatus", bodyInfo.getString("pay_status"));
			}
			
			list = userOrdersService.searchOrderByMap(map);
			Integer count = 0;
			count = userOrdersService.countOrderyMap(map);
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
		model.put("ordersList", list);
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
		List<Orders> list = new ArrayList<Orders>();
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
				map.put("endTime", bodyInfo.getString("endTime"));
			}
			if(bodyInfo.get("pay_status")!=null){
				map.put("payStatus", bodyInfo.getString("pay_status"));
			}
			if(bodyInfo.get("member_card_number")!=null){
				map.put("memberCardNumber", bodyInfo.getString("member_card_number"));
			}
			
			list = userOrdersService.searchOrderByMap(map);
			Integer count = 0;
			count = userOrdersService.countOrderyMap(map);
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
		model.put("ordersList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping(value = "detail" , method = RequestMethod.POST)
	@ResponseBody
	public String orderDetail(){
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
		if (bodyInfo.get("order_number") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("order_number"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		List<Map<String, Object>> info = new ArrayList<Map<String, Object>>();
		Orders order = ordersService.selectByOrderNumber(bodyInfo.getString("order_number"));
		if(order == null){
			json.put("result", "1");
			json.put("description", "未查询到订单信息");
			return buildReqJsonObject(json);
		}
		JSONArray cars = JSONArray.fromObject(order.getCarUuids());
		for (int i = 0; i < cars.size(); i++) {
			if(cars.get(i).toString() != null){
				ShoppingCarts car = shoppingCartsService.selectByCarUuid(cars.get(i).toString());
				if(car != null){
					Map<String, Object> map = new HashMap<String,Object>();
					map.put("order_number", order.getOrderNumber());
					map.put("good_name", car.getGoodName());
					map.put("number", car.getNumbers());
					map.put("image", car.getGoodImage());
					info.add(map);
				}
			}
		}
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("info", info);
		return buildReqJsonObject(model);
	}
}
