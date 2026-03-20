package com.qiguliuxing.dts.db.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@SuppressWarnings("rawtypes")
public interface StatMapper {
	List<Map> statUser();

	List<Map> statOrder();

	List<Map> statGoods();

	/**
	 * 按商品统计销售报表
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsId 商品ID（可选）
	 * @param userId 用户ID（可选）
	 * @return 商品销售统计数据
	 */
	List<Map> statSaleByGoods(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);

	/**
	 * 按用户统计销售报表
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsId 商品ID（可选）
	 * @param userId 用户ID（可选）
	 * @return 用户销售统计数据
	 */
	List<Map> statSaleByUser(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);

	/**
	 * 按月份统计销售报表
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsId 商品ID（可选）
	 * @param userId 用户ID（可选）
	 * @return 月份销售统计数据
	 */
	List<Map> statSaleByMonth(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);
}