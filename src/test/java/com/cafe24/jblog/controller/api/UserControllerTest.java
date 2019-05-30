package com.cafe24.jblog.controller.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

	// id 중복 확인 test
	@Test
	public void existId() {
		UserVo vo = new UserVo();
		vo.setId("test");
		vo.setName("테스트");
		vo.setPassword("test");

		assertFalse(userService.existId(vo.getId()));
		userService.joinUser(vo);
		assertFalse(userService.existId(vo.getId()));
		userService.deleteUser(vo.getId());
		assertFalse(userService.existId(vo.getId()));

	}

}