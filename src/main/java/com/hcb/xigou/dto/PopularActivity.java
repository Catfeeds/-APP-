package com.hcb.xigou.dto;

import com.hcb.xigou.pojo.ActivityZones;

public class PopularActivity extends ActivityZones{
	
	private String goodName;
	
	private String bannerUuid;

	public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }
    
    public String getBannerUuid() {
        return bannerUuid;
    }

    public void setBannerUuid(String bannerUuid) {
        this.bannerUuid = bannerUuid == null ? null : bannerUuid.trim();
    }
}
