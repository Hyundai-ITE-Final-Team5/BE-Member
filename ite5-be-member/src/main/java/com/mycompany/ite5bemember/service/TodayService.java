package com.mycompany.ite5bemember.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.Today;
import com.mycompany.ite5bemember.memberdao.TodayDao;

@Service
public class TodayService {
		
	@Resource TodayDao todayDao;
	
	private ExecutorService executorsService = Executors.newFixedThreadPool(1);
	
	public Map<String,String> visit() throws Exception {

		Callable<Integer> task = new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				
				Date nowDate  = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
				String today = simpleDateFormat.format(nowDate);
				Today todayVisit = todayDao.selectToday(today);
				
				int result = 0;
				if(todayVisit == null) {
					result = todayDao.insertToday();
				}else {
					result = todayDao.updateToday(today);
				}
				return result;
			}
		};
		
		Future<Integer> future = executorsService.submit(task);
		
		Map<String,String> map = new HashMap<String, String>();
		if(future.get() == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
}
