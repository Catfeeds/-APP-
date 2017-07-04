package com.hcb.xigou.pojo;

public class LeavingMessages implements Comparable<LeavingMessages>{

	private String user_uuid;
	
	private String avatar;
	
	private String nickname;
	
	private String address;
	
	private String content;
	
	private Integer userId;
	
	private String my_uuid;
	
	private String headimgurl;
	
	private String myNickname;
	
	private String myAddress;
	
	private String myContent;
	
	private Integer MyId;
	
	private Integer index;
	
	private String remark;

	public String getUser_uuid() {
		return user_uuid;
	}

	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid == null ? null : user_uuid.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMy_uuid() {
		return my_uuid;
	}

	public void setMy_uuid(String my_uuid) {
		this.my_uuid = my_uuid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl == null ? null : headimgurl.trim();
	}

	public String getMyNickname() {
		return myNickname;
	}

	public void setMyNickname(String myNickname) {
		this.myNickname = myNickname == null ? null : myNickname.trim();
	}

	public String getMyAddress() {
		return myAddress;
	}

	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress == null ? null : myAddress.trim();
	}

	public String getMyContent() {
		return myContent;
	}

	public void setMyContent(String myContent) {
		this.myContent = myContent == null ? null : myContent.trim();
	}

	public Integer getMyId() {
		return MyId;
	}

	public void setMyId(Integer myId) {
		MyId = myId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	@Override
	public int compareTo(LeavingMessages o) {
		if (this.index < o.index) {
			return -1;
		} else if (this.index > o.index) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
