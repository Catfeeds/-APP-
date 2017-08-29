
package com.hcb.xigou.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.pojo.GoodsWithBLOBs;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.util.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("activity/")
public class ActivityController extends BaseController{

	@Autowired
	IActivityZonesService activityZonesService;
	@Autowired
	GoodsService goodsService;
	
	
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
		if (bodyInfo.get("start_time")==null||bodyInfo.get("end_time")==null||
				bodyInfo.get("position")==null||bodyInfo.get("url")==null||	
				bodyInfo.get("image") == null||headInfo.get("store_uuid") == null) {
			json.put("result", 1);
			json.put("description", "参数不完整");
			return buildReqJsonObject(json);
		}
		ActivityZones activity = new ActivityZones();
		String activityUuid = "";
		try {
			activityUuid = MD5Util.md5Digest(bodyInfo.getString("image") + System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = new Date(bodyInfo.getString("start_time"));
		Date endtTime = new Date(bodyInfo.getString("end_time"));
		try {
			String str = null;
			startTime=format.parse(str);
			endtTime=format.parse(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bodyInfo.get("open_time")!=null){
			if(!"".equals(bodyInfo.getString("open_time"))){
				Date openTime = new Date(bodyInfo.getString("open_time"));
				try {
					String str = null;
					openTime=format.parse(str);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				activity.setOpenTime(openTime);
			}
		}
		activity.setStartTime(startTime);
		activity.setEndTime(endtTime);
		activity.setIsOpen(bodyInfo.getString("is_open"));
		activity.setPosition(Byte.valueOf((byte)bodyInfo.getInt("position")));
		activity.setUrl(bodyInfo.getString("url"));
		activity.setActivityUuid(activityUuid);
		activity.setImage(bodyInfo.getString("image"));
		activity.setType("activity");
		activity.setStoreUuid(headInfo.getString("store_uuid"));
		if(bodyInfo.get("description") != null){
			activity.setDescription(bodyInfo.getString("description"));
		}
		if(bodyInfo.get("good_uuid") != null){
			if(!bodyInfo.getString("good_uuid").equals(""))activity.setGoodUuid(bodyInfo.getString("good_uuid"));
		}
		if(bodyInfo.get("eventId") != null){
			if(!bodyInfo.getString("eventId").equals(""))activity.setActivityId(bodyInfo.getString("eventId"));
		}
		
		int rs = 0;
		rs = activityZonesService.insertSelective(activity);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加活动成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加活动失败，请重试");
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
		if (bodyInfo.get("start_time")==null||bodyInfo.get("end_time")==null||
				bodyInfo.get("image")==null||bodyInfo.get("activity_uuid")==null||
				bodyInfo.get("is_open")==null||bodyInfo.get("url")==null||
				bodyInfo.get("position")==null||headInfo.get("store_uuid") == null) {
				json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		ActivityZones activity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if(activity !=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime = new Date(bodyInfo.getString("start_time"));
			Date endtTime = new Date(bodyInfo.getString("end_time"));
		
			try {
				String str = null;
				startTime=format.parse(str);
				endtTime=format.parse(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(bodyInfo.get("open_time")!=null){
				if(!"".equals(bodyInfo.getString("open_time"))){
					Date openTime = new Date(bodyInfo.getString("open_time"));
					try {
						String str = null;
						openTime=format.parse(str);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					activity.setOpenTime(openTime);
				}
			}
			activity.setStartTime(startTime);
			activity.setEndTime(endtTime);
			activity.setIsOpen(bodyInfo.getString("is_open"));
			activity.setPosition(Byte.valueOf((byte)bodyInfo.getInt("position")));
			activity.setUrl(bodyInfo.getString("url"));
			activity.setActivityUuid(bodyInfo.getString("activity_uuid"));
			activity.setImage(bodyInfo.getString("image"));
			activity.setIsStop("2");
			if(bodyInfo.get("good_uuid") != null){
				if(!bodyInfo.getString("good_uuid").equals(""))activity.setGoodUuid(bodyInfo.getString("good_uuid"));
			}
			if(bodyInfo.get("eventId") != null){
				if(!bodyInfo.getString("eventId").equals(""))activity.setActivityId(bodyInfo.getString("eventId"));
			}
			int rs = 0;
			rs = activityZonesService.updateByActivityUuid(activity);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "编辑活动成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "编辑活动失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "没有此活动，请重试");
			return buildReqJsonObject(json);
		}
		
	}
	
	
	@RequestMapping("isStop")
	@ResponseBody
	public String isStop(){
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		ActivityZones activity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		
		if (activity != null) {
			if (activity.getIsStop().equals("2")) {
				activity.setIsStop("1");
				int num = activityZonesService.selectByActivityCount();
				int rs = 0;
				if (num >= 4) {
					json.put("result", -1);
					json.put("description", "修改活动状态失败，超过可上架活动数量限制");
					return buildReqJsonObject(json);
				} else {
					rs = activityZonesService.updateByActivityUuid(activity);
					if (rs == 1) {
						json.put("result", 0);
						json.put("description", "修改状态活动成功");
						return buildReqJsonObject(json);
					} else {
						json.put("result", 1);
						json.put("description", "修改活动状态失败，请重试");
						return buildReqJsonObject(json);
					}
				}
			} else {
				activity.setIsStop("2");
				int rs = 0;
				rs = activityZonesService.updateByActivityUuid(activity);
				if (rs == 1) {
					json.put("result", 0);
					json.put("description", "修改状态活动成功");
					return buildReqJsonObject(json);
				} else {
					json.put("result", 1);
					json.put("description", "修改活动状态失败，请重试");
					return buildReqJsonObject(json);
				}
			}
		} else {
			json.put("result", 1);
			json.put("description", "查询不到此活动，请重试");
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
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "activity_uuid为空");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("activity_uuid");
		List<String> activityUuids = new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++){
			activityUuids.add(jsonArray.getString(i));
		}
		if(activityUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("activityUuids", activityUuids);
			map.put("deleteAt", "del");
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = activityZonesService.deleteByActivityUuids(map);
			if(rs >= 1){
				json.put("result", 0);
				json.put("description", "删除活动成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "删除活动失败，请重试");
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
		if (bodyInfo.get("activity_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		ActivityZones activity = activityZonesService.selectByActivityUuid(bodyInfo.getString("activity_uuid"));
		if(activity!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("activity", activity);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到activity信息");
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
		// 登录认证失败
		if (sign == 2) {
			json.put("result", 2);
			json.put("description", "验证失败，user_uuid或密码不正确2");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("pageIndex") == null || bodyInfo.get("pageSize") == null) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否完整33");
			return buildReqJsonObject(json);
		}
		if ("".equals(bodyInfo.get("pageIndex")) || "".equals(bodyInfo.get("pageSize"))) {
			json.put("result", 1);
			json.put("description", "操作失败，请检查输入的参数是否正确4");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();

		List<ActivityZones> list = new ArrayList<ActivityZones>();
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
			list = activityZonesService.searchActivityList(map);
			for (ActivityZones item : list) {
				SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if(item.getEndTime()!=null&&item.getOpenTime()!=null){
					String fromDate = simpleFormat.format(item.getOpenTime());  
					String toDate = simpleFormat.format(item.getEndTime());  
					try {
						long from = simpleFormat.parse(fromDate).getTime();
						long to = simpleFormat.parse(toDate).getTime();
						if(to>from){
							int m = (int) ((to - from)/(1000)); 
							int day = m/60/60/24;
							int hh = (m - day*60*60*24)/60/60;
							int mm =((m - day*60*60*24) - hh*60*60)/60;
							String str =day+"/"+hh+"/"+mm;
							item.setSurplusTime(str);
						}else{
							fromDate = simpleFormat.format(item.getStartTime());  
							toDate = simpleFormat.format(item.getEndTime());  
							try {
								from = simpleFormat.parse(fromDate).getTime();
								to = simpleFormat.parse(toDate).getTime();  
								int m = (int) ((to - from)/(1000)); 
								int day = m/60/60/24;
								int hh = (m - day*60*60*24)/60/60;
								int mm =((m - day*60*60*24) - hh*60*60)/60;
								String str =day+"/"+hh+"/"+mm;
								item.setSurplusTime(str);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					String fromDate = simpleFormat.format(item.getStartTime());  
					String toDate = simpleFormat.format(item.getEndTime());  
					try {
						long from = simpleFormat.parse(fromDate).getTime();
						long to = simpleFormat.parse(toDate).getTime();  
						int m = (int) ((to - from)/(1000)); 
						int day = m/60/60/24;
						int hh = (m - day*60*60*24)/60/60;
						int mm =((m - day*60*60*24) - hh*60*60)/60;
						String str =day+"/"+hh+"/"+mm;
						item.setSurplusTime(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Integer count = 0;
			count = activityZonesService.countActivityInt(map);
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
		model.put("activityList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping(value = "good/info" , method = RequestMethod.POST)
	@ResponseBody
	public String activityOfGoodInfo(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
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
		
		ModelMap model = new ModelMap();
		GoodsWithBLOBs good = goodsService.selectGoodByGoodUuid(bodyInfo.getString("good_uuid"));
		if(good != null){
			if(good.getFirstUuid() != null)model.put("first_uuid",good.getFirstUuid());
			if(good.getFirtCategoryName() != null)model.put("first_ctatgory_name",good.getFirtCategoryName());
			if(good.getSecondUuid() != null)model.put("second_uuid",good.getSecondUuid());
			if(good.getSecondCategoryName() != null)model.put("secont_catagory_name",good.getSecondCategoryName());
			if(good.getGoodUuid() != null)model.put("good_uuid",good.getGoodUuid());
			if(good.getGoodName() != null)model.put("good_name",good.getGoodName());
		}else{
			model.put("result",1);
			model.put("description", "查询失败");
			return buildReqJsonObject(model);
		}
		model.put("description", "查询成功");
		model.put("result",0);
		return buildReqJsonObject(model);
	}
}

