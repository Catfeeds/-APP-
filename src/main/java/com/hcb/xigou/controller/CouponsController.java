
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.controller.base.BaseController;
import com.hcb.xigou.dto.Coupons;
import com.hcb.xigou.service.ICouponsService;
import com.hcb.xigou.util.MD5Util;
import com.hcb.xigou.util.StringToDate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@CrossOrigin
@RequestMapping("coupons/")
public class CouponsController extends BaseController {

	@Autowired
	ICouponsService couponsService;

	@RequestMapping("delete")
	@ResponseBody
	public String delete() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		// JSONObject headInfo = JSONObject.fromObject(headString);
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null) {
			json.put("result", 1);
			json.put("description", "coupon_uuid为空");
			return buildReqJsonObject(json);
		}
		JSONArray jsonArray = bodyInfo.getJSONArray("coupon_uuid");
		List<String> couponUuids = new ArrayList<String>();
		for (int i = 0; i < jsonArray.size(); i++) {
			couponUuids.add(jsonArray.getString(i));
		}
		if (couponUuids.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("couponUuids", couponUuids);
			map.put("deleteAt", "del");
			int rs = couponsService.deleteByCouponUuids(map);
			if (rs >= 1) {
				json.put("result", 0);
				json.put("description", "删除优惠券成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "删除优惠券失败，请重试");
				return buildReqJsonObject(json);
			}
		} else {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("detail")
	@ResponseBody
	public String detail() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Coupons coupon = couponsService.selectByCouponUuid(bodyInfo.getString("coupon_uuid"));
		if (coupon != null) {
			json.put("result", 0);
			json.put("description", "查询成功");
			json.put("coupon", coupon);
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "未查询到coupon信息");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("search")
	@ResponseBody
	public String search() {
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

		List<Coupons> list = new ArrayList<Coupons>();
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
			/*
			 * if(bodyInfo.get("bannerName") == null){ map.put("bannerName",
			 * bodyInfo.getString("bannerName")); }
			 */
			if (headInfo.getString("store_uuid") != null && !"".equals(headInfo.getString("store_uuid"))) {
				map.put("storeUuid", headInfo.getString("store_uuid"));
			}

			list = couponsService.searchCouponByMap(map);
			Integer count = 0;
			count = couponsService.countCouponByMap(map);
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
		model.put("result", 0);
		model.put("couponList", list);
		String a = buildReqJsonObject(model);
		a = a.replace("\"[", "[");
		a = a.replace("]\"", "]");
		return a;
	}

	@RequestMapping("insert")
	@ResponseBody
	public String insert() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("amount") == null || bodyInfo.get("grant_time") == null || bodyInfo.get("fail_time") == null
				|| bodyInfo.get("url") == null || bodyInfo.get("coupon_name") == null
				|| bodyInfo.get("coupon_stock") == null || bodyInfo.get("type") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createTime = new Date();
		try {
			String createAt = null;
			createTime = format.parse(createAt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String couponUuid = "";
		try {
			couponUuid = MD5Util.md5Digest(bodyInfo.getString("amount") + System.currentTimeMillis() + RandomStringUtils.random(8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Coupons coupon = new Coupons();
		coupon.setAmount(bodyInfo.getString("amount"));
		coupon.setTitle(bodyInfo.getString("coupon_name"));
		coupon.setCouponStock(bodyInfo.getInt("coupon_stock"));
		coupon.setType(bodyInfo.getString("type"));
		coupon.setDescription(bodyInfo.getString("url"));
		coupon.setCouponUuid(couponUuid);
		coupon.setCreateDatetime(createTime);
		coupon.setGrantTime(StringToDate.stringToDateStart(bodyInfo.getString("grant_time")));
		coupon.setFailTime(StringToDate.stringToDateStart(bodyInfo.getString("fail_time")));
		coupon.setIsGrant("1");
		coupon.setGroups("coupon");
		if(bodyInfo.get("rule_one") != null){
			coupon.setRuleOne(bodyInfo.getString("rule_one"));
		}
		if(bodyInfo.get("rule_two") != null){
			coupon.setRuleTwo(bodyInfo.getString("rule_two"));
		}
		int rs = 0;
		rs = couponsService.insertSelective(coupon);
		if (rs == 1) {
			json.put("result", 0);
			json.put("description", "添加优惠券成功");
			return buildReqJsonObject(json);
		} else {
			json.put("result", 1);
			json.put("description", "添加优惠券失败，请重试");
			return buildReqJsonObject(json);
		}
	}

	@RequestMapping("couponStock")
	@ResponseBody
	public String couponStock() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Coupons coupon = couponsService.selectByCouponUuid(bodyInfo.getString("coupon_uuid"));
		if (coupon != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date updateTime = new Date();
			try {
				String updateAt = null;
				updateTime = format.parse(updateAt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			coupon.setUpdateDatetime(updateTime);
			int couponStock = coupon.getCouponStock() + bodyInfo.getInt("coupon_stock");
			coupon.setCouponStock(couponStock);
			int rs = 0;
			rs = couponsService.updateByCouponUuid(coupon);
			if (rs == 1) {
				json.put("result", 0);
				json.put("description", "添加优惠券库存成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "添加优惠券库存失败，请重试");
				return buildReqJsonObject(json);
			}
		} else {
			json.put("result", 1);
			json.put("description", "查询不到优惠券库存，请重试");
			return buildReqJsonObject(json);
		}

	}

	@RequestMapping("isGrant")
	@ResponseBody
	public String isGrant() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("coupon_uuid") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Coupons coupon = couponsService.selectByCouponUuid(bodyInfo.getString("coupon_uuid"));
		if (coupon != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date updateTime = new Date();
			try {
				String updateAt = null;
				updateTime = format.parse(updateAt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			coupon.setUpdateDatetime(updateTime);
			if(coupon.getIsGrant().equals("1")){
				coupon.setIsGrant("2");
			}else{
				coupon.setIsGrant("1");
			}
			
			
			int rs = 0;
			rs = couponsService.updateByCouponUuid(coupon);
			if (rs == 1) {
				json.put("result", 0);
				json.put("description", "发放优惠券成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "发放优惠券失败，请重试");
				return buildReqJsonObject(json);
			}
		} else {
			json.put("result", 1);
			json.put("description", "查询不到优惠券，请重试");
			return buildReqJsonObject(json);
		}

	}

	
	@RequestMapping("update")
	@ResponseBody
	public String update() {
		JSONObject json = new JSONObject();
		if (sign == 1 || sign == 2) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo = JSONObject.fromObject(bodyString);
		if (bodyInfo.get("amount") == null || bodyInfo.get("grant_time") == null || bodyInfo.get("fail_time") == null
				|| bodyInfo.get("url") == null || bodyInfo.get("coupon_name") == null
				|| bodyInfo.get("coupon_stock") == null || bodyInfo.get("coupon_uuid") == null
				|| bodyInfo.get("type") == null) {
			json.put("result", 1);
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonObject(json);
		}
		Coupons coupon = couponsService.selectByCouponUuid(bodyInfo.getString("coupon_uuid"));

		if (coupon != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date updateTime = new Date();
			try {
				String updateAt = null;
				updateTime = format.parse(updateAt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			coupon.setAmount(bodyInfo.getString("amount"));
			coupon.setTitle(bodyInfo.getString("coupon_name"));
			coupon.setCouponStock(bodyInfo.getInt("coupon_stock"));
			coupon.setType(bodyInfo.getString("type"));
			coupon.setDescription(bodyInfo.getString("url"));
			coupon.setUpdateDatetime(updateTime);
			coupon.setGrantTime(StringToDate.stringToDateStart(bodyInfo.getString("grant_time")));
			coupon.setFailTime(StringToDate.stringToDateStart(bodyInfo.getString("fail_time")));
			coupon.setIsGrant("1");
			int rs = 0;
			rs = couponsService.updateByCouponUuid(coupon);
			if (rs == 1) {
				json.put("result", 0);
				json.put("description", "编辑优惠券成功");
				return buildReqJsonObject(json);
			} else {
				json.put("result", 1);
				json.put("description", "编辑优惠券失败，请重试");
				return buildReqJsonObject(json);
			}
		} else {
			json.put("result", 1);
			json.put("description", "查询不到优惠券，请重试");
			return buildReqJsonObject(json);
		}

	}
}

