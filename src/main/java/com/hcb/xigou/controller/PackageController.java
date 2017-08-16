package com.hcb.xigou.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.hcb.xigou.dto.Coupons;
import com.hcb.xigou.dto.Packages;
import com.hcb.xigou.service.ICouponsService;
import com.hcb.xigou.service.IPackagesService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.RandomStringGenerator;
import com.hcb.xigou.util.StringToDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("package")
public class PackageController extends BannersController{
	
	@Autowired
	IPackagesService packagesService;
	@Autowired
	ICouponsService couponsService;

	@RequestMapping(value = "publish" , method = RequestMethod.POST)
    public String packagePublish(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("package_name") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
    	Packages pack = new Packages();
    	pack.setCreateDatetime(new Date());
    	pack.setPackageName(bodyInfo.getString("package_name"));
    	if(bodyInfo.get("packeage_stock") != null){
    		pack.setPackeageStock(bodyInfo.getInt("packeage_stock"));
    	}
		if(bodyInfo.get("url") != null){
			pack.setUrl(bodyInfo.getString("url"));
		}
		if(bodyInfo.get("title") != null){
			pack.setTitle(bodyInfo.getString("title"));	
		}
		if(bodyInfo.get("banner") != null){
			pack.setBanner(bodyInfo.getString("banner"));
		}
		if(bodyInfo.get("start_time") != null){
			pack.setStartDatetime(StringToDate.stringToDateStart(bodyInfo.getString("start_time")));
		}
		if(bodyInfo.get("close_time") != null){
			pack.setCloseDatetime(StringToDate.stringToDateStart(bodyInfo.getString("close_time")));
		}
		try {
			pack.setPackageUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		Integer rs = packagesService.insertSelective(pack);
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
	
	@RequestMapping(value = "charge" , method = RequestMethod.POST)
	public String packageCharge(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("package_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Packages pack = packagesService.selectByPackageUuid(bodyInfo.getString("package_uuid"));
		if(pack == null){
			json.put("result", 1);
			json.put("description", "未查询到大礼包信息");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("url") != null){
			pack.setUrl(bodyInfo.getString("url"));
		}
		if(bodyInfo.get("title") != null){
			pack.setTitle(bodyInfo.getString("title"));	
		}
		if(bodyInfo.get("banner") != null){
			pack.setBanner(bodyInfo.getString("banner"));
		}
		if(bodyInfo.get("package_name") != null){
			pack.setPackageName(bodyInfo.getString("package_name"));
		}
		if(bodyInfo.get("packeage_stock") != null){
			pack.setPackeageStock(bodyInfo.getInt("packeage_stock"));
		}
		if(bodyInfo.get("start_time") != null){
			pack.setStartDatetime(StringToDate.stringToDateStart(bodyInfo.getString("start_time")));
		}
		if(bodyInfo.get("close_time") != null){
			pack.setCloseDatetime(StringToDate.stringToDateStart(bodyInfo.getString("close_time")));
		}
		Integer rs = packagesService.updateByPrimaryKeySelective(pack);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "更改成功");
		}else{
			json.put("result", 0);
			json.put("description", "更改失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "all" , method = RequestMethod.POST)
	public String packageAll(){
		JSONObject json = new JSONObject();
		List<Map<String, Object>> list = packagesService.selectByAll();
		json.put("result", 0);
		json.put("description", "查询成功");
		json.put("list", list);
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "delete" , method = RequestMethod.POST)
	public String  packageDelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("package_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Boolean flag = false;
	    JSONArray array = JSONArray.fromObject(bodyInfo.getString("package_uuid"));
	    for (int i = 0; i < array.size(); i++) {
		   if(array.get(i).toString() != null){
			   Packages pack = packagesService.selectByPackageUuid(array.get(i).toString());
			   if(pack != null){
				  pack.setDeleteAt("del");
				  Integer rs = packagesService.updateByPrimaryKeySelective(pack);
				  if(rs == 1){
					  flag = true;
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("packageUuid", array.get(i).toString());
						List<Coupons> coupos = couponsService.selectByPackageAll(map);
						for (Coupons coupon : coupos) {
							if(coupon != null){
								coupon.setDeleteAt("del");
								couponsService.updateByPrimaryKeySelective(coupon);
							}
						}
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
	
	@RequestMapping(value = "status" , method = RequestMethod.POST)
	public String packageStatus(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("package_uuid") == null || bodyInfo.get("package_status") ==null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Packages pack = packagesService.selectByPackageUuid(bodyInfo.getString("package_uuid"));
		if(pack == null){
			json.put("result", 1);
			json.put("description", "未查询到大礼包信息");
			return buildReqJsonObject(json);
		}
		pack.setPackageStatus(bodyInfo.getString("package_status"));
		Integer rs = packagesService.updateByPrimaryKeySelective(pack);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "更改成功");
		}else{
			json.put("result", 0);
			json.put("description", "更改失败");
			return buildReqJsonObject(json);
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "list" , method = RequestMethod.POST)
	public String packageList(){
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
			List<Map<String, Object>> list = packagesService.selectByPaging(map);
			model.put("list", list);
			Integer count = 0;
			count = packagesService.totalCount(map);
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
	
	@RequestMapping(value = "coupon/create" , method = RequestMethod.POST)
	public String couponOfCreate(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("package_uuid") == null || bodyInfo.get("title") ==null ||
			bodyInfo.get("amount") == null || bodyInfo.get("rule_one") ==null||
			bodyInfo.get("rule_two") == null || bodyInfo.get("description") ==null||
			bodyInfo.get("fail_time") == null || bodyInfo.get("url") ==null||
			bodyInfo.get("coupon_stock") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Packages pack = packagesService.selectByPackageUuid(bodyInfo.getString("package_uuid"));
		if(pack == null){
			json.put("result", 1);
			json.put("description", "未查询到大礼包信息");
			return buildReqJsonObject(json);
		}
		Coupons coupon = new Coupons();
		coupon.setCreateDatetime(new Date());
		coupon.setTitle(bodyInfo.getString("title"));
		coupon.setAmount(bodyInfo.getString("amount"));
		coupon.setRuleOne(bodyInfo.getString("rule_one"));
		coupon.setRuleTwo(bodyInfo.getString("rule_two"));
		coupon.setDescription(bodyInfo.getString("description"));
		int days = bodyInfo.getInt("fail_time");
		Date close = new Date();
	    Calendar calendar1 = Calendar.getInstance();// 日历对象
	    calendar1.setTime(close);// 设置到期日期
	    calendar1.add(Calendar.DATE, days);// 月份加一
	    Date closeTime = calendar1.getTime();
	    coupon.setFailTime(closeTime);
	    coupon.setUrl(bodyInfo.getString("url"));
	    coupon.setCouponStock(bodyInfo.getInt("coupon_stock"));
	    coupon.setPackageUuid(pack.getPackageUuid());
	    coupon.setGroups("gift");
	    coupon.setValidityDays(days);
	    coupon.setIsGrant("1");
	    coupon.setCouponStatus("0");
	    coupon.setType("1");
		try {
			coupon.setCouponUuid(MD5Util.md5Digest(RandomStringGenerator.getRandomStringByLength(32) + System.currentTimeMillis() + RandomStringUtils.random(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer rs = couponsService.insert(coupon);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "添加成功");
			pack.setCouponNumbers(pack.getCouponNumbers() + 1);
			packagesService.updateByPrimaryKeySelective(pack);
		}else{
			json.put("result", 1);
			json.put("description", "添加失败");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "coupon/edit" , method = RequestMethod.POST)
	public String couponOfEdit(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Coupons coupon = couponsService.selectByCouponUuid(bodyInfo.getString("coupon_uuid"));
		if(coupon == null){
			json.put("result", 1);
			json.put("description", "未查询到优惠券信息");
			return buildReqJsonObject(json);
		}
		if(bodyInfo.get("title") != null){
			coupon.setTitle(bodyInfo.getString("title"));
		}
		if(bodyInfo.get("amount") != null){
			coupon.setAmount(bodyInfo.getString("amount"));
		}
		if(bodyInfo.get("rule_one") != null){
			coupon.setRuleOne(bodyInfo.getString("rule_one"));
		}
		if(bodyInfo.get("rule_two") != null){
			coupon.setRuleTwo(bodyInfo.getString("rule_two"));
		}
		if(bodyInfo.get("description") != null){
			coupon.setDescription(bodyInfo.getString("description"));
		}
		if(bodyInfo.get("fail_time") != null){
			int days = bodyInfo.getInt("fail_time");
			Date close = new Date();
		    Calendar calendar1 = Calendar.getInstance();// 日历对象
		    calendar1.setTime(close);// 设置到期日期
		    calendar1.add(Calendar.DATE, days);// 月份加一
		    Date closeTime = calendar1.getTime();
		    coupon.setFailTime(closeTime);
		}
		if(bodyInfo.get("url") != null){
			 coupon.setUrl(bodyInfo.getString("url"));
		}
		if(bodyInfo.get("coupon_stock") != null){
			coupon.setCouponStock(bodyInfo.getInt("coupon_stock"));
		}
		Integer rs = couponsService.updateByPrimaryKeySelective(coupon);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "更改成功");
		}else{
			json.put("result", 0);
			json.put("description", "更改失败");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "coupon/delete" , method = RequestMethod.POST)
	public String couponOfDelete(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Boolean flag = false;
	    JSONArray array = JSONArray.fromObject(bodyInfo.getString("coupon_uuid"));
	    for (int i = 0; i < array.size(); i++) {
	    	if(array.get(i).toString() != null){
	    		Coupons coupon = couponsService.selectByCouponUuid(array.get(i).toString());
	    		if(coupon != null){
	    			coupon.setDeleteAt("del");
	    			Integer rs = couponsService.updateByPrimaryKeySelective(coupon);
	    			if(rs == 1){
						  flag = true;
						  Packages pack = packagesService.selectByPackageUuid(coupon.getPackageUuid());
						  if(pack != null){
							  pack.setCouponNumbers(pack.getCouponNumbers() - 1);
							  packagesService.updateByPrimaryKeySelective(pack);
						  }
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
	
	@RequestMapping(value = "coupon/status" , method = RequestMethod.POST)
	public String couponOfStatus(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null || bodyInfo.get("is_grant") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		Coupons coupon = couponsService.selectByCouponUuid(bodyInfo.getString("coupon_uuid"));
		if(coupon == null){
			json.put("result", 1);
			json.put("description", "未查询到优惠券信息");
			return buildReqJsonObject(json);
		}
		coupon.setIsGrant(bodyInfo.getString("is_grant"));
		Integer rs = couponsService.updateByPrimaryKeySelective(coupon);
		if(rs == 1){
			json.put("result", 0);
			json.put("description", "上下架成功");
		}else{
			json.put("result", 0);
			json.put("description", "上下架失败");
		}
		return buildReqJsonObject(json);
	}
	
	@RequestMapping(value = "detail" , method = RequestMethod.POST)
	public String packageDetail(){
		JSONObject json = new JSONObject();
		if (sign == 1||sign == 2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整1");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("package_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请输入必填项");
			return buildReqJsonObject(json);
		}
		ModelMap model = new ModelMap();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		map.put("packageUuid", bodyInfo.getString("package_uuid"));
		List<Coupons> coupos = couponsService.selectByPackageAll(map);
		for (Coupons coupon : coupos) {
			if(coupon != null){
				Map<String, Object> mvp = new HashMap<String, Object>();
				mvp.put("package_uuid", bodyInfo.getString("package_uuid"));
				mvp.put("coupon_uuid", coupon.getCouponUuid());
				mvp.put("title", coupon.getTitle());
				mvp.put("coupon_stock", coupon.getCouponStock());
				mvp.put("amount", coupon.getAmount());
				mvp.put("is_grant", coupon.getIsGrant());
				list.add(mvp);
			}
		}
		model.put("description", "查询成功");
		model.put("result", "0");
		model.put("list", list);
		return buildReqJsonObject(model);
	}
}
