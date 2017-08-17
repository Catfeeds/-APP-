package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Managers;

public interface ManagersMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Managers record);

    int insertSelective(Managers record);

    Managers selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Managers record);

    int updateByPrimaryKeyWithBLOBs(Managers record);

    int updateByPrimaryKey(Managers record);
    
    Managers selectBynicknameAndGrade(Map<String,Object> map);
    
    Managers selectByNickname(String nickname);
    
    Managers selectByManagerUuid(String managerUuid);
    
    List<Managers> selectByPaging(Map<String, Object> map);
    
    List<Managers> selectByManagersExcelport(Map<String, Object> map);
    
    Integer totalCount(Map<String, Object> map);
    
    List<Map<String, Object>> selectByAll();
}