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
import com.hcb.xigou.dto.UserManage;
import com.hcb.xigou.service.UserManageService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@CrossOrigin
@RequestMapping("userManage/")
public class UserManageController extends BaseController{
	
	@Autowired
	UserManageService userManageService;
	
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

		List<UserManage> list = new ArrayList<UserManage>();
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
			
			if(bodyInfo.get("name")!=null){
				if(!"".equals(bodyInfo.getString("name"))){
					map.put("name", bodyInfo.getString("name"));
				}
			}
			if(bodyInfo.get("nickname")!=null){
				if(!"".equals(bodyInfo.getString("nickname"))){
					map.put("nickname", bodyInfo.getString("nickname"));
				}
			}
			if(bodyInfo.get("phone")!=null){
				if(!"".equals(bodyInfo.getString("phone"))){
					map.put("phone", bodyInfo.getString("phone"));
				}
			}
			list = userManageService.searchUserMagageByMap(map);
			Integer count = 0;
			int usernum = userManageService.countUsers(map);
			count = userManageService.countUserMagageByMap(map);
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
				model.put("total", total);// 页码总数
				model.put("page", pageIndex);
				model.put("count",count);
				model.put("usernum",usernum);
			} else {
				Integer total = count / pageSize + 1;
				if (pageIndex > total) {
					json.put("result", "1");
					json.put("description", "操作失败，请求页数大于总页数");
					return buildReqJsonObject(json);
				}
				model.put("total", total);// 页码总数
				model.put("page", pageIndex);
				model.put("count",count);
				model.put("usernum",usernum);
			}
		}
		
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("userManagerList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping("searchSelect")
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
		ModelMap model = new ModelMap();

		List<UserManage> list = new ArrayList<UserManage>();
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
			
			if(bodyInfo.get("name")!=null){
				if(!"".equals(bodyInfo.getString("name"))){
					map.put("name", bodyInfo.getString("name"));
				}
			}
			if(bodyInfo.get("nickname")!=null){
				if(!"".equals(bodyInfo.getString("nickname"))){
					map.put("nickname", bodyInfo.getString("nickname"));
				}
			}
			if(bodyInfo.get("phone")!=null){
				if(!"".equals(bodyInfo.getString("phone"))){
					map.put("phone", bodyInfo.getString("phone"));
				}
			}
			list = userManageService.searchUserMagageByMap(map);
			Integer count = 0;
			int usernum = userManageService.countUsers(map);
			count = userManageService.countUserMagageByMap(map);
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
				model.put("total", total);// 页码总数
				model.put("page", pageIndex);
				model.put("count",count);
				model.put("usernum",usernum);
			} else {
				Integer total = count / pageSize + 1;
				if (pageIndex > total) {
					json.put("result", "1");
					json.put("description", "操作失败，请求页数大于总页数");
					return buildReqJsonObject(json);
				}
				model.put("total", total);// 页码总数
				model.put("page", pageIndex);
				model.put("count",count);
				model.put("usernum",usernum);
			}
		}
		
		model.put("description", "查询成功");
		model.put("result",0);
		model.put("userManagerList", list);
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
		JSONArray jsonArray = bodyInfo.getJSONArray("user_uuid");
		List<String> userUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			userUuids.add(jsonArray.getString(i));
		}
		if(userUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userUuids",userUuids);
			if(headInfo.get("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = userManageService.deleteByUsersUuids(map);
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
}
