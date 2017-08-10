package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Packages;

public interface PackagesMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Packages record);

    int insertSelective(Packages record);

    Packages selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Packages record);

    int updateByPrimaryKey(Packages record);
    
    Packages selectByPackageUuid(String PackageUuid);
	
    List<Map<String, Object>> selectByPaging(Map<String, Object> map);
	
    Integer totalCount(Map<String, Object> map);
    
    List<Map<String, Object>> selectByAll();
}