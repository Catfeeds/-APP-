package com.hcb.xigou.dao.interfaceClass;

import java.util.Map;

import com.hcb.xigou.dto.Captchas;

public interface CaptchasMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Captchas record);

    int insertSelective(Captchas record);

    Captchas selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Captchas record);

    int updateByPrimaryKey(Captchas record);
    
    Captchas selectByphone(String phone);
    
    Captchas selectByCaptchaPhone(Map<String, Object> map);
}