package com.qiguliuxing.dts.db.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiguliuxing.dts.db.dao.ex.StatMapper;

@Service
@SuppressWarnings("rawtypes")
public class StatService {
	@Resource
	private StatMapper statMapper;

	public List<Map> statUser() {
		return statMapper.statUser();
	}

	public List<Map> statOrder() {
		return statMapper.statOrder();
	}

	public List<Map> statGoods() {
		return statMapper.statGoods();
	}

	/**
	 * 按商品统计销售报表
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsId 商品ID（可选）
	 * @param userId 用户ID（可选）
	 * @return 商品销售统计数据
	 */
	public List<Map> statSaleByGoods(String startDate, String endDate, Integer goodsId, Integer userId) {
		return statMapper.statSaleByGoods(startDate, endDate, goodsId, userId);
	}

	/**
	 * 按用户统计销售报表
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsId 商品ID（可选）
	 * @param userId 用户ID（可选）
	 * @return 用户销售统计数据
	 */
	public List<Map> statSaleByUser(String startDate, String endDate, Integer goodsId, Integer userId) {
		return statMapper.statSaleByUser(startDate, endDate, goodsId, userId);
	}

	/**
	 * 按月份统计销售报表
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param goodsId 商品ID（可选）
	 * @param userId 用户ID（可选）
	 * @return 月份销售统计数据
	 */
	public List<Map> statSaleByMonth(String startDate, String endDate, Integer goodsId, Integer userId) {
		return statMapper.statSaleByMonth(startDate, endDate, goodsId, userId);
	}
}
