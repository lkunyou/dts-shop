package com.qiguliuxing.dts.db.dao.ex;

import com.qiguliuxing.dts.admin.vo.GoodsSalesReportVo;
import com.qiguliuxing.dts.admin.vo.MonthSalesReportVo;
import com.qiguliuxing.dts.admin.vo.UserSalesReportVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface SalesReportMapper {

    List<GoodsSalesReportVo> selectSalesReportByGoods(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("goodsId") Integer goodsId,
            @Param("userId") Integer userId,
            @Param("orderStatusList") List<Short> orderStatusList);

    List<UserSalesReportVo> selectSalesReportByUser(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("goodsId") Integer goodsId,
            @Param("userId") Integer userId,
            @Param("orderStatusList") List<Short> orderStatusList);

    List<MonthSalesReportVo> selectSalesReportByMonth(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("goodsId") Integer goodsId,
            @Param("userId") Integer userId,
            @Param("orderStatusList") List<Short> orderStatusList);
}
