package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.ThirdCategorysMapper;
import com.hcb.xigou.dto.ThirdCategorys;
import com.hcb.xigou.service.IThirdCategorysService;

@Service("ThirdCategorysService")
public class ThirdCategorysServiceImpl implements IThirdCategorysService{

	@Autowired
	ThirdCategorysMapper thirdCategorysMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return thirdCategorysMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(ThirdCategorys record) {
		return thirdCategorysMapper.insert(record);
	}

	@Override
	public int insertSelective(ThirdCategorys record) {
		return thirdCategorysMapper.insertSelective(record);
	}

	@Override
	public ThirdCategorys selectByPrimaryKey(Integer fakeId) {
		return thirdCategorysMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(ThirdCategorys record) {
		return thirdCategorysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ThirdCategorys record) {
		return thirdCategorysMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Map<String, Object>> selectByPaging(Map<String, Object> map) {
		return thirdCategorysMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return thirdCategorysMapper.totalCount(map);
	}

	@Override
	public ThirdCategorys selectByThirdUuid(String thirdUuid) {
		return thirdCategorysMapper.selectByThirdUuid(thirdUuid);
	}
}
