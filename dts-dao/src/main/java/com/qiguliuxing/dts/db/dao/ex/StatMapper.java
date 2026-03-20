package com.qiguliuxing.dts.db.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@SuppressWarnings("rawtypes")
public interface StatMapper {
	List<Map> statUser();

	List<Map> statOrder();

	List<Map> statGoods();

	List<Map> statSalesByGoods(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);

	List<Map> statSalesByUser(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);

	List<Map> statSalesByMonth(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);
}