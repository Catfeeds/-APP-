package com.hcb.xigou.dto;

public class UserOrders extends Orders{
	
    private String nickname;
    
    private String storeName;
    
    private String member_card_number;
    
    public String getNickname() {
        return nickname;
    }

    public String getMember_card_number() {
		return member_card_number;
	}

	public void setMember_card_number(String member_card_number) {
		this.member_card_number = member_card_number == null ? null : member_card_number.trim();
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
