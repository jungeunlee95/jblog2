package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

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
	
	public Boolean addCategory(CategoryVo vo) {
		return categoryDao.insert(vo);
	}
	
	public Boolean deleteCategory(Long no) {
		Boolean result = false;
		Boolean a = categoryDao.deletePost(no);
		Boolean b = categoryDao.deleteCategory(no);
		if(a==true && b==true) {
			result = true;
		}
		return result;
	}
	
	public Boolean modifyBlogInfo(BlogVo vo) {
		return blogDao.modifyBlogInfo(vo);
	}
	
	public Boolean writePost(PostVo vo) {
		return blogDao.writePost(vo);
	}
}
