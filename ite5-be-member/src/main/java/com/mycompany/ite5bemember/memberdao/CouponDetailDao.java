package com.mycompany.ite5bemember.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.CouponDetail;
import com.mycompany.ite5bemember.dto.DownloadedCoupon;


@Mapper
public interface CouponDetailDao {
	public List<DownloadedCoupon> selectCouponList(String mid);
	public CouponDetail selectCouponDetail(Map<String, Object> map);
	public int insertCouponDetail(CouponDetail couponDetail);
}
