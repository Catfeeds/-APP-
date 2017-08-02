package com.hcb.xigou.dto;

import java.util.Date;

public class SecondCategorys {
    private Integer fakeId;

    private String secondUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String firstUuid;

    private String categoryName;

    private String image;

    private String storeUuid;

    private String banner;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getSecondUuid() {
        return secondUuid;
    }

    public void setSecondUuid(String secondUuid) {
        this.secondUuid = secondUuid == null ? null : secondUuid.trim();
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

    public String getFirstUuid() {
        return firstUuid;
    }

    public void setFirstUuid(String firstUuid) {
        this.firstUuid = firstUuid == null ? null : firstUuid.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getStoreUuid() {
        return storeUuid;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid == null ? null : storeUuid.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }
}