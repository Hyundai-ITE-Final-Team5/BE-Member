package com.mycompany.ite5bemember.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.LikedProduct;
import com.mycompany.ite5bemember.dto.Likes;
import com.mycompany.ite5bemember.memberdao.LikesDao;
import com.mycompany.ite5bemember.productdao.ProductDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikesService {
	
	@Resource
	private LikesDao likesDao;
	
	@Resource
	private ProductDao productDao;
	
	//찜목록을 가져오기위해서 mid로 likes리스트를 가져온다.
	public List<Likes> getLikeList(String mid){
		return likesDao.selectLikeByMid(mid);
	}
	
	//가져온 likes로 상품의 이미지, 이름 등을 가져온다. 
	public List<LikedProduct> getProductbyLike(List<Likes> likes){
		return productDao.selectBylikes(likes);
	}
	
	//찜 목록에서 삭제
	public int deleteLike(Likes likes) {
		return likesDao.deleteLikes(likes);
	}
	
	//찜 목록에 추가
	public int addLike(Likes likes) {
		return likesDao.insertLikes(likes);
	}
	
}
