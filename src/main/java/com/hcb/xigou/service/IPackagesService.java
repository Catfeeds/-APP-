package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Packages;

public interface IPackagesService {
	public int deleteByPrimaryKey(Integer fakeId);

	public int insert(Packages record);

	public int insertSelective(Packages record);

	public Packages selectByPrimaryKey(Integer fakeId);

	public int updateByPrimaryKeySelective(Packages record);

	public int updateByPrimaryKey(Packages record);
	
	public Packages selectByPackageUuid(String PackageUuid);
	
	public List<Map<String, Object>> selectByPaging(Map<String, Object> map);
	
	public Integer totalCount(Map<String, Object> map);
	
	public List<Map<String, Object>> selectByAll();
}
