package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.ManagersMapper;
import com.hcb.xigou.dto.Managers;
import com.hcb.xigou.service.IManagersService;

@Service("ManagersService")
public class ManagersServiceImpl implements IManagersService{
     
	@Autowired
	ManagersMapper managersMapper;

	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return managersMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Managers record) {
		return managersMapper.insert(record);
	}

	@Override
	public int insertSelective(Managers record) {
		return managersMapper.insertSelective(record);
	}

	@Override
	public Managers selectByPrimaryKey(Integer fakeId) {
		return managersMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Managers record) {
		return managersMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Managers record) {
		return managersMapper.updateByPrimaryKey(record);
	}

	@Override
	public Managers selectBynicknameAndGrade(Map<String, Object> map) {
		return managersMapper.selectBynicknameAndGrade(map);
	}

	@Override
	public Managers selectByManagerUuid(String managerUuid) {
		return managersMapper.selectByManagerUuid(managerUuid);
	}

	@Override
	public List<Managers> selectByPaging(Map<String, Object> map) {
		return managersMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return managersMapper.totalCount(map);
	}

	@Override
	public List<Map<String, Object>> selectByAll() {
		return managersMapper.selectByAll();
	}
}
