package com.hcb.xigou.dao.interfaceClass;


import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Goods;

public interface GoodsMapper {

	public void deleteByGoodsId(int[] fakeId);

	public List<Goods> selectGoodDetails(Map<String, Object> map);

	public Goods selectGoodById(int fakeId);

	public int selectCountByGood(Map<String, Object> map);
	
}