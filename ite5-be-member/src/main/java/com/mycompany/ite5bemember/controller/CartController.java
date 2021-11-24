package com.mycompany.ite5bemember.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.Cart;
import com.mycompany.ite5bemember.dto.CartedProduct;
import com.mycompany.ite5bemember.security.JWTUtil;
import com.mycompany.ite5bemember.service.CartService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member/cart")
@Slf4j

public class CartController {
	
	@Resource
	CartService cartService;
	
	
	// **장바구니리스트 조회**
	@PostMapping("/cartlist")
	public Map<String,Object> searchlike(HttpServletRequest request){
		String jwt = request.getHeader("Authorization").substring(7);
	 	Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		List<Cart> cartList = cartService.getCartList(mid);
		List<CartedProduct> cartedList = cartService.cartProductByCart(cartList);
		
		Map<String, Object> map = new HashMap<>();
		map.put("cartedlist", cartedList);
		return map;
	}
	
	// **장바구니에서 상품 삭제**
	@DeleteMapping("/deletecart")
	public Map<String,String> deletecart(HttpServletRequest request, @RequestBody String json) {
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		JSONObject jsonObject = new JSONObject(json);
		String psid = jsonObject.getString("psid");
		
		Cart cart = new Cart();
		cart.setMid(mid);
		cart.setPsid(psid);
		
		int result = cartService.deleteCart(cart);
		
		Map<String,String> map = new HashMap<String, String>();
		
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
	
	// **장바구니에서 상품 "변경"버튼 누를때 => 색상,사이즈 정보보여주어야한다.
	@PostMapping("/getcolorsize")
	public Map<String,Object> getcolorsize(@RequestBody String json) {
		
		JSONObject jsonObject = new JSONObject(json);
		String pid = jsonObject.getString("pid");
		
		Map colorsize = cartService.getColorSize(pid);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("colorsize", colorsize);
		return map;
	}
	
	// **장바구니 옵션 변경후 젖아버튼을 눌렀을때 **
	
	
	// **장바구니에 담기**
	@PostMapping("/addcart")
	public Map<String,String> addcart(HttpServletRequest request, @RequestBody Cart cart){
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		cart.setMid(mid);
		
		int result = cartService.addCart(cart);
		Map<String,String> map = new HashMap<String, String>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
}
