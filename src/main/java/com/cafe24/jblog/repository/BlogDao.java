package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlogInfo(String userId) {
		return sqlSession.selectOne("blog.getById", userId);
	}
	
	public Boolean modifyBlogInfo(BlogVo vo) {
		return 1==sqlSession.update("blog.modifyBlogInfo", vo);
	}
}
