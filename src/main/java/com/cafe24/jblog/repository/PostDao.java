package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> mainPost(String userId) {
		return sqlSession.selectList("blog.mainPost", userId);
	}
	public List<PostVo> categoryPost(Long cateNo) {
		return sqlSession.selectList("blog.categoryPost", cateNo);
	}
	
	public String getPost(Long cateNo, Long postNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("cateNo", cateNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne("blog.getPost", map);
	}

}
