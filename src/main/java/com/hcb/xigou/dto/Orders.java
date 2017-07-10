package com.hcb.xigou.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private Integer fakeId;

    private String orderUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String userUuid;

    private String goodUuid;

    private String couponUuids;

    private BigDecimal totalMoney;

    private BigDecimal couponMoney;

    private Integer fraction;

    private BigDecimal paidMoney;

    private Integer numbers;

    private String payStatus;

    private String payWay;

    private String orderNumber;

    private String alipayNumber;

    private String wechatNumber;

    private Date payTime;

    private Date failTime;

    private Date collectTime;

    private Date commentTime;

    private Date returnTime;

    private String addressUuid;

    private String deliveryTime;

    private String giveStatus;

    private Date signTime;

    private String courierUuid;

    private String scanerUuid;

    private String remarks;

    private String storeUuid;

    private String dailySign;

    private String voucher;

    private String content;

    private String code;

    private String type;

    private String barCode;

    private String isSign;

    private String carUuids;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid == null ? null : orderUuid.trim();
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

    public String getGoodUuid() {
        return goodUuid;
    }

    public void setGoodUuid(String goodUuid) {
        this.goodUuid = goodUuid == null ? null : goodUuid.trim();
    }

    public String getCouponUuids() {
        return couponUuids;
    }

    public void setCouponUuids(String couponUuids) {
        this.couponUuids = couponUuids == null ? null : couponUuids.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Integer getFraction() {
        return fraction;
    }

    public void setFraction(Integer fraction) {
        this.fraction = fraction;
    }

    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getAlipayNumber() {
        return alipayNumber;
    }

    public void setAlipayNumber(String alipayNumber) {
        this.alipayNumber = alipayNumber == null ? null : alipayNumber.trim();
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber == null ? null : wechatNumber.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getFailTime() {
        return failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getAddressUuid() {
        return addressUuid;
    }

    public void setAddressUuid(String addressUuid) {
        this.addressUuid = addressUuid == null ? null : addressUuid.trim();
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
    }

    public String getGiveStatus() {
        return giveStatus;
    }

    public void setGiveStatus(String giveStatus) {
        this.giveStatus = giveStatus == null ? null : giveStatus.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getCourierUuid() {
        return courierUuid;
    }

    public void setCourierUuid(String courierUuid) {
        this.courierUuid = courierUuid == null ? null : courierUuid.trim();
    }

    public String getScanerUuid() {
        return scanerUuid;
    }

    public void setScanerUuid(String scanerUuid) {
        this.scanerUuid = scanerUuid == null ? null : scanerUuid.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
    }

    public String getDailySign() {
        return dailySign;
    }

    public void setDailySign(String dailySign) {
        this.dailySign = dailySign == null ? null : dailySign.trim();
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher == null ? null : voucher.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign == null ? null : isSign.trim();
    }

    public String getCarUuids() {
        return carUuids;
    }

    public void setCarUuids(String carUuids) {
        this.carUuids = carUuids == null ? null : carUuids.trim();
    }
}