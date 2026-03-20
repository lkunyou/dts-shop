package com.qiguliuxing.dts.db.dao.ex;

import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 优惠券用户自定义Mapper
 */
public interface CouponUserMapper {

	/**
	 * 使用优惠券（带乐观锁，防止优惠券被多用）
	 * <p>
	 * 更新条件：用户ID、优惠券ID、状态为可用(STATUS_USABLE)、未删除
	 *
	 * @param userId   用户ID
	 * @param couponId 优惠券ID
	 * @param status   要更新的状态
	 * @param usedTime 使用时间
	 * @param orderSn  订单编号
	 * @param updateTime 更新时间
	 * @return 影响的行数，0表示更新失败（优惠券已被使用或不存在）
	 */
	int useCoupon(@Param("userId") Integer userId,
				  @Param("couponId") Integer couponId,
				  @Param("status") Short status,
				  @Param("usedTime") LocalDateTime usedTime,
				  @Param("orderSn") String orderSn,
				  @Param("updateTime") LocalDateTime updateTime);
}
