package com.mycompany.ite5bemember.memberdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.HomeOrderDto;

@Mapper
public interface HomeOrderDao {
	public List<HomeOrderDto> selectHomeOrder();
}
