package com.mycompany.ite5bemember.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.HomeOrder;

@Mapper
public interface HomeOrderDao {
	public List<HomeOrder> selectHomeOrder();
}
