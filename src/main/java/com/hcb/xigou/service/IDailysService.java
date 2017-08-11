package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Dailys;

public interface IDailysService {
	public int deleteByPrimaryKey(Integer fakeId);

	public int insert(Dailys record);

	public int insertSelective(Dailys record);

	public Dailys selectByPrimaryKey(Integer fakeId);

	public int updateByPrimaryKeySelective(Dailys record);

	public int updateByPrimaryKey(Dailys record);
	
	public List<Dailys> selectByPaging(Map<String, Object> map);
	
	public Integer totalCount(Map<String, Object> map);
	
	public Dailys selectByDailyUuid(String dailyUuid);
}
