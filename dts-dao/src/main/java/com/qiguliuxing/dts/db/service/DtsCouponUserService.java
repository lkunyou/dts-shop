package com.qiguliuxing.dts.db.service;

import com.github.pagehelper.PageHelper;
import com.qiguliuxing.dts.db.dao.DtsCouponUserMapper;
import com.qiguliuxing.dts.db.dao.ex.CouponUserMapper;
import com.qiguliuxing.dts.db.domain.DtsCouponUser;
import com.qiguliuxing.dts.db.domain.DtsCouponUserExample;
import com.qiguliuxing.dts.db.util.CouponUserConstant;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DtsCouponUserService {
	@Resource
	private DtsCouponUserMapper couponUserMapper;
	@Resource
	private CouponUserMapper couponUserMapperEx;

	public Integer countCoupon(Integer couponId) {
		DtsCouponUserExample example = new DtsCouponUserExample();
		example.or().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
		return (int) couponUserMapper.countByExample(example);
	}

	public Integer countUserAndCoupon(Integer userId, Integer couponId) {
		DtsCouponUserExample example = new DtsCouponUserExample();
		example.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
		return (int) couponUserMapper.countByExample(example);
	}

	public void add(DtsCouponUser couponUser) {
		couponUser.setAddTime(LocalDateTime.now());
		couponUser.setUpdateTime(LocalDateTime.now());
		couponUserMapper.insertSelective(couponUser);
	}

	public List<DtsCouponUser> queryList(Integer userId, Integer couponId, Short status, Integer page, Integer size,
			String sort, String order) {
		DtsCouponUserExample example = new DtsCouponUserExample();
		DtsCouponUserExample.Criteria criteria = example.createCriteria();
		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (couponId != null) {
			criteria.andCouponIdEqualTo(couponId);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)) {
			PageHelper.startPage(page, size);
		}

		return couponUserMapper.selectByExample(example);
	}

	public List<DtsCouponUser> queryAll(Integer userId, Integer couponId) {
		return queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
	}

	public List<DtsCouponUser> queryAll(Integer userId) {
		return queryList(userId, null, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
	}

	public DtsCouponUser queryOne(Integer userId, Integer couponId) {
		List<DtsCouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, 1, 1,
				"add_time", "desc");
		if (couponUserList.size() == 0) {
			return null;
		}
		return couponUserList.get(0);
	}

	public DtsCouponUser findById(Integer id) {
		return couponUserMapper.selectByPrimaryKey(id);
	}

	public int update(DtsCouponUser couponUser) {
		couponUser.setUpdateTime(LocalDateTime.now());
		return couponUserMapper.updateByPrimaryKeySelective(couponUser);
	}

	public List<DtsCouponUser> queryExpired() {
		DtsCouponUserExample example = new DtsCouponUserExample();
		example.or().andStatusEqualTo(CouponUserConstant.STATUS_USABLE).andEndTimeLessThan(LocalDate.now())
				.andDeletedEqualTo(false);
		return couponUserMapper.selectByExample(example);
	}

	/**
	 * 使用优惠券（带乐观锁，防止优惠券被多用）
	 * <p>
	 * 使用自定义Mapper，通过精确的条件匹配（user_id, coupon_id, status, deleted）
	 * 实现乐观锁，防止优惠券被多用
	 *
	 * @param userId  用户ID
	 * @param couponId 优惠券ID
	 * @param orderSn 订单编号
	 * @return 影响的行数，0表示使用失败（可能已被使用或不存在）
	 */
	public int useCoupon(Integer userId, Integer couponId, String orderSn) {
		return couponUserMapperEx.useCoupon(
				userId,
				couponId,
				CouponUserConstant.STATUS_USED,
				LocalDateTime.now(),
				orderSn,
				LocalDateTime.now()
		);
	}
}
