package com.hcb.xigou.controller;

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
import com.hcb.xigou.dto.Notices;
import com.hcb.xigou.service.INoticesService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.RandomStringGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("notice")
public class NoticeController extends BaseController{
	
	@Autowired
	INoticesService noticesService;
	
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String noticeCreate(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("content") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Notices notice = new Notices();
		notice.setContent(bodyInfo.getString("content"));
		notice.setCreateDatetime(new Date());
		notice.setIsOpen("no");
		notice.setType("system");;
		try {
			notice.setNoticeUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer rs = noticesService.insertSelective(notice);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "新增成功");
		}else{
			json.put("result", 1);
			json.put("description", "新增失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
    public String noticeDelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("notice_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Boolean flag = false;
		JSONArray array = JSONArray.fromObject(bodyInfo.getString("notice_uuid"));
		 for (int i = 0; i < array.size(); i++) {
			 if(array.get(i).toString() != null){
				 Notices notice = noticesService.selectByNoticeUuid(array.get(i).toString());
				 if(null != notice){
					 notice.setDeleteAt("del");
					 Integer rs = noticesService.updateByPrimaryKeySelective(notice);
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

	@RequestMapping(value = "shelves", method = RequestMethod.POST)
	public String noticeShelves(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("notice_uuid") == null || bodyInfo.get("is_open") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Notices notice = noticesService.selectByNoticeUuid(bodyInfo.getString("notice_uuid"));
		if(null == notice){
			json.put("result", 1);
			json.put("description", "未查询到滚动条信息");
			return buildReqJsonObject(json);
		}
		if("yes".equals(bodyInfo.getString("is_open"))){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", 0);
			map.put("end", noticesService.totalCount(map));
			List<Map<String, Object>> list = noticesService.selectByPaging(map);
			if(list.size() >= 5){
				json.put("result", 1);
				json.put("description", "最多上架5个滚动条");
				return buildReqJsonObject(json);
			}
			notice.setIsOpen("yes");
			notice.setOpenTime(new Date());
		}else{
			notice.setIsOpen("no");
			notice.setOpenTime(null);
			notice.setCloseTime(new Date());
		}
		Integer rs = noticesService.updateByPrimaryKey(notice);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "更改成功");
		}else{
			json.put("result", 1);
			json.put("description", "更改失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String noticeList(){
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
			List<Map<String, Object>> list = noticesService.selectByPaging(map);
			model.put("list", list);
			Integer count = 0;
			count = noticesService.totalCount(map);
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
