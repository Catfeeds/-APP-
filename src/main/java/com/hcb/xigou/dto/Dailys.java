package com.hcb.xigou.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Dailys {
    private Integer fakeId;

    private String dailyUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private BigDecimal totalMoney;

    private BigDecimal splitMoney;

    private Long numbers;

    private Long splitNumbers;

    private Integer theNumber;

    private BigDecimal highestAmount;

    private Date startTime;

    private Date endTime;

    private Date openTime;

    private String isOpen;

    private String type;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getDailyUuid() {
        return dailyUuid;
    }

    public void setDailyUuid(String dailyUuid) {
        this.dailyUuid = dailyUuid == null ? null : dailyUuid.trim();
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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getSplitMoney() {
        return splitMoney;
    }

    public void setSplitMoney(BigDecimal splitMoney) {
        this.splitMoney = splitMoney;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }

    public Long getSplitNumbers() {
        return splitNumbers;
    }

    public void setSplitNumbers(Long splitNumbers) {
        this.splitNumbers = splitNumbers;
    }

    public Integer getTheNumber() {
        return theNumber;
    }

    public void setTheNumber(Integer theNumber) {
        this.theNumber = theNumber;
    }

    public BigDecimal getHighestAmount() {
        return highestAmount;
    }

    public void setHighestAmount(BigDecimal highestAmount) {
        this.highestAmount = highestAmount;
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

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}