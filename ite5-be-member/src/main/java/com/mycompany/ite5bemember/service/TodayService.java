package com.mycompany.ite5bemember.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.Today;
import com.mycompany.ite5bemember.memberdao.TodayDao;

@Service
public class TodayService {
	
	@Resource TodayDao todayDao;
	
	
	public Today getToday() {
		Date nowDate  = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
		String today = simpleDateFormat.format(nowDate);
		return todayDao.selectToday(today);
	}
	
	public int makeToday() {
		return todayDao.insertToday();
	}
	
	public int updateToday() {
		Date nowDate  = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
		String today = simpleDateFormat.format(nowDate);
		return todayDao.updateToday(today);
	}
}
