package com.hcb.xigou.dto;

public class UserRechargers extends Recharges{
	 private String name;
	 
	 private String nickname;
	 
	 private String phone;
	 
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
	 public String getName() {
	     return name;
	 }

	 public void setName(String name) {
	     this.name = name == null ? null : name.trim();
	 }
}
