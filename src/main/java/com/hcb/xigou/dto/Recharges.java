package com.hcb.xigou.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recharges {
    private Integer fakeId;

    private String rechargeUuid;

    private String createDatetime;

    private String updateDatetime;

    private String deleteAt;

    private String userUuid;

    private String rechargeNumber;

    private String wechatNumber;

    private String alipayNumber;

    private String payTime;

    private BigDecimal amount;

    private String status;

    private String thirdWay;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getRechargeUuid() {
        return rechargeUuid;
    }

    public void setRechargeUuid(String rechargeUuid) {
        this.rechargeUuid = rechargeUuid == null ? null : rechargeUuid.trim();
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

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getRechargeNumber() {
        return rechargeNumber;
    }

    public void setRechargeNumber(String rechargeNumber) {
        this.rechargeNumber = rechargeNumber == null ? null : rechargeNumber.trim();
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber == null ? null : wechatNumber.trim();
    }

    public String getAlipayNumber() {
        return alipayNumber;
    }

    public void setAlipayNumber(String alipayNumber) {
        this.alipayNumber = alipayNumber == null ? null : alipayNumber.trim();
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(payTime);
        this.payTime = time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getThirdWay() {
        return thirdWay;
    }

    public void setThirdWay(String thirdWay) {
        this.thirdWay = thirdWay == null ? null : thirdWay.trim();
    }
}