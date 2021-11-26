package com.mycompany.ite5bemember.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.CouponDetail;
import com.mycompany.ite5bemember.dto.DownloadedCoupon;
import com.mycompany.ite5bemember.dto.Event;
import com.mycompany.ite5bemember.memberdao.CouponDetailDao;
import com.mycompany.ite5bemember.memberdao.EventDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CouponDetailService {
	
	@Resource
	private EventDao eventDao; 
	
	@Resource
	private CouponDetailDao couponDetailDao;
	
	public List<DownloadedCoupon> getCouponList(String mid) {
		return couponDetailDao.selectCouponList(mid);
	}
	
	public CouponDetail searchCoupon(int eno, String mid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eno", eno);
		map.put("mid", mid);
		return couponDetailDao.selectCouponDetail(map);
	}
	
	public int addCoupon(int eno, String mid) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, 1);
		
		
		CouponDetail couponDetail = new CouponDetail();
		couponDetail.setEno(eno);
		couponDetail.setMid(mid);
		couponDetail.setCpissuedate(today);
		couponDetail.setCpexpiredate(cal.getTime());
		couponDetail.setCpid(eno+mid);
		couponDetail.setCpstatus(1);
		Event event = eventDao.selectEvent(eno);
		
		//Event 테이블에 쿠폰count도 올려주자.
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ecount", event.getEcount()+1);
		map.put("eno", eno);
		int result = 0;
		if(eventDao.updateEcount(map) != 0 &&couponDetailDao.insertCouponDetail(couponDetail) != 0) {
			result = 1;
		}
		return result;
	}
}
