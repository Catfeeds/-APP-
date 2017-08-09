package com.hcb.xigou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.ShoppingCartsMapper;
import com.hcb.xigou.dto.ShoppingCarts;
import com.hcb.xigou.service.IShoppingCartsService;

@Service("ShoppingCartsService")
public class ShoppingCartsServiceImpl implements IShoppingCartsService{

	@Autowired
	ShoppingCartsMapper ShoppingCartsMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return ShoppingCartsMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(ShoppingCarts record) {
		return ShoppingCartsMapper.insert(record);
	}

	@Override
	public int insertSelective(ShoppingCarts record) {
		return ShoppingCartsMapper.insertSelective(record);
	}

	@Override
	public ShoppingCarts selectByPrimaryKey(Integer fakeId) {
		return ShoppingCartsMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(ShoppingCarts record) {
		return ShoppingCartsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ShoppingCarts record) {
		return ShoppingCartsMapper.updateByPrimaryKey(record);
	}

	@Override
	public ShoppingCarts selectByCarUuid(String carUuid) {
		return ShoppingCartsMapper.selectByCarUuid(carUuid);
	}
}
