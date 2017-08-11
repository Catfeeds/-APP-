package com.hcb.xigou.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Dailys;
import com.hcb.xigou.service.IDailysService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.RandomStringGenerator;
import com.hcb.xigou.util.StringToDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("daily")
public class DailyController extends BaseController{

	@Autowired
	IDailysService dailysService;
	
	@RequestMapping(value = "publish/{type}" , method = RequestMethod.POST)
	public String dailyOfPublish(@PathVariable String type){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("total_money") == null || bodyInfo.get("numbers") ==null || 
			bodyInfo.get("the_number") == null || bodyInfo.get("highest_amount") ==null ||
			bodyInfo.get("start_time") == null || bodyInfo.get("end_time") == null ||
			bodyInfo.get("is_open") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Dailys daily = new Dailys();
		daily.setCreateDatetime(Timestamp.from(Instant.now()));
		daily.setTotalMoney(new BigDecimal(bodyInfo.getString("total_money")));
		daily.setNumbers(Long.valueOf(bodyInfo.getString("numbers")));
		daily.setTheNumber(bodyInfo.getInt("the_number"));
		daily.setHighestAmount(new BigDecimal(bodyInfo.getString("highest_amount")));
		daily.setStartTime(StringToDate.stringtoDateTime(bodyInfo.getString("start_time")));
		daily.setEndTime(StringToDate.stringtoDateTime(bodyInfo.getString("end_time")));
		daily.setIsOpen(bodyInfo.getString("is_open"));
		daily.setType(type);
		daily.setSplitMoney(new BigDecimal(bodyInfo.getString("total_money")));
		daily.setSplitNumbers(Long.valueOf(bodyInfo.getString("numbers")));
		try {
			daily.setDailyUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer rs = dailysService.insertSelective(daily);
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
	
	@RequestMapping(value = "edit" , method = RequestMethod.POST)
	public String dailyOfEdit(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("daily_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Dailys daily = dailysService.selectByDailyUuid(bodyInfo.getString("daily_uuid"));
		if(daily == null){
			json.put("result", 1);
			json.put("description", "未查询到每日领取信息");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("total_money") == null){
			daily.setTotalMoney(new BigDecimal(bodyInfo.getString("total_money")));
			daily.setSplitMoney(new BigDecimal(bodyInfo.getString("total_money")));
		}
		if(bodyInfo.get("numbers") != null){
			daily.setNumbers(Long.valueOf(bodyInfo.getString("numbers")));
			daily.setSplitNumbers(Long.valueOf(bodyInfo.getString("numbers")));
		}
		if(bodyInfo.get("the_number") != null){
			daily.setTheNumber(bodyInfo.getInt("the_number"));
		}
		if(bodyInfo.get("highest_amount") != null){
			daily.setHighestAmount(new BigDecimal(bodyInfo.getString("highest_amount")));
		}
		if(bodyInfo.get("start_time") != null){
			daily.setStartTime(StringToDate.stringtoDateTime(bodyInfo.getString("start_time")));
		}
		if(bodyInfo.get("end_time") != null){
			daily.setEndTime(StringToDate.stringtoDateTime(bodyInfo.getString("end_time")));
		}
		if(bodyInfo.get("is_open") != null){
			daily.setIsOpen(bodyInfo.getString("is_open"));
		}
		Integer rs = dailysService.updateByPrimaryKeySelective(daily);
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
	
	@RequestMapping(value = "delete" ,method = RequestMethod.POST)
	public String dailyOfDelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("daily_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Boolean flag = false;
	    JSONArray array = JSONArray.fromObject(bodyInfo.getString("daily_uuid"));
	    for (int i = 0; i < array.size(); i++) {
	      	if(array.get(i).toString() != null){
              Dailys daily = dailysService.selectByDailyUuid(array.get(i).toString());
				if (daily != null) {
					daily.setDeleteAt("del");
					Integer rs = dailysService.updateByPrimaryKeySelective(daily);
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
	
	@RequestMapping(value = "status" , method = RequestMethod.POST)
	public String dailyOfStatus(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("daily_uuid") == null || bodyInfo.get("is_open") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Dailys daily = dailysService.selectByDailyUuid(bodyInfo.getString("daily_uuid"));
		if(daily == null){
			json.put("result", 1);
			json.put("description", "未查询到每日领取信息");
			return buildReqJsonObject(json);
		}
		daily.setIsOpen(bodyInfo.getString("is_open"));
		if(bodyInfo.getString("is_open").equals("yes")){
			daily.setOpenTime(Timestamp.from(Instant.now()));
		}
		Integer rs = dailysService.updateByPrimaryKeySelective(daily);
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
	
	@RequestMapping(value = "list" , method = RequestMethod.POST)
	public String dailyOfList(){
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
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			int start = (pageIndex - 1) * pageSize;
			map.put("start", start);
			map.put("end", pageSize);
			if(bodyInfo.get("type") != null){
				map.put("type", bodyInfo.getString("type"));
			}
            if(bodyInfo.get("start_time") != null){
            	map.put("startTime", bodyInfo.getString("start_time"));
			}
            if(bodyInfo.get("end_time") != null){
            	map.put("endTime", bodyInfo.getString("end_time"));
			}
            List<Dailys> dailys = dailysService.selectByPaging(map);
            Integer count = 0;
            count = dailysService.totalCount(map);
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
            for (Dailys daily : dailys) {
				if(daily != null){
					Map<String, Object> mop = new HashMap<String, Object>();
					mop.put("daily_uuid", daily.getDailyUuid());
					mop.put("type", daily.getType());
					mop.put("total_money", daily.getTotalMoney());
					mop.put("numbers", daily.getNumbers());
					mop.put("the_number", daily.getTheNumber());
					mop.put("highest_amount", daily.getHighestAmount());
					mop.put("start_time", StringToDate.dateToStringTime(daily.getStartTime()));
					mop.put("end_time", StringToDate.dateToStringTime(daily.getEndTime()));
					mop.put("is_open", daily.getIsOpen());
					mop.put("split_numbers", daily.getSplitNumbers());
					mop.put("split_money", daily.getSplitMoney());
					list.add(mop);
				}
			}
		}
		model.put("description", "查询成功");
		model.put("result", "0");
		model.put("list", list);
		return buildReqJsonObject(model);
	}
}
