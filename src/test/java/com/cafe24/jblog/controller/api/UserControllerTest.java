package com.cafe24.jblog.controller.api;

import static org.junit.Assert.assertEquals;
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

	@Test
	public void existId() {
		UserVo vo = new UserVo();
		vo.setId("test");
		vo.setName("테스트");
		vo.setPassword("test");

		assertEquals(userService.existId(vo.getId()), false);
		userService.joinUser(vo);
		assertEquals(userService.existId(vo.getId()), true);
		userService.deleteUser(vo.getId());
		assertEquals(userService.existId(vo.getId()), false);

	}

}