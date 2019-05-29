package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}
	
	public UserVo get(UserVo vo) {
		return sqlSession.selectOne("user.getLoginUser", vo);
	}
	
	public Boolean insertUser(UserVo vo) {
	    int count = sqlSession.insert("user.insertUser", vo);
	    return 1==count;
	}
	
	public Boolean insertBlog(UserVo vo) {
		int count = sqlSession.insert("user.insertBlog", vo);
		return 1==count;
	}
	
	public Boolean deleteBlog(String id) {
		int count = sqlSession.delete("user.deleteBlog", id);
		return 1==count;
	}
	
	public Boolean deleteUser(String id) {
		int count = sqlSession.delete("user.deleteUser", id);
		return 1==count;
	}
}
