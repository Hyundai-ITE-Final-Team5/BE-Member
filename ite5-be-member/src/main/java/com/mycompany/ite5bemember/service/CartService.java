package com.mycompany.ite5bemember.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.Cart;
import com.mycompany.ite5bemember.dto.CartedProduct;
import com.mycompany.ite5bemember.memberdao.CartDao;
import com.mycompany.ite5bemember.memberdao.MemberDao;
import com.mycompany.ite5bemember.productdao.ProductDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
	
	@Resource
	private MemberDao memberDao;
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private CartDao cartDao;
	
	public List<Cart> getCartList(String mid){
		return cartDao.selectCartByMid(mid);
	}
	
	public List<CartedProduct> cartProductByCart(List<Cart> cartList){
		return productDao.selectByCart(cartList);
	}
	
	public int deleteCart(Cart cart) {
		return cartDao.deleteCart(cart);
	}
	
	public Map getColorSize(String pid){
		return productDao.selectColorNSizeByPid(pid);
	}
	
	public int addCart(Cart cart) {
		return cartDao.insertCart(cart);
	}
	
	public Cart searchCart(Cart cart) {
		return cartDao.selectByCart(cart);
	}
	
	public int updateCart(Cart cart) {
		return cartDao.updateCart(cart);
	}
	
	public int updateCart(Map<String, Object> map) {
		return cartDao.updateCartByMap(map);
	}
}
