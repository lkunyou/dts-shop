package com.qiguliuxing.dts.admin.web;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qiguliuxing.dts.admin.annotation.RequiresPermissionsDesc;
import com.qiguliuxing.dts.admin.util.StatVo;
import com.qiguliuxing.dts.core.util.ResponseUtil;
import com.qiguliuxing.dts.db.service.StatService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/admin/stat")
@Validated
public class AdminStatController {
	private static final Logger logger = LoggerFactory.getLogger(AdminStatController.class);

	@Autowired
	private StatService statService;

	@RequiresPermissions("admin:stat:user")
	@RequiresPermissionsDesc(menu = { "统计管理", "用户统计" }, button = "查询")
	@GetMapping("/user")
	public Object statUser() {
		logger.info("【请求开始】统计管理->用户统计->查询");

		List<Map> rows = statService.statUser();
		String[] columns = new String[] { "day", "users" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		logger.info("【请求结束】统计管理->用户统计->查询,响应结果:{}", JSONObject.toJSONString(statVo));
		return ResponseUtil.ok(statVo);
	}

	@RequiresPermissions("admin:stat:order")
	@RequiresPermissionsDesc(menu = { "统计管理", "订单统计" }, button = "查询")
	@GetMapping("/order")
	public Object statOrder() {
		logger.info("【请求开始】统计管理->订单统计->查询");

		List<Map> rows = statService.statOrder();
		String[] columns = new String[] { "day", "orders", "customers", "amount", "pcr" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		logger.info("【请求结束】统计管理->订单统计->查询,响应结果:{}", JSONObject.toJSONString(statVo));
		return ResponseUtil.ok(statVo);
	}

	@RequiresPermissions("admin:stat:goods")
	@RequiresPermissionsDesc(menu = { "统计管理", "商品统计" }, button = "查询")
	@GetMapping("/goods")
	public Object statGoods() {
		logger.info("【请求开始】统计管理->商品统计->查询");

		List<Map> rows = statService.statGoods();
		String[] columns = new String[] { "day", "orders", "products", "amount" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		logger.info("【请求结束】统计管理->商品统计->查询,响应结果:{}", JSONObject.toJSONString(statVo));
		return ResponseUtil.ok(statVo);
	}

	/**
	 * 按商品统计销售报表
	 * @param startDate 开始日期(yyyy-MM-dd)
	 * @param endDate 结束日期(yyyy-MM-dd)
	 * @param goodsId 商品ID(可选)
	 * @param userId 用户ID(可选)
	 * @return 商品销售统计数据
	 */
	@RequiresPermissions("admin:stat:sale:goods")
	@RequiresPermissionsDesc(menu = { "统计管理", "销售报表" }, button = "按商品统计")
	@GetMapping("/sale/goods")
	public Object statSaleByGoods(
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Integer goodsId,
			@RequestParam(required = false) Integer userId) {
		logger.info("【请求开始】统计管理->销售报表->按商品统计->查询,参数: startDate={}, endDate={}, goodsId={}, userId={}",
				startDate, endDate, goodsId, userId);

		List<Map> rows = statService.statSaleByGoods(startDate, endDate, goodsId, userId);
		String[] columns = new String[] { "goods_id", "goods_name", "day", "sales_volume", "order_amount" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		logger.info("【请求结束】统计管理->销售报表->按商品统计->查询");
		return ResponseUtil.ok(statVo);
	}

	/**
	 * 按用户统计销售报表
	 * @param startDate 开始日期(yyyy-MM-dd)
	 * @param endDate 结束日期(yyyy-MM-dd)
	 * @param goodsId 商品ID(可选)
	 * @param userId 用户ID(可选)
	 * @return 用户销售统计数据
	 */
	@RequiresPermissions("admin:stat:sale:user")
	@RequiresPermissionsDesc(menu = { "统计管理", "销售报表" }, button = "按用户统计")
	@GetMapping("/sale/user")
	public Object statSaleByUser(
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Integer goodsId,
			@RequestParam(required = false) Integer userId) {
		logger.info("【请求开始】统计管理->销售报表->按用户统计->查询,参数: startDate={}, endDate={}, goodsId={}, userId={}",
				startDate, endDate, goodsId, userId);

		List<Map> rows = statService.statSaleByUser(startDate, endDate, goodsId, userId);
		String[] columns = new String[] { "user_id", "user_name", "day", "sales_volume", "order_amount" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		logger.info("【请求结束】统计管理->销售报表->按用户统计->查询");
		return ResponseUtil.ok(statVo);
	}

	/**
	 * 按月份统计销售报表
	 * @param startDate 开始日期(yyyy-MM-dd)
	 * @param endDate 结束日期(yyyy-MM-dd)
	 * @param goodsId 商品ID(可选)
	 * @param userId 用户ID(可选)
	 * @return 月份销售统计数据
	 */
	@RequiresPermissions("admin:stat:sale:month")
	@RequiresPermissionsDesc(menu = { "统计管理", "销售报表" }, button = "按月份统计")
	@GetMapping("/sale/month")
	public Object statSaleByMonth(
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Integer goodsId,
			@RequestParam(required = false) Integer userId) {
		logger.info("【请求开始】统计管理->销售报表->按月份统计->查询,参数: startDate={}, endDate={}, goodsId={}, userId={}",
				startDate, endDate, goodsId, userId);

		List<Map> rows = statService.statSaleByMonth(startDate, endDate, goodsId, userId);
		String[] columns = new String[] { "month", "sales_volume", "order_amount" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		logger.info("【请求结束】统计管理->销售报表->按月份统计->查询");
		return ResponseUtil.ok(statVo);
	}

}
