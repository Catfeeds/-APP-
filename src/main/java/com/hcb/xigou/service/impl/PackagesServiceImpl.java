package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.PackagesMapper;
import com.hcb.xigou.dto.Packages;
import com.hcb.xigou.service.IPackagesService;

@Service("PackagesService")
public class PackagesServiceImpl implements IPackagesService{
	
	@Autowired
	PackagesMapper packagesMapper;

	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return packagesMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Packages record) {
		return packagesMapper.insert(record);
	}

	@Override
	public int insertSelective(Packages record) {
		return packagesMapper.insertSelective(record);
	}

	@Override
	public Packages selectByPrimaryKey(Integer fakeId) {
		return packagesMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Packages record) {
		return packagesMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Packages record) {
		return packagesMapper.updateByPrimaryKey(record);
	}

	@Override
	public Packages selectByPackageUuid(String PackageUuid) {
		return packagesMapper.selectByPackageUuid(PackageUuid);
	}

	@Override
	public List<Map<String, Object>> selectByPaging(Map<String, Object> map) {
		return packagesMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return packagesMapper.totalCount(map);
	}

	@Override
	public List<Map<String, Object>> selectByAll() {
		return packagesMapper.selectByAll();
	}

	@Override
	public List<Map<String, Object>> selectByPackagesExcelport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return packagesMapper.selectByPackagesExcelport(map);
	}
}
