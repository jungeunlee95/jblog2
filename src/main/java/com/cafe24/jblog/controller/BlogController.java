package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.FileuploadService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
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
		
		if(pathNo2.isPresent()) { // 카테고리의 글
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
			model.addAttribute("currentPost", blogService.getPost(categoryNo, postNo));
			model.addAttribute("postList", blogService.categoryPost(categoryNo));			
		}else if (pathNo1.isPresent()) { // 카테고리만
			categoryNo = pathNo1.get();
			model.addAttribute("postList", blogService.categoryPost(categoryNo));			
		}else {
			model.addAttribute("postList", blogService.mainPost(userId));			
		}
		
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
	
	@Auth
	@RequestMapping(value = {"/admin/category"}, method = RequestMethod.GET)
	public String adminCategory(@AuthUser UserVo authUser,
			@ModelAttribute CategoryVo categoryVo,
			Model model) {
		String userId = authUser.getId(); 
		if(!userId.equals(authUser.getId())){
			return "redirect:/" + userId;
		}
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		model.addAttribute("blogVo", vo);
		model.addAttribute("categoryList", blogService.getCategory(userId));
		return "blog/blog-admin-category"; 
	}
	
	@Auth
	@RequestMapping(value = {"/admin/category"}, method = RequestMethod.POST)
	public String adminCategory(@AuthUser UserVo authUser,
			@ModelAttribute @Valid CategoryVo categoryVo,
			@PathVariable(value = "userId") String userId,
			BindingResult result, 
			Model model) {
		if(!userId.equals(userId)){
			return "redirect:/" + userId;
		}
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());
			return "/blog/blog-admin-category";
		}
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		model.addAttribute("blogVo", vo);
		categoryVo.setBlogId(userId);
		blogService.addCategory(categoryVo);
		model.addAttribute("categoryList", blogService.getCategory(userId));
		return "redirect:/" + userId + "/admin/category"; 
	}
	
	@Auth
	@RequestMapping(value = {"/admin/category/delete/{no}"})
	public String deleteCategory(@AuthUser UserVo authUser,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "no") Long cateNo, 
			Model model) {
		if(!userId.equals(userId)){
			return "redirect:/" + userId;
		}
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		model.addAttribute("blogVo", vo);
		blogService.deleteCategory(cateNo);
		model.addAttribute("categoryList", blogService.getCategory(userId));
		return "redirect:/" + userId + "/admin/category"; 
	}
	
	@Auth
	@RequestMapping(value = {"/admin/write"}, method = RequestMethod.GET)
	public String adminWrite(@AuthUser UserVo authUser,
							@PathVariable(value = "userId") String userId,
							@ModelAttribute PostVo postVo,
							Model model) {
		if(!userId.equals(userId)){
			return "redirect:/" + userId;
		}
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		model.addAttribute("blogVo", vo);
		
		List<CategoryVo> cvoList = blogService.getCategory(userId);
		model.addAttribute("categoryList", cvoList);

		return "blog/blog-admin-write"; 
	}
	
	@Auth
	@RequestMapping(value = {"/admin/write"}, method = RequestMethod.POST)
	public String adminWrite(@AuthUser UserVo authUser,
							@ModelAttribute @Valid PostVo postVo,
							@PathVariable(value = "userId") String userId,
							BindingResult result, 
							Model model) {
		if(!userId.equals(userId)){
			return "redirect:/" + userId;
		}
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());
			return "/blog/blog-admin-category";
		}
		
		BlogVo vo = blogService.getBlogInfo(userId);
		if(vo==null) {
			return "error/404";
		}
		model.addAttribute("blogVo", vo);

		blogService.writePost(postVo);

		return "redirect:/" + userId; 
	}
	

}
