package com.qiguliuxing.dts.db.dao.ex;

import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface CouponUserMapper {
    int useCouponCAS(@Param("id") Integer id, 
                     @Param("orderSn") String orderSn, 
                     @Param("usedTime") LocalDateTime usedTime,
                     @Param("oldStatus") Short oldStatus,
                     @Param("newStatus") Short newStatus);
}
