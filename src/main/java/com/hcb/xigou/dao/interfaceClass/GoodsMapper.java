package com.hcb.xigou.dao.interfaceClass;


import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.pojo.Goods;

public interface GoodsMapper {

	public int deleteByGoodsId(int[] fakeId);

	public List<Goods> selectGoodDetails(Map<String, Object> map);

	public Goods selectGoodById(int fakeId);

	public int selectCountByGood(Map<String, Object> map);
	
	public Goods selectGoodByGoodUuid(String goodUuid);
	
	int deleteByGoodUuids(Map<String,Object> map);

	public List<Goods> searchGoodsByMap(Map<String, Object> map);

	public int countGoodsByMap(Map<String, Object> map);
	
	int insertSelective(Goods record);
	
	Goods selectByGoodUuid(String goodUuid);
	
	int updateByGoodsUuid(Goods record);

	public Goods selectByActivityGood(String activityUuid);

	public List<UserActivity> searchGood(String activity_uuid);

	public int updateByPrimaryKeySelective(Goods good);

	public List<Goods> secondUuid(Map<String, Object> map);

	public List<Goods> firstUuid();

	public List<Goods> searchGoodUuid(Map<String, Object> map);
}