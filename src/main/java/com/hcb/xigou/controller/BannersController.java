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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.service.GoodsService;
import com.hcb.xigou.service.IActivityZonesService;
import com.hcb.xigou.service.IBannersService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.StringToDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@CrossOrigin
@RequestMapping("banner/")
public class BannersController extends BaseController{
	
	@Autowired
	IBannersService bannersService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	IActivityZonesService activityZonesService;
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		
		JSONArray jsonArray = bodyInfo.getJSONArray("banner_uuid");
		
		List<String> bannerUuids = new ArrayList<String>();
		
		for(int i=0;i<jsonArray.size();i++){
			bannerUuids.add(jsonArray.getString(i));
		}
		if(bannerUuids.size()>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bannerUuids", bannerUuids);
			int rs = bannersService.deleteByBannerUuids(map);
			if(rs >= 1){
				json.put("result",0);
				json.put("description", "删除成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result",1);
				json.put("description", "请检查参数格式是否正确或者参数是否完整");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result",1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public String selectGoodid(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Banners banner = bannersService.selectByBannerUuid(bodyInfo.getString("banner_uuid"));
		if(banner!=null){
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("banner", banner);
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "未查询到Banner信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("update_image")
	@ResponseBody
	public String updateBannerImage(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null||bodyInfo.get("url") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Banners banner = bannersService.selectByBannerUuid(bodyInfo.getString("banner_uuid"));
		if(banner!=null){
			banner.setUrl(bodyInfo.getString("url"));
			int rs = 0;
			rs = bannersService.updateByPrimaryKeySelective(banner);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "替换banner图片成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "替换banner图片失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "替换banner图片失败，未查询到Banner信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping("update_status")
	@ResponseBody
	public String updateBannerStatus(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null ||
				bodyInfo.get("banner_status") ==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		Banners banner = new Banners();
		int i = bannersService.selectByBannerStatus();
		if ("1".equals(bodyInfo.getString("banner_status"))) {
			banner.setBannerStatus(2);
			banner.setShelvesTime(null);
			int rs = 0;
			banner.setBannerUuid(bodyInfo.getString("banner_uuid"));
			rs = bannersService.updateByPrimaryKey(banner);
			if (rs == 1) {
				json.put("result", 0);
				json.put("description", "更改banner状态成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "更改banner状态失败，请重试");
				return buildReqJsonObject(json);
			}
		}
		if ("2".equals(bodyInfo.getString("banner_status"))) {
			banner.setBannerStatus(1);
			banner.setShelvesTime(Timestamp.from(Instant.now()));
			banner.setBannerUuid(bodyInfo.getString("banner_uuid"));
			int rs = 0;
			if(i>=4){
				json.put("result", -1);
				json.put("description", "修改失败，超过可使用banner数量限制");
				return buildReqJsonObject(json);
			}else{
				rs = bannersService.updateByPrimaryKeySelective(banner);
				if (rs == 1) {
					//默認第一張
					Map<String, Object> mtp = new HashMap<String, Object>();
					mtp.put("currentindex", 1);
					mtp.put("type", "home");
					Banners b1 = bannersService.selectByCurrentindex(mtp);
					Banners b2 = bannersService.selectByBannerUuid(bodyInfo.getString("banner_uuid"));
					if(b1 != null && b2 != null){
						b1.setCurrentindex(b2.getCurrentindex());
						b2.setCurrentindex(b1.getCurrentindex());
						bannersService.updateByPrimaryKeySelective(b1);
						bannersService.updateByPrimaryKeySelective(b2);
					}
					json.put("result", 0);
					json.put("description", "更改banner状态成功");
					return buildReqJsonObject(json);
				} else {
					json.put("result", 1);
					json.put("description", "更改banner状态失败，请重试");
					return buildReqJsonObject(json);
				}
			}
		}
		json.put("result", 1);
		json.put("description", "参数错误");
		return buildReqJsonObject(json);
	}

	
	
	@RequestMapping("search")
	@ResponseBody
	public String search(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确2");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
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

		List<Banners> list = new ArrayList<Banners>();
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
			if(bodyInfo.get("banner_name") != null){
				if(!"".equals(bodyInfo.getString("banner_name"))){
					map.put("bannerName", bodyInfo.getString("banner_name"));
				}
			}
			if(headInfo.getString("store_uuid")!=null){
				if(!"".equals(headInfo.getString("store_uuid"))){
					map.put("storeUuid", headInfo.getString("store_uuid"));
				}
			}
			list = bannersService.searchBannerByMap(map);
			Integer count = 0;
			count = bannersService.countBannerByMap(map);
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
				model.put("count", count);
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
		model.put("bannerList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping("searchselect")
	@ResponseBody
	public String searchselect(){
		JSONObject json = new JSONObject();
		if (sign == 1) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		// 登录认证失败
		if (sign == 2) {
			json.put("result", "2");
			json.put("description", "验证失败，user_uuid或密码不正确2");
			return buildReqJsonInteger(2, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
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

		List<Banners> list = new ArrayList<Banners>();
		Integer pageIndex = bodyInfo.getInt("pageIndex");
		pageIndex=1;
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
			if(bodyInfo.get("banner_name") != null){
				if(!"".equals(bodyInfo.getString("banner_name"))){
					map.put("bannerName", bodyInfo.getString("banner_name"));
				}
			}
			if(headInfo.getString("store_uuid")!=null){
				if(!"".equals(headInfo.getString("store_uuid"))){
					map.put("storeUuid", headInfo.getString("store_uuid"));
				}
			}
			list = bannersService.searchBannerByMap(map);
			Integer count = 0;
			count = bannersService.countBannerByMap(map);
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
				model.put("count", count);
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
		model.put("bannerList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public String insertBanners(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("currentIndex")==null||bodyInfo.get("banner_name") == null||bodyInfo.get("url") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		Banners banner = new Banners();
		String bannerUuid = "";
		try {
			bannerUuid = MD5Util.md5Digest(bodyInfo.getString("banner_name") + System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		banner.setBannerUuid(bannerUuid);
		banner.setType("home");
		banner.setUrl(bodyInfo.getString("url"));
		Map<String, Object> mtp = new HashMap<String, Object>();
		mtp.put("currentindex", bodyInfo.getInt("currentIndex"));
		mtp.put("type", "home");
		Banners b1 = bannersService.selectByCurrentindex(mtp);
		if(b1 != null){
			bannersService.deleteByPrimaryKey(b1.getFakeId());
		}else{
			//暂不处理
		}
		banner.setCurrentindex(bodyInfo.getInt("currentIndex"));
		banner.setBannerName(bodyInfo.getString("banner_name"));
		banner.setStoreUuid(headInfo.getString("store_uuid"));
		if(bodyInfo.get("web_url") != null){
			banner.setWebUrl(bodyInfo.getString("web_url"));
		}
		if(bodyInfo.get("good_uuid") != null){
			banner.setGoodUuid(bodyInfo.getString("good_uuid"));
		}
		if(bodyInfo.get("eventId") != null){
			banner.setActivityId(bodyInfo.getInt("eventId"));
		}
		if(bodyInfo.get("start_time") != null){
			banner.setStartTime(StringToDate.stringToDateStart(bodyInfo.getString("start_time")));
		}
		if(bodyInfo.get("end_time") != null){
			banner.setEndTime(StringToDate.stringToDateStart(bodyInfo.getString("end_time")));
		}
		int rs = 0;
		rs = bannersService.insertSelective(banner);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加banner图片成功");
			return buildReqJsonObject(json);
		}else{
			json.put("result", 1);
			json.put("description", "添加banner图片失败，请重试");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping(value = "/sort",method = RequestMethod.POST)
	@ResponseBody
	public String bannerSort(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null || bodyInfo.get("currentIndex") ==null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整2");
			return buildReqJsonObject(json);
		}
		Map<String, Object> mtp = new HashMap<String, Object>();
		mtp.put("currentindex", bodyInfo.getInt("currentIndex"));
		mtp.put("type", "home");
		Banners b1 = bannersService.selectByCurrentindex(mtp);
		Banners b2 = bannersService.selectByBannerUuid(bodyInfo.getString("banner_uuid"));
		if(b2 == null){
			json.put("result", 1);
			json.put("description", "未查詢到輪播圖信息");
			return buildReqJsonObject(json);
		}
		if(b1 == null){
          b2.setCurrentindex(bodyInfo.getInt("currentIndex"));
          bannersService.updateByPrimaryKeySelective(b2);
		}else{
			b1.setCurrentindex(b2.getCurrentindex());
			b2.setCurrentindex(bodyInfo.getInt("currentIndex"));
			bannersService.updateByPrimaryKeySelective(b1);
			bannersService.updateByPrimaryKeySelective(b2);
		}
		json.put("result", 0);
		json.put("description", "更改成功");
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "all/goods" , method = RequestMethod.POST)
	public String allOfGoods(){
		JSONObject json = new JSONObject();
		List<Map<String, Object>> list = goodsService.selectByAll();
		json.put("result", 0);
		json.put("description", "查询成功");
		json.put("list", list);
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "all/events" , method = RequestMethod.POST)
	public String allOfEvents(){
		JSONObject json = new JSONObject();
		List<Map<String, Object>> list = activityZonesService.selectByAll();
		json.put("result", 0);
		json.put("description", "查询成功");
		json.put("list", list);
		return buildReqJsonObject(json);
	}
}
