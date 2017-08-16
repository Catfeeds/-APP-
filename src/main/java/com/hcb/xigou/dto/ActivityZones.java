package com.hcb.xigou.dto;

import java.util.Date;

public class ActivityZones {
    private Integer fakeId;

    private String activityUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String goodUuid;

    private String merchantUuid;

    private String image;

    private String title;

    private String description;

    private Date startTime;

    private Date endTime;

    private String type;

    private String sign;

    private String groups;

    private String banner;

    private String storeUuid;

    private String isStop;

    private Byte position;

    private String isOpen;

    private String url;
    
    private Date openTime;
    
    private String surplusTime;
    
    private String ruleOne;
    
    private String ruleTwo;
    
    private Integer currentIndex;
    
    public String getRuleOne() {
		return ruleOne;
	}

	public void setRuleOne(String ruleOne) {
		this.ruleOne = ruleOne;
	}

	public String getRuleTwo() {
		return ruleTwo;
	}

	public void setRuleTwo(String ruleTwo) {
		this.ruleTwo = ruleTwo;
	}

	public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getSurplusTime() {
		return surplusTime;
	}

	public void setSurplusTime(String str) {
		this.surplusTime = str;
	}

	public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getActivityUuid() {
        return activityUuid;
    }

    public void setActivityUuid(String activityUuid) {
        this.activityUuid = activityUuid == null ? null : activityUuid.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt == null ? null : deleteAt.trim();
    }

    public String getGoodUuid() {
        return goodUuid;
    }

    public void setGoodUuid(String goodUuid) {
        this.goodUuid = goodUuid == null ? null : goodUuid.trim();
    }

    public String getMerchantUuid() {
        return merchantUuid;
    }

    public void setMerchantUuid(String merchantUuid) {
        this.merchantUuid = merchantUuid == null ? null : merchantUuid.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups == null ? null : groups.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
    }

    public String getIsStop() {
        return isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop == null ? null : isStop.trim();
    }

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}
}