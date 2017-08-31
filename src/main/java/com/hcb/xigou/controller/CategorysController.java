
package com.hcb.xigou.controller;


import java.sql.Timestamp;
import java.time.Instant;
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
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.dto.ThirdCategorys;
import com.hcb.xigou.service.IFirstCategorysService;
import com.hcb.xigou.service.ISecondCategorysService;
import com.hcb.xigou.service.IThirdCategorysService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.RandomStringGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("categorys/")
public class CategorysController extends BaseController{

	@Autowired
	ISecondCategorysService secondCategorysService;
	@Autowired
	IFirstCategorysService firstCategorysService;
	@Autowired
	IThirdCategorysService thirdCategorysService;
	
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
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否完整3");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();

		List<FirstCategorys> list = new ArrayList<FirstCategorys>();
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
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			
			list = firstCategorysService.searchCategoryByMap(map);
			Integer count = 0;
			count = firstCategorysService.countCategoryByMap(map);
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
		model.put("categoryList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	
	@RequestMapping("secondsearch")
	@ResponseBody
	public String secondsearch(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", 2);
			json.put("description", "验证失败，user_uuid或密码不正确");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();

		List<SecondCategorys> list = new ArrayList<SecondCategorys>();
		
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
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			map.put("firstUuid", bodyInfo.getString("first_uuid"));
			list = secondCategorysService.searchCategoryByMap(map);
			Integer count = 0;
			count = secondCategorysService.countCategoryByMap(map);
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
		model.put("categoryList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	
	
	@RequestMapping("insert")
	@ResponseBody
	public String insert(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("category_name")==null||bodyInfo.get("image") == null||
			headInfo.get("store_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		String firstUuid = "";
		try {
			firstUuid = MD5Util.md5Digest(bodyInfo.getString("category_name") + System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		FirstCategorys first = new FirstCategorys();
		first.setFirstUuid(firstUuid);
		first.setCategoryName(bodyInfo.getString("category_name"));
		first.setImage(bodyInfo.getString("image"));
		first.setStoreUuid(headInfo.getString("store_uuid"));
		
		int rs = 0;
		rs = firstCategorysService.insertSelective(first);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加商品分类成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加商品分类失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	
	
	@RequestMapping("secondinsert")
	@ResponseBody
	public String secondinsert(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("category_name")==null||bodyInfo.get("image") == null||
			headInfo.get("store_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		String secondUuid = "";
		try {
			secondUuid = MD5Util.md5Digest(bodyInfo.getString("category_name") 
					+ System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		SecondCategorys second = new SecondCategorys();
		second.setSecondUuid(secondUuid);
		second.setFirstUuid(bodyInfo.getString("first_uuid"));
		second.setCategoryName(bodyInfo.getString("category_name"));
		if(!bodyInfo.getString("image").equals(""))second.setImage(bodyInfo.getString("image"));
		second.setStoreUuid(headInfo.getString("store_uuid"));
		
		int rs = 0;
		rs = secondCategorysService.insertSelective(second);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加商品分类成功");
		    if(bodyInfo.get("labels") != null){
		    	JSONArray array = JSONArray.fromObject(bodyInfo.getString("labels"));
		    	for (int i = 0; i < array.size(); i++) {
					if(array.get(i).toString() != null &&! array.get(i).toString().equals("")){
						ThirdCategorys category = new ThirdCategorys();
						category.setCreateDatetime(Timestamp.from(Instant.now()));
						category.setSecondUuid(second.getSecondUuid());
						category.setCategoryName(array.get(i).toString());
						category.setFirstUuid(second.getFirstUuid());
						category.setStoreUuid(second.getStoreUuid());
						try {
							category.setThirdUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						thirdCategorysService.insertSelective(category);					
					}
				}
		    } 
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加商品分类失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("category_name")==null||bodyInfo.get("image") == null||
			headInfo.get("store_uuid") == null||bodyInfo.get("first_uuid") == null) {
			json.put("result", 1);
			json.put("description", "有参数s为空，请重试");
			return buildReqJsonObject(json);
		}
		FirstCategorys first =firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		if(first !=null){
			first.setFirstUuid(bodyInfo.getString("first_uuid"));
			first.setCategoryName(bodyInfo.getString("category_name"));
			first.setImage(bodyInfo.getString("image"));
			
			int rs = 0;
			rs = firstCategorysService.updateByfirstUuid(first);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "编辑商品分类成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "编辑商品分类失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "查询不到商品，请重试");
			return buildReqJsonObject(json);
		}
	}
	
	
	@RequestMapping("secondupdate")
	@ResponseBody
	public String secondupdate() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("second_uuid") == null) {
			json.put("result", 1);
			json.put("description", "有参数为空，请重试");
			return buildReqJsonObject(json);
		}
		SecondCategorys second = new SecondCategorys();
		second.setSecondUuid(bodyInfo.getString("second_uuid"));
		if(bodyInfo.get("category_name") != null){
			second.setCategoryName(bodyInfo.getString("category_name"));
		}
		if(bodyInfo.get("image") != null){
			if(!bodyInfo.getString("image").equals(""))second.setImage(bodyInfo.getString("image"));
		}
		int rs = 0;
		rs = secondCategorysService.updateBySecondUuid(second);
		if (rs >= 1) {
			json.put("result", 0);
			json.put("description", "编辑商品分类成功");
			if (bodyInfo.get("labels") != null) {
				SecondCategorys se = secondCategorysService.selectBySecondUuid(bodyInfo.getString("second_uuid"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("secondUuid", se.getSecondUuid());
				Integer count = 0;
				count = thirdCategorysService.totalCount(map);
				map.put("start", 0);
				map.put("end", count);
				List<Map<String, Object>> list = thirdCategorysService.selectByPaging(map);
                for (Map<String, Object> map2 : list) {
					ThirdCategorys thrid= thirdCategorysService.selectByThirdUuid(String.valueOf(map2.get("third_uuid")));
					thrid.setDeleteAt("del");
					thirdCategorysService.updateByPrimaryKey(thrid);
				}
				JSONArray array = JSONArray.fromObject(bodyInfo.getString("labels"));
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).toString() != null && !array.get(i).toString().equals("")) {
						ThirdCategorys category = new ThirdCategorys();
						category.setCreateDatetime(Timestamp.from(Instant.now()));
						category.setSecondUuid(se.getSecondUuid());
						category.setCategoryName(array.get(i).toString());
						category.setFirstUuid(se.getFirstUuid());
						category.setStoreUuid(se.getStoreUuid());
						try {
							category.setThirdUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32)
									+ System.currentTimeMillis() + RandomStringUtils.random(8)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						thirdCategorysService.insertSelective(category);
					}
				}
			} 
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "编辑商品分类失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	@RequestMapping("delete")
	@ResponseBody
	public String delete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("first_uuid") == null) {
			json.put("result", 1);
			json.put("description", "first_uuid为空，请重试");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("first_uuid");
		List<String> firstUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			firstUuids.add(jsonArray.getString(i));
		}
		if(firstUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("firstUuids", firstUuids);
			map.put("deleteAt", "del");
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = firstCategorysService.deleteByFirstUuids(map);
			if(rs >= 1){
				json.put("result", 0);
				json.put("description", "删除成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "删除失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("seconddelete")
	@ResponseBody
	public String seconddelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("second_uuid") == null) {
			json.put("result", 1);
			json.put("description", "second_uuid为空，请重试");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("second_uuid");
		List<String> secondUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			secondUuids.add(jsonArray.getString(i));
		}
		if(secondUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("secondUuids", secondUuids);
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = secondCategorysService.deleteBySecondUuids(map);
			if(rs >= 1){
				json.put("result", 0);
				json.put("description", "删除成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "删除失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public String detail(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		FirstCategorys first = firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		if(first!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("first", first);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到分类信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("seconddetail")
	@ResponseBody
	public String seconddetail(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		FirstCategorys first = firstCategorysService.selectByFirstUuid(bodyInfo.getString("first_uuid"));
		if(first!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("first", first);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到分类信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping(value = "/label/publish" , method = RequestMethod.POST)
	@ResponseBody
	public String labelPublish(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("second_uuid") == null || bodyInfo.get("category_name") ==null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		SecondCategorys second = secondCategorysService.selectBySecondUuid(bodyInfo.getString("second_uuid"));
		if(second == null){
			json.put("result", "1");
			json.put("description", "未查询到二级分类信息");
			return buildReqJsonObject(json);
		}
		ThirdCategorys category = new ThirdCategorys();
		category.setCreateDatetime(Timestamp.from(Instant.now()));
		category.setSecondUuid(second.getSecondUuid());
		category.setCategoryName(bodyInfo.getString("category_name"));
		category.setFirstUuid(second.getFirstUuid());
		category.setStoreUuid(second.getStoreUuid());
		try {
			category.setThirdUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer rs = thirdCategorysService.insertSelective(category);
		if(rs == 1){
			json.put("result", "0");
			json.put("description", "添加成功");
		}else{
			json.put("result", "1");
			json.put("description", "添加失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "/label/edit" , method = RequestMethod.POST)
	@ResponseBody
	public String labelEdit(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("third_uuid") == null ) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		ThirdCategorys category = thirdCategorysService.selectByThirdUuid(bodyInfo.getString("third_uuid"));
		if(category == null){
			json.put("result", "1");
			json.put("description", "未查询到标签信息");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("category_name") != null){
			category.setCategoryName(bodyInfo.getString("category_name"));
		}
		Integer rs = thirdCategorysService.updateByPrimaryKeySelective(category);
		if(rs == 1){
			json.put("result", "0");
			json.put("description", "编辑成功");
		}else{
			json.put("result", "1");
			json.put("description", "编辑失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	
	@RequestMapping(value = "/label/delete" , method = RequestMethod.POST)
	@ResponseBody
	public String labelDelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("third_uuid") == null ) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Boolean flag = false;
	    JSONArray array = JSONArray.fromObject(bodyInfo.getString("third_uuid"));
	    for (int i = 0; i < array.size(); i++) {
	      	if(array.get(i).toString() != null){
              ThirdCategorys category = thirdCategorysService.selectByThirdUuid(array.get(i).toString());
				if (category != null) {
					category.setDeleteAt("del");
					Integer rs = thirdCategorysService.updateByPrimaryKeySelective(category);
					if (rs == 1) {
						flag = true;
						continue;
					} else {
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
	
	@RequestMapping(value = "/label/list" , method = RequestMethod.POST)
	@ResponseBody
	public String labelList(){
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
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null || bodyInfo.get("second_uuid") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize")) || "".equals(bodyInfo.get("second_uuid"))) {
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
			map.put("secondUuid", bodyInfo.getString("second_uuid"));
			list = thirdCategorysService.selectByPaging(map);
			Integer count = 0;
			count = thirdCategorysService.totalCount(map);
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
		model.put("list", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		a = a.replaceAll("\\\\", "");
		return a;
	}
	
	@RequestMapping(value = "/label/all" , method = RequestMethod.POST)
	@ResponseBody
	public String labelAll(){
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
		if (bodyInfo.get("second_uuid") == null) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否完整");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("second_uuid"))) {
			json.put("result", "1");
			json.put("description", "操作失败，请检查输入的参数是否正确");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("secondUuid", bodyInfo.getString("second_uuid"));
		Integer count = 0;
		count = thirdCategorysService.totalCount(map);
		map.put("start", 0);
		map.put("end", count);
		List<Map<String, Object>> list = thirdCategorysService.selectByPaging(map);
		model.put("description", "查询成功");
		model.put("result", "0");
		model.put("list", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		a = a.replaceAll("\\\\", "");
		return a;
	}
}

