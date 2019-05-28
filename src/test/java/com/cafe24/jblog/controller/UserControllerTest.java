package com.cafe24.jblog.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class UserControllerTest {

	@Autowired
	private UserService userService;

	// 회원가입, 로그인, 회원탈퇴 테스트
	@Test
	public void joinUserTest() {
		UserVo vo = new UserVo();
		vo.setId("test");
		vo.setName("테스트");
		vo.setPassword("test");
		System.out.println(vo);

		userService.joinUser(vo);
		
		// 로그인 테스트
		UserVo vo2 = userService.getUser(vo);
		assertEquals(vo2.getId(), "test");
		assertEquals(vo2.getName(), "테스트");
		assertEquals(vo2.getPassword(), "test");
		
		userService.deleteUser(vo.getId());
	}
	
	

}
