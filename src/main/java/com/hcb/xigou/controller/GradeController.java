package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Managers;
import com.hcb.xigou.service.IManagersService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.RandomStringGenerator;
import com.hcb.xigou.util.StringToDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("grade")
public class GradeController extends BaseController{

	@Autowired
	IManagersService managersService;
	
	@RequestMapping(value = "group/create" , method = RequestMethod.POST)
	public String groupOfCreate(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("group_name") == null || bodyInfo.get("nickname") ==null || 
			bodyInfo.get("password") == null || bodyInfo.get("repassword") ==null ||
			bodyInfo.get("permissions") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		if(!bodyInfo.getString("password").equals(bodyInfo.getString("repassword"))){
			json.put("result", 1);
			json.put("description", "输入密码不一致");
			return buildReqJsonObject(json);
		}
		Managers manager = new Managers();
		manager.setCreateDatetime(new Date());
		manager.setGroupName(bodyInfo.getString("group_name"));
		manager.setNickname(bodyInfo.getString("nickname"));
		manager.setPassword(bodyInfo.getString("password"));
		manager.setPermissions(bodyInfo.getString("permissions"));
		manager.setGrade("3");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grade", "0");
		Managers admin = managersService.selectBynicknameAndGrade(map);
		if(admin != null){
			manager.setSuperiorUuid(admin.getManagerUuid());
		}
		try {
			manager.setManagerUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer rs = managersService.insertSelective(manager);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "创建成功");
		}else{
			json.put("result", 1);
			json.put("description", "创建失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "crew/create" , method = RequestMethod.POST)
	public String gg(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_uuid") == null || bodyInfo.get("nickname") ==null || 
			bodyInfo.get("password") == null || bodyInfo.get("repassword") ==null ||
			bodyInfo.get("permissions") == null || bodyInfo.get("number") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		if(!bodyInfo.getString("password").equals(bodyInfo.getString("repassword"))){
			json.put("result", 1);
			json.put("description", "输入密码不一致");
			return buildReqJsonObject(json);
		}
		Managers admin = managersService.selectByManagerUuid(bodyInfo.getString("manager_uuid"));
		if(admin == null){
			json.put("result", 1);
			json.put("description", "未查询到小组信息");
			return buildReqJsonObject(json);
		}
		Managers manager = new Managers();
		manager.setCreateDatetime(new Date());
		//manager.setSuperiorUuid(bodyInfo.getString("manager_uuid"));
		manager.setNickname(bodyInfo.getString("nickname"));
		manager.setNumber(bodyInfo.getString("number"));
		manager.setPassword(bodyInfo.getString("password"));
		manager.setPermissions(bodyInfo.getString("permissions"));
		manager.setGrade("6");
        manager.setSuperiorUuid(admin.getManagerUuid());
        manager.setGroups(admin.getGroupName());
		try {
			manager.setManagerUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer rs = managersService.insertSelective(manager);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "创建成功");
		}else{
			json.put("result", 1);
			json.put("description", "创建失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "group/list" , method = RequestMethod.POST)
	public String groupOfList(){
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
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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
			map.put("grade", "3");
			if(bodyInfo.get("group_name") != null){
				map.put("groupname", bodyInfo.getString("group_name"));
			}
            if(bodyInfo.get("nickname") != null){
            	map.put("nickname", bodyInfo.getString("nickname"));
			}
            List<Managers> managers = managersService.selectByPaging(map);
            Integer count = 0;
            count = managersService.totalCount(map);
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
            for (Managers manager : managers) {
				if(manager != null){
					Map<String, Object> mop = new HashMap<String, Object>();
					mop.put("manager_uuid", manager.getManagerUuid());
					mop.put("create_datetime", StringToDate.dateToString(manager.getCreateDatetime()));
					mop.put("nickname", manager.getNickname());
					mop.put("group_name", manager.getGroupName());
					mop.put("permissions", manager.getPermissions());
					mop.put("grade", manager.getGrade());
					list.add(mop);
				}
			}
		}
		model.put("description", "查询成功");
		model.put("result", "0");
		model.put("list", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		a = a.replaceAll("\\\\", "");
		return a;
	}
	
	@RequestMapping(value = "crew/list" , method = RequestMethod.POST)
	public String crewOfList(){
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
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null || bodyInfo.get("manager_uuid") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize")) || "".equals(bodyInfo.get("manager_uuid"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		Integer pageSize = bodyInfo.getInt("pageSize");
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "操作失败，pageIndex不小于0");
			return buildReqJsonObject(json);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			map.put("grade", "6");
			map.put("managerUuid", bodyInfo.getString("manager_uuid"));
			if(bodyInfo.get("number") != null){
				map.put("number", bodyInfo.getString("number"));
			}
            if(bodyInfo.get("nickname") != null){
            	map.put("nickname", bodyInfo.getString("nickname"));
			}
			List<Managers> maangers = managersService.selectByPaging(map);
			Integer count = 0;
			count = managersService.totalCount(map);
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
			 for (Managers manager : maangers) {
				if(manager != null){
					Map<String, Object> mop = new HashMap<String, Object>();
					mop.put("manager_uuid", manager.getManagerUuid());
					mop.put("create_datetime", StringToDate.dateToString(manager.getCreateDatetime()));
					mop.put("nickname", manager.getNickname());
					mop.put("groups", manager.getGroups());
					mop.put("permissions", manager.getPermissions());
					mop.put("grade", manager.getGrade());
					mop.put("number", manager.getNumber());
					list.add(mop);
				}
			}
		}
		model.put("description", "查询成功");
		model.put("result", "0");
		model.put("list", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		a = a.replaceAll("\\\\", "");
		return a;
	}
	
	@RequestMapping(value = "group/edit" ,method = RequestMethod.POST)
	public String  groupOfEdit(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Managers manager = managersService.selectByManagerUuid(bodyInfo.getString("manager_uuid"));
		if(manager == null){
			json.put("result", 1);
			json.put("description", "未查询到小组信息");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("group_name") != null){
			manager.setGroupName(bodyInfo.getString("group_name"));
		}
		if(bodyInfo.get("nickname") != null){
			manager.setNickname(bodyInfo.getString("nickname"));
		}
        if(bodyInfo.get("password") != null && bodyInfo.get("repassword") != null){
        	if(!bodyInfo.getString("password").equals(bodyInfo.getString("repassword"))){
    			json.put("result", 1);
    			json.put("description", "输入密码不一致");
    			return buildReqJsonObject(json);
    		}
        	manager.setPassword(bodyInfo.getString("password"));
		}
        if(bodyInfo.get("permissions") != null){
        	manager.setPermissions(bodyInfo.getString("permissions"));
        }
		Integer rs = managersService.updateByPrimaryKeySelective(manager);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "编辑成功");
		}else{
			json.put("result", 1);
			json.put("description", "编辑失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "crew/edit" , method = RequestMethod.POST)
	public String crewOfEdit(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Managers manager = managersService.selectByManagerUuid(bodyInfo.getString("manager_uuid"));
		if(manager == null){
			json.put("result", 1);
			json.put("description", "未查询到小组信息");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("number") != null){
			manager.setNumber(bodyInfo.getString("number"));
		}
		if(bodyInfo.get("nickname") != null){
			manager.setNickname(bodyInfo.getString("nickname"));
		}
        if(bodyInfo.get("password") != null && bodyInfo.get("repassword") != null){
        	if(!bodyInfo.getString("password").equals(bodyInfo.getString("repassword"))){
    			json.put("result", 1);
    			json.put("description", "输入密码不一致");
    			return buildReqJsonObject(json);
    		}
        	manager.setPassword(bodyInfo.getString("password"));
		}
        if(bodyInfo.get("permissions") != null){
        	manager.setPermissions(bodyInfo.getString("permissions"));
        }
		Integer rs = managersService.updateByPrimaryKeySelective(manager);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "编辑成功");
		}else{
			json.put("result", 1);
			json.put("description", "编辑失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "delete" , method = RequestMethod.POST)
	public String gradeOfDelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("manager_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Boolean flag = false;
	    JSONArray array = JSONArray.fromObject(bodyInfo.getString("manager_uuid"));
	    for (int i = 0; i < array.size(); i++) {
	    	if(array.get(i).toString() != null){
	    		Managers manager = managersService.selectByManagerUuid(array.get(i).toString());
	    		if(manager != null){
	    			manager.setDeleteAt("del");
	    			Integer rs = managersService.updateByPrimaryKeySelective(manager);
	    			if(rs == 1){
	    				flag = true;
	    				  continue;
					  }else{
						  flag = false;
						  break;
					  }
	    		}
	    	}
	    }
	    if(flag){
	    	json.put("result", 0);
	    	json.put("description", "删除成功");
	    }else{
	     	json.put("result", 1);
	    	json.put("description", "删除失败");
	    }
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "group/all" , method = RequestMethod.POST)
	public String groupOfAll(){
		JSONObject json = new JSONObject();
		List<Map<String, Object>> list = managersService.selectByAll();
		json.put("result", 0);
		json.put("description", "查询成功");
		json.put("list", list);
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "change" , method = RequestMethod.POST)
	public String gradeOfChange(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("group_uuid") == null || bodyInfo.get("staff_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Managers admin = managersService.selectByManagerUuid(bodyInfo.getString("group_uuid"));
		if(admin == null){
			json.put("result", 1);
			json.put("description", "未查询到小组信息");
			return buildReqJsonObject(json);
		}
		Managers staff = managersService.selectByManagerUuid(bodyInfo.getString("staff_uuid"));
        if(staff == null){
        	json.put("result", 1);
			json.put("description", "未查询到组员信息");
			return buildReqJsonObject(json);
		}
        staff.setSuperiorUuid(admin.getManagerUuid());
        staff.setGroups(admin.getGroupName());
        Integer rs = managersService.updateByPrimaryKeySelective(staff);
        if(rs == 1){
			json.put("result", 0);
			json.put("description", "编辑成功");
		}else{
			json.put("result", 1);
			json.put("description", "编辑失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
}
