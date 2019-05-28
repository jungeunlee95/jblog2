package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public BlogVo getBlogInfo(String userId) {
		return blogDao.getBlogInfo(userId);
	}
	
	public List<CategoryVo> getCategory(String userId) {
		return categoryDao.getCategory(userId);
	}
	
	public Boolean modifyBlogInfo(BlogVo vo) {
		return blogDao.modifyBlogInfo(vo);
	}
}
