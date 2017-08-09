package com.hcb.xigou.dao.interfaceClass;

import com.hcb.xigou.dto.ShoppingCarts;

public interface ShoppingCartsMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(ShoppingCarts record);

    int insertSelective(ShoppingCarts record);

    ShoppingCarts selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(ShoppingCarts record);

    int updateByPrimaryKey(ShoppingCarts record);
    
    ShoppingCarts selectByCarUuid(String carUuid);
}