package com.hcb.xigou.pojo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {
    private Integer fakeId;

    private String userUuid;

    private String createDatetime;

    private String updateDatetime;

    private String deleteAt;

    private String password;

    private Integer userid;

    private String phone;

    private String nickname;

    private String headimgurl;

    private Byte sex;

    private String birthday;

    private String country;

    private String province;

    private String city;

    private String unionid;

    private String openid;

    private BigDecimal balance;

    private Integer coupons;

    private Integer coins;

    private Integer fraction;

    private String grade;

    private String codes;

    private String clientId;

    private String deviceToken;

    private String name;

    private String deviceName;

    private String deviceOsver;

    private String appVersion;

    private String storeUuid;

    private String memberCardNumber;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(birthday);
        this.birthday = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getCoupons() {
        return coupons;
    }

    public void setCoupons(Integer coupons) {
        this.coupons = coupons;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getFraction() {
        return fraction;
    }

    public void setFraction(Integer fraction) {
        this.fraction = fraction;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes == null ? null : codes.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceOsver() {
        return deviceOsver;
    }

    public void setDeviceOsver(String deviceOsver) {
        this.deviceOsver = deviceOsver == null ? null : deviceOsver.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
    }

    public String getMemberCardNumber() {
        return memberCardNumber;
    }

    public void setMemberCardNumber(String memberCardNumber) {
        this.memberCardNumber = memberCardNumber == null ? null : memberCardNumber.trim();
    }
}