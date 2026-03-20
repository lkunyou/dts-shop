package com.qiguliuxing.dts.db.dao.ex;

import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 优惠券用户扩展Mapper
 */
public interface CouponUserMapperEx {

    /**
     * 精确更新优惠券使用状态
     * 通过指定参数精确匹配条件进行更新，避免使用Example查询的模糊匹配
     *
     * @param id         优惠券用户记录ID
     * @param status     当前状态
     * @param newStatus  新状态
     * @param orderSn    订单编号
     * @param updateTime 更新时间
     * @return 更新条数
     */
    int updateCouponStatus(
            @Param("id") Integer id,
            @Param("status") Short status,
            @Param("newStatus") Short newStatus,
            @Param("orderSn") String orderSn,
            @Param("updateTime") LocalDateTime updateTime,
            @Param("usedTime") LocalDateTime usedTime);
}
