package com.qiguliuxing.dts.admin.web;

import com.alibaba.fastjson.JSONObject;
import com.qiguliuxing.dts.admin.annotation.RequiresPermissionsDesc;
import com.qiguliuxing.dts.admin.vo.GoodsSalesReportVo;
import com.qiguliuxing.dts.admin.vo.MonthSalesReportVo;
import com.qiguliuxing.dts.admin.vo.SalesReportQueryParam;
import com.qiguliuxing.dts.admin.vo.UserSalesReportVo;
import com.qiguliuxing.dts.core.util.ResponseUtil;
import com.qiguliuxing.dts.db.service.SalesReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/salesReport")
@Validated
public class AdminSalesReportController {

    private static final Logger logger = LoggerFactory.getLogger(AdminSalesReportController.class);

    @Autowired
    private SalesReportService salesReportService;

    @RequiresPermissions("admin:salesReport:goods")
    @RequiresPermissionsDesc(menu = {"统计管理", "销售报表"}, button = "按商品统计")
    @GetMapping("/goods")
    public Object getSalesReportByGoods(@RequestBody SalesReportQueryParam queryParam) {
        logger.info("【请求开始】统计管理->销售报表->按商品统计,请求参数:{}", JSONObject.toJSONString(queryParam));

        List<GoodsSalesReportVo> result = salesReportService.getSalesReportByGoods(queryParam);

        logger.info("【请求结束】统计管理->销售报表->按商品统计,响应结果:{}", JSONObject.toJSONString(result));
        return ResponseUtil.ok(result);
    }

    @RequiresPermissions("admin:salesReport:user")
    @RequiresPermissionsDesc(menu = {"统计管理", "销售报表"}, button = "按用户统计")
    @GetMapping("/user")
    public Object getSalesReportByUser(@RequestBody SalesReportQueryParam queryParam) {
        logger.info("【请求开始】统计管理->销售报表->按用户统计,请求参数:{}", JSONObject.toJSONString(queryParam));

        List<UserSalesReportVo> result = salesReportService.getSalesReportByUser(queryParam);

        logger.info("【请求结束】统计管理->销售报表->按用户统计,响应结果:{}", JSONObject.toJSONString(result));
        return ResponseUtil.ok(result);
    }

    @RequiresPermissions("admin:salesReport:month")
    @RequiresPermissionsDesc(menu = {"统计管理", "销售报表"}, button = "按月份统计")
    @GetMapping("/month")
    public Object getSalesReportByMonth(@RequestBody SalesReportQueryParam queryParam) {
        logger.info("【请求开始】统计管理->销售报表->按月份统计,请求参数:{}", JSONObject.toJSONString(queryParam));

        List<MonthSalesReportVo> result = salesReportService.getSalesReportByMonth(queryParam);

        logger.info("【请求结束】统计管理->销售报表->按月份统计,响应结果:{}", JSONObject.toJSONString(result));
        return ResponseUtil.ok(result);
    }
}
