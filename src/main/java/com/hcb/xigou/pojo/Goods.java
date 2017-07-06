package com.hcb.xigou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer fakeId;

    private String goodUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String merchantUuid;

    private String goodName;

    private String description;

    private String title;

    private String cover;

    private BigDecimal unitPrice;

    private Integer numbers;

    private String classifier;

    private String firstUuid;

    private String firtCategoryName;

    private String secondUuid;

    private String secondCategoryName;

    private String storeUuid;

    private String address;

    private String goodCode;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getGoodUuid() {
        return goodUuid;
    }

    public void setGoodUuid(String goodUuid) {
        this.goodUuid = goodUuid == null ? null : goodUuid.trim();
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

    public String getMerchantUuid() {
        return merchantUuid;
    }

    public void setMerchantUuid(String merchantUuid) {
        this.merchantUuid = merchantUuid == null ? null : merchantUuid.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier == null ? null : classifier.trim();
    }

    public String getFirstUuid() {
        return firstUuid;
    }

    public void setFirstUuid(String firstUuid) {
        this.firstUuid = firstUuid == null ? null : firstUuid.trim();
    }

    public String getFirtCategoryName() {
        return firtCategoryName;
    }

    public void setFirtCategoryName(String firtCategoryName) {
        this.firtCategoryName = firtCategoryName == null ? null : firtCategoryName.trim();
    }

    public String getSecondUuid() {
        return secondUuid;
    }

    public void setSecondUuid(String secondUuid) {
        this.secondUuid = secondUuid == null ? null : secondUuid.trim();
    }

    public String getSecondCategoryName() {
        return secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName == null ? null : secondCategoryName.trim();
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode == null ? null : goodCode.trim();
    }
}