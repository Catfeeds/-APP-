package com.hcb.xigou.pojo;

import java.math.BigDecimal;

public class GoodsExcelport {
	private String goodUuid;
	
    private String goodName;
    
    private String models;
    
    private String firtCategoryName;
    
    private BigDecimal unitPrice;
    
    private String goodStatus;

	public String getGoodUuid() {
		return goodUuid;
	}

	public void setGoodUuid(String goodUuid) {
		this.goodUuid = goodUuid;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getFirtCategoryName() {
		return firtCategoryName;
	}

	public void setFirtCategoryName(String firtCategoryName) {
		this.firtCategoryName = firtCategoryName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getGoodStatus() {
		return goodStatus;
	}

	public void setGoodStatus(String goodStatus) {
		this.goodStatus = goodStatus;
	}
    
    
}
