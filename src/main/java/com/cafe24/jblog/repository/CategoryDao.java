package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;


@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(String userId) {
		return 1==sqlSession.insert("category.insert", userId);
	}
	
	public Boolean insert(CategoryVo vo) {
		return 1==sqlSession.insert("category.insertVo", vo);
	}
	
	public Boolean deletePost(Long no) {
		return 1==sqlSession.delete("category.deletePost", no);
	}
	
	public Boolean deleteCategory(Long no) {
		return 1==sqlSession.delete("category.deleteCategory", no);
	}
	
	public List<CategoryVo> getCategory(String userId) {
		return sqlSession.selectList("category.getList", userId);
	}
	
}
