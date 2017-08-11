package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Managers;

public interface IManagersService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(Managers record);

    int insertSelective(Managers record);

    Managers selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Managers record);

    int updateByPrimaryKey(Managers record);
    
    Managers selectBynicknameAndGrade(Map<String,Object> map);
    
    public Managers selectByManagerUuid(String managerUuid);
    
    public List<Managers> selectByPaging(Map<String, Object> map);
    
    public Integer totalCount(Map<String, Object> map);
    
    public List<Map<String, Object>> selectByAll();
}
