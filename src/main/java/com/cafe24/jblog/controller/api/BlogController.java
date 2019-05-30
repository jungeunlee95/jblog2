package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller("blogAPIController")
@RequestMapping("/blog/api")
public class BlogController {
	
	@Autowired
	private BlogService BlogService;
	
	@Auth
	@ResponseBody
	@RequestMapping("/checkCate")
	public JSONResult checkId(
			@RequestParam(value="name", required=true, defaultValue="") String name,
			@AuthUser UserVo authUser) {
		System.out.println("@@@ 들어옴 체크");
		Boolean exist = BlogService.existCate(name, authUser.getId());
		return JSONResult.success(exist);
	}

}










