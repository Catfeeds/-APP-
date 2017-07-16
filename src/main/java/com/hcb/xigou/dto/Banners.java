package com.hcb.xigou.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Banners {
    private Integer fakeId;

    private String bannerUuid;

    private String createDatetime;

    private String updateDatetime;

    private String deleteAt;

    private String type;

    private String url;

    private String storeUuid;

    private String goodUuid;

    private Integer currentindex;
    
    private Integer bannerStatus;
    
    private String bannerName;

    public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public Integer getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(Integer bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getBannerUuid() {
        return bannerUuid;
    }

    public void setBannerUuid(String bannerUuid) {
        this.bannerUuid = bannerUuid == null ? null : bannerUuid.trim();
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(createDatetime);
        this.createDatetime = time;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(updateDatetime);
        this.updateDatetime = time;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt == null ? null : deleteAt.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
    }

    public String getGoodUuid() {
        return goodUuid;
    }

    public void setGoodUuid(String goodUuid) {
        this.goodUuid = goodUuid == null ? null : goodUuid.trim();
    }

    public Integer getCurrentindex() {
        return currentindex;
    }

    public void setCurrentindex(Integer currentindex) {
        this.currentindex = currentindex;
    }
}