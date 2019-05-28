package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.FileuploadService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping(value = {"","/{pathNo1}","/{pathNo1}/{pathNo2}" })
	public String blogMain(@PathVariable(value = "userId") String userId,
						@PathVariable(value = "pathNo1") Optional<Long> pathNo1,
						@PathVariable(value = "pathNo2") Optional<Long> pathNo2,
						Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		List<CategoryVo> cvoList = blogService.getCategory(userId);
		
		model.addAttribute("blogVo", vo);
		model.addAttribute("categoryList", cvoList);
		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping(value = {"/admin/basic"})
	public String blogAdmin(@AuthUser UserVo authUser,
			@PathVariable(value = "userId") String userId,
			Model model) {
		if(!userId.equals(authUser.getId())){
			return "redirect:/" + userId;
		}
		
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		List<CategoryVo> cvoList = blogService.getCategory(userId);
		
		model.addAttribute("blogVo", vo);
		model.addAttribute("categoryList", cvoList);
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value = {"/admin/basic/modify"})
	public String modifyBlogInfo(@AuthUser UserVo authUser,
								@PathVariable(value = "userId") String userId,
								@RequestParam(value="title") String title,
								@RequestParam(value="file") MultipartFile multipartFile,
								Model model) {
		if(!userId.equals(authUser.getId())){
			return "redirect:/" + userId;
		}
		String url = fileuploadService.restore(multipartFile);
		BlogVo vo = new BlogVo();
		vo.setBlogId(userId);
		vo.setTitle(title);
		vo.setLogo(url);
		blogService.modifyBlogInfo(vo);
		
		BlogVo bvo = blogService.getBlogInfo(userId);
		model.addAttribute("blogVo", bvo);
		return "blog/blog-main"; 
	}
	
	
	

}
