package com.mycompany.ite5bemember.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ite5bemember.dto.CouponDetail;
import com.mycompany.ite5bemember.dto.DownloadedCoupon;
import com.mycompany.ite5bemember.dto.Event;
import com.mycompany.ite5bemember.memberdao.CouponDetailDao;
import com.mycompany.ite5bemember.memberdao.EventDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CouponDetailService {
	
	@Resource private EventService eventService;
	private ExecutorService executorsService = Executors.newFixedThreadPool(1);
	
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
	
	@Transactional
	public int addCoupon(int eno, String mid) throws Exception {
		
		Callable<Integer> task = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				//Event 테이블에 쿠폰count도 올려주자.
				Event event = eventDao.selectEvent(eno);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("ecount", event.getEcount()+1);
				map.put("eno", eno);
				
				//CouponDetail 테이블에 insert하자.
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
				
				int result = 0;
				try {
					if(couponDetailDao.insertCouponDetail(couponDetail) !=0 && eventDao.updateEcount(map) != 0) {
						result = 1;
					}
				} catch(Exception e) {
					result = 0;
				}
				return result;
			}
		};
		
		Future<Integer> future = executorsService.submit(task);

		return future.get();
	
	}
}
