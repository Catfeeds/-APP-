package com.hcb.xigou.service;

import com.hcb.xigou.dto.ShoppingCarts;

public interface IShoppingCartsService {
	public int deleteByPrimaryKey(Integer fakeId);

	public int insert(ShoppingCarts record);

	public int insertSelective(ShoppingCarts record);

	public ShoppingCarts selectByPrimaryKey(Integer fakeId);

	public int updateByPrimaryKeySelective(ShoppingCarts record);

	public int updateByPrimaryKey(ShoppingCarts record);
	
	public ShoppingCarts selectByCarUuid(String carUuid);
}
