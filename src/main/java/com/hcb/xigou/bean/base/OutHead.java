package com.hcb.xigou.bean.base;

import java.math.BigDecimal;

public class OutHead {
	
    private String returnCode;
    
    private String returnDescription;
    
    private String sysTime;
    	
	public String getReturnCode() {
		return returnCode;
	}



	public OutHead setReturnCode(String returnCode) {
		this.returnCode = returnCode;
		return this;
	}



	public String getReturnDescription() {
		return returnDescription;
	}



	public OutHead setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
		return this;
	}



	public String getSysTime() {
		return sysTime;
	}



	public OutHead setSysTime(String sysTime) {
		this.sysTime = sysTime;
		return this;
	}


	@Override
	public String toString() {
		return "OutHead [returnCode=" + returnCode + ", returnDescription=" + returnDescription + ", sysTime=" + sysTime+ "]";
	}
}
