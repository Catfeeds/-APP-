package com.hcb.xigou.service;

import com.hcb.xigou.dto.Captchas;

public interface ICaptchaService {
   public int deleteByPrimaryKey(Integer fakeId);

   public int insert(Captchas record);

   public int insertSelective(Captchas record);

   public Captchas selectByPrimaryKey(Integer fakeId);

   public int updateByPrimaryKeySelective(Captchas record);

   public int updateByPrimaryKey(Captchas record);
   
   public boolean sendTo(final String phone);

   public boolean check(final String phone, final String captcha);
}
