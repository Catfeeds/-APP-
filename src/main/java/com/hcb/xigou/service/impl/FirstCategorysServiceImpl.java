package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.FirstCategorysMapper;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.service.IFirstCategorysService;

@Service("FirstCategorysService")
public class FirstCategorysServiceImpl implements IFirstCategorysService{

	@Autowired
	FirstCategorysMapper firstCategorysMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return firstCategorysMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(FirstCategorys record) {
		return firstCategorysMapper.insert(record);
	}

	@Override
	public int insertSelective(FirstCategorys record) {
		return firstCategorysMapper.insertSelective(record);
	}

	@Override
	public FirstCategorys selectByPrimaryKey(Integer fakeId) {
		return firstCategorysMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(FirstCategorys record) {
		return firstCategorysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FirstCategorys record) {
		return firstCategorysMapper.updateByPrimaryKey(record);
	}

	@Override
	public FirstCategorys selectByFirstUuid(String firstUuid) {
		return firstCategorysMapper.selectByFirstUuid(firstUuid);
	}

	@Override
	public List<FirstCategorys> firstUuid() {
		return firstCategorysMapper.firstUuid();
	}
	
	public List<FirstCategorys> searchCategoryByMap(Map<String, Object> map) {
		return firstCategorysMapper.searchCategoryByMap(map);
	}

	@Override
	public int countCategoryByMap(Map<String, Object> map) {
		return firstCategorysMapper.countCategoryByMap(map);
	}

	@Override
	public int updateByfirstUuid(FirstCategorys record) {
		return firstCategorysMapper.updateByfirstUuid(record);
	}

	@Override
	public int deleteByFirstUuids(Map<String, Object> map) {
		return firstCategorysMapper.deleteByFirstUuids(map);
	}

}
