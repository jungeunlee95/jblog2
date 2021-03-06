package com.cafe24.jblog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class BlogServiceTest {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	// 카테고리 가져오기
//		@Test
	public void getCategory() {
		String userId = "aaa";

		List<CategoryVo> list = categoryDao.getCategory(userId);
		assertNotNull(list);
	}
	
	// 카테고리 넣기
//	@Test
	public void addCategory() {
		CategoryVo vo = new CategoryVo();
		vo.setBlogId("aaa");
		vo.setName("test");
		vo.setDescription("test카테고리");
		
		assertEquals(categoryDao.insert(vo), true);
	}
	
//	@Test
	public void deleteCategory() {
		assertTrue(categoryDao.deletePost(11L));
		assertTrue(categoryDao.deleteCategory(11L));
	}
	
//	@Test
	public void writePost() {
		PostVo vo = new PostVo();
		vo.setTitle("test");
		vo.setContent("testtest");
		vo.setCategoryNo(9L);
		
		assertTrue(blogDao.writePost(vo));
		
	}
	
//	@Test
	public void mainPost() {
		List<PostVo> list = postDao.mainPost("jjjj");
		for(PostVo vo : list) {
			System.out.println(vo.toString());
		}
	}
	
//	@Test
	public void existCate() {
		CategoryVo vo = categoryDao.get("미분류","aaa");
		System.out.println(vo);
	}


}
