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
		// TODO Auto-generated method stub
		return firstCategorysMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.insert(record);
	}

	@Override
	public int insertSelective(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.insertSelective(record);
	}

	@Override
	public FirstCategorys selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.updateByPrimaryKey(record);
	}

	@Override
	public FirstCategorys selectByFirstUuid(String firstUuid) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.selectByFirstUuid(firstUuid);
	}

	@Override
	public List<FirstCategorys> searchCategoryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.searchCategoryByMap(map);
	}

	@Override
	public int countCategoryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.countCategoryByMap(map);
	}

	@Override
	public int updateByfirstUuid(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.updateByfirstUuid(record);
	}

	@Override
	public int deleteByFirstUuids(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.deleteByFirstUuids(map);
	}

	@Override
	public List<FirstCategorys> firstUuid() {
		// TODO Auto-generated method stub
		return firstCategorysMapper.firstUuid();
	}

}
