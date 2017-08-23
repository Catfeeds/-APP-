package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.ThirdCategorys;

public interface IThirdCategorysService {
	public int deleteByPrimaryKey(Integer fakeId);

	public int insert(ThirdCategorys record);

	public int insertSelective(ThirdCategorys record);

	public ThirdCategorys selectByPrimaryKey(Integer fakeId);

	public int updateByPrimaryKeySelective(ThirdCategorys record);

	public int updateByPrimaryKey(ThirdCategorys record);
	
	public List<Map<String, Object>> selectByPaging(Map<String, Object> map);
	
	public Integer totalCount(Map<String, Object> map);
	
	public ThirdCategorys selectByThirdUuid(String thirdUuid);
}
