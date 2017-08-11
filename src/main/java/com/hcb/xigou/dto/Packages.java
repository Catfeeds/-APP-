package com.hcb.xigou.dto;

import java.util.Date;

public class Packages {
    private Integer fakeId;

    private String packageUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String couponUuid;

    private String packageName;

    private String packageStatus;

    private Integer packeageStock;

    private Integer couponNumbers;

    private String ruleOne;

    private String ruleTwo;

    private String title;

    private String url;

    private String banner;

    private Date startDatetime;

    private Date endDatetime;

    private String isOpen;

    private Date openDatetime;

    private Date closeDatetime;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getPackageUuid() {
        return packageUuid;
    }

    public void setPackageUuid(String packageUuid) {
        this.packageUuid = packageUuid == null ? null : packageUuid.trim();
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

    public String getCouponUuid() {
        return couponUuid;
    }

    public void setCouponUuid(String couponUuid) {
        this.couponUuid = couponUuid == null ? null : couponUuid.trim();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus == null ? null : packageStatus.trim();
    }

    public Integer getPackeageStock() {
        return packeageStock;
    }

    public void setPackeageStock(Integer packeageStock) {
        this.packeageStock = packeageStock;
    }

    public Integer getCouponNumbers() {
        return couponNumbers;
    }

    public void setCouponNumbers(Integer couponNumbers) {
        this.couponNumbers = couponNumbers;
    }

    public String getRuleOne() {
        return ruleOne;
    }

    public void setRuleOne(String ruleOne) {
        this.ruleOne = ruleOne == null ? null : ruleOne.trim();
    }

    public String getRuleTwo() {
        return ruleTwo;
    }

    public void setRuleTwo(String ruleTwo) {
        this.ruleTwo = ruleTwo == null ? null : ruleTwo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }

    public Date getOpenDatetime() {
        return openDatetime;
    }

    public void setOpenDatetime(Date openDatetime) {
        this.openDatetime = openDatetime;
    }

    public Date getCloseDatetime() {
        return closeDatetime;
    }

    public void setCloseDatetime(Date closeDatetime) {
        this.closeDatetime = closeDatetime;
    }
}