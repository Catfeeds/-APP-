package com.hcb.xigou.dao.interfaceClass;

import java.util.Map;

import com.hcb.xigou.dto.Captcha;

public interface CaptchaMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Captcha record);

    int insertSelective(Captcha record);

    Captcha selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Captcha record);

    int updateByPrimaryKey(Captcha record);
    
    Captcha selectByphone(String phone);
    
    Captcha selectByCaptchaPhone(Map<String, Object> map);
}