package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.DailysMapper;
import com.hcb.xigou.dto.Dailys;
import com.hcb.xigou.service.IDailysService;

@Service("DailysService")
public class DailysServiceImpl implements IDailysService{

	@Autowired
	DailysMapper dailysMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return dailysMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Dailys record) {
		return dailysMapper.insert(record);
	}

	@Override
	public int insertSelective(Dailys record) {
		return dailysMapper.insertSelective(record);
	}

	@Override
	public Dailys selectByPrimaryKey(Integer fakeId) {
		return dailysMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Dailys record) {
		return dailysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Dailys record) {
		return dailysMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Dailys> selectByPaging(Map<String, Object> map) {
		return dailysMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return dailysMapper.totalCount(map);
	}

	@Override
	public Dailys selectByDailyUuid(String dailyUuid) {
		return dailysMapper.selectByDailyUuid(dailyUuid);
	}
}
