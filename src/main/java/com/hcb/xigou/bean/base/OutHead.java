package com.hcb.xigou.bean.base;

import java.math.BigDecimal;

public class OutHead {
	
    private String returnCode;
    
    private String returnDescription;
    
    private String sysTime;
    
    private String nick_name;
    
    private String gender;
    
    private String avatar;
    
    private String franchisee;
    
    private String sign;
    
    private String privileges;
    
    private String user_uuid;
    
    private BigDecimal balance;
    
    private Integer userId;
    
    private String agreement;
    
    private String prove;
    
    private String name;
    
    private String phone;
    
    private String telphone;
    
    private Integer coins;
	
	public String getReturnCode() {
		return returnCode;
	}



	public OutHead setReturnCode(String returnCode) {
		this.returnCode = returnCode;
		return this;
	}



	public String getReturnDescription() {
		return returnDescription;
	}



	public OutHead setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
		return this;
	}



	public String getSysTime() {
		return sysTime;
	}



	public OutHead setSysTime(String sysTime) {
		this.sysTime = sysTime;
		return this;
	}


	@Override
	public String toString() {
		return "OutHead [returnCode=" + returnCode + ", returnDescription=" + returnDescription + ", sysTime=" + sysTime
				+ ", nick_name=" + nick_name + ", gender=" + gender + ", avatar=" + avatar + ", franchisee="
				+ franchisee + ", sign=" + sign + ", privileges=" + privileges + ", user_uuid=" + user_uuid + "]";
	}



	public String getNick_name() {
		return nick_name;
	}



	public OutHead setNick_name(String nick_name) {
		this.nick_name = nick_name;
		return this;
	}



	public String getGender() {
		return gender;
	}



	public OutHead setGender(String gender) {
		this.gender = gender;
		return this;
	}



	public String getAvatar() {
		return avatar;
	}



	public OutHead setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}



	public String getFranchisee() {
		return franchisee;
	}



	public OutHead setFranchisee(String franchisee) {
		this.franchisee = franchisee;
		return this;
	}



	public String getSign() {
		return sign;
	}



	public OutHead setSign(String sign) {
		this.sign = sign;
		return this;
	}



	public String getPrivileges() {
		return privileges;
	}



	public OutHead setPrivileges(String privileges) {
		this.privileges = privileges;
		return this;
	}



	public String getUser_uuid() {
		return user_uuid;
	}

	public OutHead setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
		return this;
	}



	public BigDecimal getBalance() {
		return balance;
	}



	public OutHead setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}



	public Integer getUserId() {
		return userId;
	}



	public OutHead setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}



	public String getAgreement() {
		return agreement;
	}



	public OutHead setAgreement(String agreement) {
		this.agreement = agreement;
		return this;
	}



	public String getProve() {
		return prove;
	}



	public OutHead setProve(String prove) {
		this.prove = prove;
		return this;
	}



	public String getName() {
		return name;
	}



	public OutHead setName(String name) {
		this.name = name;
		return this;
	}



	public String getPhone() {
		return phone;
	}



	public OutHead setPhone(String phone) {
		this.phone = phone;
		return this;
	}



	public String getTelphone() {
		return telphone;
	}



	public OutHead setTelphone(String telphone) {
		this.telphone = telphone;
		return this;
	}



	public Integer getCoins() {
		return coins;
	}



	public OutHead setCoins(Integer coins) {
		this.coins = coins;
		return this;
	}
}
