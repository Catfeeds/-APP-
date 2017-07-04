package com.hcb.xigou.service;

import com.hcb.xigou.dto.Captcha;

public interface ICaptchaService {
   public int deleteByPrimaryKey(Integer fakeId);

   public int insert(Captcha record);

   public int insertSelective(Captcha record);

   public Captcha selectByPrimaryKey(Integer fakeId);

   public int updateByPrimaryKeySelective(Captcha record);

   public int updateByPrimaryKey(Captcha record);
   
   public boolean sendTo(final String phone);

   public boolean check(final String phone, final String captcha);
}
