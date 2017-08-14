package com.hcb.xigou.pojo;

import java.util.Date;

public class ActivityExcelport {
	private String title;
	 
    private Date startTime;

    private Date endTime;
    
    private Date openTime;
    
    private String isOpen;
    
    private String countdown;
    
    private Byte position;
    
    private String isStop;
    
   

	public String getCountdown() {
		return countdown;
	}

	public void setCountdown(String countdown) {
		this.countdown = countdown;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		this.isOpen = isOpen;
	}

	public Byte getPosition() {
		return position;
	}

	public void setPosition(Byte position) {
		this.position = position;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
}
