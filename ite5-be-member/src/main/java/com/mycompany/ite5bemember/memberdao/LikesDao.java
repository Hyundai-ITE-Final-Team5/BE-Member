package com.mycompany.ite5bemember.memberdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Likes;

@Mapper
public interface LikesDao {
	public List<Likes> selectLikeByMid(String mid);
	public int deleteLikes(Likes likes);
	public int insertLikes(Likes likes);
}
