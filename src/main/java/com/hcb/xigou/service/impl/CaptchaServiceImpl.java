package com.hcb.xigou.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.hcb.xigou.dao.interfaceClass.CaptchaMapper;
import com.hcb.xigou.dto.Captcha;
import com.hcb.xigou.service.ICaptchaService;

@Service("CaptchaService")
public class CaptchaServiceImpl implements ICaptchaService{
     
	@Autowired
	CaptchaMapper captchaMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return captchaMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Captcha record) {
		return captchaMapper.insert(record);
	}

	@Override
	public int insertSelective(Captcha record) {
		return captchaMapper.insertSelective(record);
	}

	@Override
	public Captcha selectByPrimaryKey(Integer fakeId) {
		return captchaMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Captcha record) {
		return captchaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Captcha record) {
		return captchaMapper.updateByPrimaryKey(record);
	}

	@Override
	public boolean sendTo(String phone) {

		boolean a = false;
		Captcha captcha = captchaMapper.selectByphone(phone);
        if(captcha == null){
        	Captcha captcha1 = new Captcha();
        	Random random = new Random();
        	String flag="";
        	for(int i=0;i<6;i++){
        		flag+=random.nextInt(10);
        	}
        	CCPRestSDK restAPI = new CCPRestSDK();
        	restAPI.init("sandboxapp.cloopen.com", "8883");
        	restAPI.setAccount("aaf98f89488d0aad0148ab8e790f0d1c", "fda1ca24f48c49daa959a0f5f095de53");
        	restAPI.setAppId("8a216da857dc1f6a0157e601e04d0dad");// 鍒濆鍖栧簲鐢↖D
        	HashMap<String, Object> result = restAPI.sendTemplateSMS(phone,"127669", new String[]{flag});
        	if("000000".equals(result.get("statusCode"))){
        		captcha1.setPhone(phone);
            	captcha1.setCaptcha(flag);
            	captcha1.setCreateDatetime(new Date());
            	Integer rs = captchaMapper.insertSelective(captcha1);
            	if(rs == 1){
            		a= true;
            	}else{
            		a= false;
            	}
    		}else{
    			a = false;
    		}
        	
         }else if(captcha !=null){
        	if(captcha.getUpdateDatetime()==null){
				Date date = new Date();
				//插入通知，推送
				Calendar calendar = Calendar.getInstance();//日历对象
				calendar.setTime(date);//设置到期日期
				calendar.add(Calendar.MINUTE, -1);//分钟减一
				Date pushDate = calendar.getTime();
				if(pushDate.before(captcha.getCreateDatetime())){
					//不能
					
					a =false;
				}else{
					//能
					Random random = new Random();
		        	String flag="";
		        	for(int i=0;i<6;i++){
		        		flag+=random.nextInt(10);
		        	}
		        	CCPRestSDK restAPI = new CCPRestSDK();
		        	restAPI.init("sandboxapp.cloopen.com", "8883");
		        	restAPI.setAccount("aaf98f89488d0aad0148ab8e790f0d1c", "fda1ca24f48c49daa959a0f5f095de53");
		        	restAPI.setAppId("8a216da857dc1f6a0157e601e04d0dad");// 鍒濆鍖栧簲鐢↖D
		        	HashMap<String, Object> result = restAPI.sendTemplateSMS(phone,"127669", new String[]{flag});
		        	if("000000".equals(result.get("statusCode"))){
		        		captcha.setCaptcha(flag);
		            	captcha.setUpdateDatetime(new Date());
		            	Integer rs = captchaMapper.updateByPrimaryKeySelective(captcha);
		    	        if(rs == 1){
		            		a= true;
		            	}else {
		            		a= false;
		            	}
		    		}else{
		    			a = false;
		    		}
					
				}
        		
        	} else{
				Date date = new Date();
				//插入通知，推送
				Calendar calendar = Calendar.getInstance();//日历对象
				calendar.setTime(date);//设置到期日期
				calendar.add(Calendar.MINUTE, -1);//分钟减一
				Date pushDate = calendar.getTime();
				if(pushDate.before(captcha.getUpdateDatetime())){
					//不能
					
					a = false;
				}else{
					//能
					Random random = new Random();
		        	String flag="";
		        	for(int i=0;i<6;i++){
		        		flag+=random.nextInt(10);
		        	}
		        	CCPRestSDK restAPI = new CCPRestSDK();
		        	restAPI.init("sandboxapp.cloopen.com", "8883");
		        	restAPI.setAccount("aaf98f89488d0aad0148ab8e790f0d1c", "fda1ca24f48c49daa959a0f5f095de53");
		        	restAPI.setAppId("8a216da857dc1f6a0157e601e04d0dad");// 鍒濆鍖栧簲鐢↖D
		        	HashMap<String, Object> result = restAPI.sendTemplateSMS(phone,"127669", new String[]{flag});
		        	if("000000".equals(result.get("statusCode"))){
		        		captcha.setCaptcha(flag);
		            	captcha.setUpdateDatetime(new Date());
		            	Integer rs = captchaMapper.updateByPrimaryKeySelective(captcha);
		    	        if(rs == 1){
		            		a= true;
		            	}else {
		            		a= false;
		            	}
		    		}else{
		    			a = false;
		    		}
					
				}
        	}
        
        	
        }		
            return a;
        
	
	}

	@Override
	public boolean check(String phone, String captcha) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("captcha", captcha);
		Captcha captcha1 =  captchaMapper.selectByCaptchaPhone(map);
		if(captcha1 == null){
			return false;
		}else{
			return true;
		}
	}

	

}
