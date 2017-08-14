package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Coupons;

public interface CouponsMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Coupons record);

    int insertSelective(Coupons record);

    Coupons selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Coupons record);

    int updateByPrimaryKey(Coupons record);
    
    int deleteByCouponUuids(Map<String,Object> map);
    
    Coupons selectByCouponUuid(String couponUuid);
    
    List<Coupons> searchCouponByMap(Map<String,Object> map);
    
    List<Coupons> searchCouponExcelportByMap(Map<String,Object> map);
    
    int countCouponByMap(Map<String,Object> map);
    
    int updateByCouponUuid(Coupons record);
    
    List<Coupons> selectByPackageAll(Map<String, Object> map);
}