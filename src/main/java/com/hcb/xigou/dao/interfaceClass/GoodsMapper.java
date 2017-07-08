package com.hcb.xigou.dao.interfaceClass;


import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Goods;

public interface GoodsMapper {

	public int deleteByGoodsId(int[] fakeId);

	public List<Goods> selectGoodDetails(Map<String, Object> map);

	public Goods selectGoodById(int fakeId);

	public int selectCountByGood(Map<String, Object> map);
	
	public Goods selectGoodByGoodUuid(String goodUuid);
	
	int deleteByGoodUuids(Map<String,Object> map);
}