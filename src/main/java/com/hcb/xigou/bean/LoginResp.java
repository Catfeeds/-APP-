package com.hcb.xigou.bean;

import com.hcb.xigou.bean.base.BaseResp;

public class LoginResp extends BaseResp {

	private String user_uuid;
	
    private String password;
    
    private Boolean has_profile;
    
    private String privileges;
    
	private String franchisee;
	
	private String sign;
	
	private Integer userId;
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid == null ? null : user_uuid.trim();
	}
	public Boolean getHas_profile() {
		return has_profile;
	}
	public void setHas_profile(Boolean has_profile) {
		this.has_profile = has_profile;
	}
	public String getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String privileges) {
		this.privileges = privileges == null ? null : privileges.trim();
	}
	public String getFranchisee() {
		return franchisee;
	}
	public void setFranchisee(String franchisee) {
		this.franchisee = franchisee == null ? null : franchisee.trim();
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
