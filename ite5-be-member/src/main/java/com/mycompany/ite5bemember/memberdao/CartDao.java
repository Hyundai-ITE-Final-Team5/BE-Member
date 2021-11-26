package com.mycompany.ite5bemember.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Cart;

@Mapper
public interface CartDao {
	public List<Cart> selectCartByMid(String mid);
	public int deleteCart(Cart cart);
	public int insertCart(Cart cart);
	public Cart selectByCart(Cart cart);
	public int updateCart(Cart cart);
	public int updateCartByMap(Map<String, Object> map);
}
