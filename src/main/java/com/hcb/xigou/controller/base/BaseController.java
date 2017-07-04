package com.hcb.xigou.controller.base;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;
import com.hcb.xigou.util.Config;
import com.hcb.xigou.util.EmojiConvert;
@Scope("prototype")
public class BaseController {

	@Autowired
	private HttpServletRequest request;

	protected String webPath;
	//protected User user;
    protected StringBuffer requestUrl;
    protected String headString;
    protected String bodyString;
    protected int sign = 0; //sign等于1时表示参数错误，sign等于2时表示登录认证失败
	
    
	/**
	 * 所有的子类方法执行之前都要先执行此方法，子类方法不需要在model此方法中的参数
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @param platformUserName
	 * @param platformType
	 * @param code
	 */
	@ModelAttribute
	public void init(HttpServletRequest req,HttpServletResponse res) {
		//TODO
	}

	public String getBasePath() {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
	}

	public String getWebPath() {
		return Config.getString("webPath");
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	protected String toJsonString(Object obj) {
		return net.sf.json.JSONObject.fromObject(obj).toString();
	}
	
	protected com.hcb.xigou.bean.base.OutHead getDefaultOutHead() {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String time = format.format(date);
	        return new com.hcb.xigou.bean.base.OutHead()
	                .setReturnCode("000")
	                .setReturnDescription("验证通过")
	                .setSysTime(time);
	 }
	
	 protected String buildReqJson(List<Object> msgs) {
		 JSONObject jo = new JSONObject();
		 jo.put("head", getDefaultOutHead());
		 jo.put("body", msgs);
		 return jo.toJSONString();
	 }
	 
	 protected String buildReqJsonObject(Object msgs) {
		 JSONObject jo = new JSONObject();
		 jo.put("head", getDefaultOutHead());
		 jo.put("body", msgs);
			String json =  jo.toJSONString();
			try {
				json = EmojiConvert.emojiRecovery2(json);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
	 }
	 
	 protected com.hcb.xigou.bean.base.OutHead getLoginFailureOutHead() {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String time = format.format(date);
	        return new com.hcb.xigou.bean.base.OutHead()
	                .setReturnCode("002")
	                .setReturnDescription("验证失败，user_uuid或验证码不正确")
	                .setSysTime(time);
	}
	 
	 protected com.hcb.xigou.bean.base.OutHead getParamWrongOutHead() {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String time = format.format(date);
	        return new com.hcb.xigou.bean.base.OutHead()
	                .setReturnCode("001")
	                .setReturnDescription("请检查参数格式是否正确或者参数是否完整")
	                .setSysTime(time);
	}
	
	 protected String buildReqJsonInteger(Integer result,Object msgs) {
		 JSONObject jo = new JSONObject();
		 if(result == 1){
			 jo.put("head", getParamWrongOutHead());
		 }else if(result == 2){
			 jo.put("head", getLoginFailureOutHead());
		 }else{
			 jo.put("head", getDefaultOutHead());
		 }
		 jo.put("body", msgs);
		 return jo.toJSONString();
	 }
	
}
