package com.hcb.xigou.dto;

public class UserOrders extends Orders{
	
    private String nickname;
    
    private String storeName;
    
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
    
    
   
}
