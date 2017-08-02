package com.hcb.xigou.dto;

import java.util.Date;

public class Stores {
    private Integer fakeId;

    private String storeUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String image;

    private String storeName;

    private String storeAddress;

    private String latitude;

    private String longitude;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }
}