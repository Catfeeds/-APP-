package com.hcb.xigou.dto;

import java.util.Date;

public class Skus {
    private Integer fakeId;

    private String skuUuid;

    private Date createDatetime;

    private Date updateDatetime;

    private String deleteAt;

    private String category;

    private String parameter;

    public Integer getFakeId() {
        return fakeId;
    }

    public void setFakeId(Integer fakeId) {
        this.fakeId = fakeId;
    }

    public String getSkuUuid() {
        return skuUuid;
    }

    public void setSkuUuid(String skuUuid) {
        this.skuUuid = skuUuid == null ? null : skuUuid.trim();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }
}