package com.hcb.xigou.dto;

import com.hcb.xigou.pojo.ActivityZones;

public class UserActivity extends ActivityZones{
	
	private Integer goodStatus;
	
	private String goodName;
	
	 public Integer getGoodStatus() {
		return goodStatus;
	 }

	public void setGoodStatus(Integer goodStatus) {
		this.goodStatus = goodStatus;
	}
	
	public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }
}
