package com.qiguliuxing.dts.db.service;

import com.qiguliuxing.dts.admin.vo.GoodsSalesReportVo;
import com.qiguliuxing.dts.admin.vo.MonthSalesReportVo;
import com.qiguliuxing.dts.admin.vo.SalesReportQueryParam;
import com.qiguliuxing.dts.admin.vo.UserSalesReportVo;
import com.qiguliuxing.dts.db.dao.ex.SalesReportMapper;
import com.qiguliuxing.dts.db.util.OrderUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class SalesReportService {

    @Resource
    private SalesReportMapper salesReportMapper;

    private static final List<Short> VALID_ORDER_STATUS = Arrays.asList(
            OrderUtil.STATUS_PAY,      // 201 已付款
            OrderUtil.STATUS_SHIP,     // 301 已发货
            OrderUtil.STATUS_CONFIRM,  // 401 已收货
            OrderUtil.STATUS_AUTO_CONFIRM // 402 已收货(系统)
    );

    public List<GoodsSalesReportVo> getSalesReportByGoods(SalesReportQueryParam queryParam) {
        return salesReportMapper.selectSalesReportByGoods(
                queryParam.getStartDate(),
                queryParam.getEndDate(),
                queryParam.getGoodsId(),
                queryParam.getUserId(),
                VALID_ORDER_STATUS
        );
    }

    public List<UserSalesReportVo> getSalesReportByUser(SalesReportQueryParam queryParam) {
        return salesReportMapper.selectSalesReportByUser(
                queryParam.getStartDate(),
                queryParam.getEndDate(),
                queryParam.getGoodsId(),
                queryParam.getUserId(),
                VALID_ORDER_STATUS
        );
    }

    public List<MonthSalesReportVo> getSalesReportByMonth(SalesReportQueryParam queryParam) {
        return salesReportMapper.selectSalesReportByMonth(
                queryParam.getStartDate(),
                queryParam.getEndDate(),
                queryParam.getGoodsId(),
                queryParam.getUserId(),
                VALID_ORDER_STATUS
        );
    }
}
