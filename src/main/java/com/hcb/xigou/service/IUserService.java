package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Users;

public interface IUserService {

	public List<Users> selectByPaging(Map<String,Object> map);

	public Integer totalCount(Map<String,Object> map);
}
