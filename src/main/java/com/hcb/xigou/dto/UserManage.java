package com.hcb.xigou.dto;


import com.hcb.xigou.pojo.Users;

public class UserManage extends Users{
    
    private String address;
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}
