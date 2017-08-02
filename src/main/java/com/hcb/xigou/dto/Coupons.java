package com.hcb.xigou.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupons {
    private Integer fakeId;

    private String couponUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String userUuid;

    private String amount;

    private String goodUuid;

    private String grantTime;
    
    private String failTime;

    private String firstUuid;

    private String secondUuid;

    private String title;

    private String description;

    private String type;

    private String content;

    private String couponStatus;

    private Integer couponStock;

    private String isGrant;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getCouponUuid() {
        return couponUuid;
    }

    public void setCouponUuid(String couponUuid) {
        this.couponUuid = couponUuid == null ? null : couponUuid.trim();
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

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getGoodUuid() {
        return goodUuid;
    }

    public void setGoodUuid(String goodUuid) {
        this.goodUuid = goodUuid == null ? null : goodUuid.trim();
    }   

    public String getGrantTime() {
		return grantTime.substring(0,10);
	}

	public void setGrantTime(String grantTime) {
		this.grantTime = grantTime;
	}

	public String getFailTime() {
        return failTime.substring(0, 10);
    }

    public void setFailTime(String failTime) {
        this.failTime = failTime;
    }

    public String getFirstUuid() {
        return firstUuid;
    }

    public void setFirstUuid(String firstUuid) {
        this.firstUuid = firstUuid == null ? null : firstUuid.trim();
    }

    public String getSecondUuid() {
        return secondUuid;
    }

    public void setSecondUuid(String secondUuid) {
        this.secondUuid = secondUuid == null ? null : secondUuid.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus == null ? null : couponStatus.trim();
    }

    public Integer getCouponStock() {
        return couponStock;
    }

    public void setCouponStock(Integer couponStock) {
        this.couponStock = couponStock;
    }

    public String getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(String isGrant) {
        this.isGrant = isGrant == null ? null : isGrant.trim();
    }
}