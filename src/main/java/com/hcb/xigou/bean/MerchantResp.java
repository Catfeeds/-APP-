package com.hcb.xigou.bean;

import com.hcb.xigou.bean.base.BaseResp;

public class MerchantResp extends BaseResp{

	private String merchant_uuid;
	
	private String password;
	
	private Boolean has_profile;

	public String getMerchant_uuid() {
		return merchant_uuid;
	}

	public void setMerchant_uuid(String merchant_uuid) {
		this.merchant_uuid = merchant_uuid == null ? null : merchant_uuid.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Boolean getHas_profile() {
		return has_profile;
	}

	public void setHas_profile(Boolean has_profile) {
		this.has_profile = has_profile;
	}
}
