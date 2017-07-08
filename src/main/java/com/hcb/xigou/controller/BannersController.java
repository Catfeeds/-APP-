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
import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.service.IBannersService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("banner/")
public class BannersController extends BaseController{
	
	@Autowired
	IBannersService bannersService;
	
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
			map.put("bannerUuids", "bannerUuids");
			if(headInfo.getString("store_uuid")!=null&&!"".equals(headInfo.getString("store_uuid"))){
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}
			int rs = bannersService.deleteByBannerUuids(map);
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
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
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
			banner.setUpdateDatetime(new Date());
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
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("banner_uuid") == null||bodyInfo.get("banner_status") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Banners banner = bannersService.selectByBannerUuid(bodyInfo.getString("banner_uuid"));
		if(banner!=null){
			banner.setBannerStatus(bodyInfo.getInt("banner_status"));
			banner.setUpdateDatetime(new Date());
			int rs = 0;
			rs = bannersService.updateByPrimaryKeySelective(banner);
			if(rs == 1){
				json.put("result", 0);
				json.put("description", "更改banner状态成功");
				return buildReqJsonObject(json);
			}else{
				json.put("result", 1);
				json.put("description", "更改banner状态失败，请重试");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", 1);
			json.put("description", "更改banner状态失败，未查询到Banner信息");
			return buildReqJsonObject(json);
		}
	}
}
