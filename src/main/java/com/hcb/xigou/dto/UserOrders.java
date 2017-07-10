package com.hcb.xigou.dto;

public class UserOrders extends Orders{
	
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }
    
}
