package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.CouponsMapper;
import com.hcb.xigou.dto.Coupons;
import com.hcb.xigou.service.ICouponsService;

@Service("CouponsService")
public class CouponsServiceImpl implements ICouponsService{

	@Autowired
	CouponsMapper couponsMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return couponsMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Coupons record) {
		// TODO Auto-generated method stub
		return couponsMapper.insert(record);
	}

	@Override
	public int insertSelective(Coupons record) {
		// TODO Auto-generated method stub
		return couponsMapper.insertSelective(record);
	}

	@Override
	public Coupons selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return couponsMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Coupons record) {
		// TODO Auto-generated method stub
		return couponsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Coupons record) {
		// TODO Auto-generated method stub
		return couponsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByCouponUuids(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return couponsMapper.deleteByCouponUuids(map);
	}

	@Override
	public Coupons selectByCouponUuid(String couponUuid) {
		// TODO Auto-generated method stub
		return couponsMapper.selectByCouponUuid(couponUuid);
	}

	@Override
	public List<Coupons> searchCouponByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return couponsMapper.searchCouponByMap(map);
	}

	@Override
	public int countCouponByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return couponsMapper.countCouponByMap(map);
	}

	@Override
	public int updateByCouponUuid(Coupons record) {
		// TODO Auto-generated method stub
		return couponsMapper.updateByCouponUuid(record);
	}

}
