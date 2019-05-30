package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public Map<String,Object> getCatePost(Long cateNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPost",postDao.getPost(cateNo, postNo));
		map.put("postList", postDao.categoryPost(cateNo));
		return map;
	}
	
	public Map<String, Object> getBlogInfo(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogVo", blogDao.getBlogInfo(userId));
		map.put("categoryList", categoryDao.getCategory(userId));
		return map;
	}
	
	public BlogVo getBlogInfo2(String userId) {
		return blogDao.getBlogInfo(userId);
	}
	
	public List<CategoryVo> getCategory(String userId) {
		return categoryDao.getCategory(userId);
	}
	
	public List<PostVo> mainPost(String userId) {
		return postDao.mainPost(userId);
	}
	
	public List<PostVo> categoryPost(Long cateNo) {
		return postDao.categoryPost(cateNo);
	}
	
	public String getPost(Long cateNo, Long postNo) {
		return postDao.getPost(cateNo, postNo);
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
	
	public Boolean existCate(String name, String userId) {
		CategoryVo categoryVo = categoryDao.get(name, userId);
		return categoryVo != null;
	}
}
