package com.cafe24.jblog.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class BlogControllerTest {

	@Autowired
	private BlogService blogService;

	// 블로그 정보 가져오기
//	@Test
	public void getBlogInfo() {
		String userId = "aaa";
		
		assertNotNull(blogService.getBlogInfo(userId));
	}
	
	// 카테고리 가져오기
//	@Test
	public void getCategory() {
		String userId = "aaa";
		
		List<CategoryVo> list = blogService.getCategory(userId);
		assertNotNull(list);
	}
	
	// 블로그 정보 수정 test
//	@Test
	public void modifyBlogInfo() {
		BlogVo vo = new BlogVo();
		vo.setBlogId("aaa");
		vo.setTitle("333");
		vo.setLogo("111");
		blogService.modifyBlogInfo(vo);
	}
}




