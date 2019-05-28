package com.cafe24.jblog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class UserControllerTest {

	@Autowired
	private UserService userService;

	@Test
	public void joinUserTest() {
		UserVo vo = new UserVo();
		vo.setId("test");
		vo.setName("테스트");
		vo.setPassword("test");
		System.out.println(vo);

		userService.joinUser(vo);
		userService.deleteUser(vo.getId());
		
	}

}
