package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {

		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED; // 난 이거 몰라
		}
		
		// 내가 아는 파라미터라면 가져와서
		HttpServletRequest request = 
				webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		// 설정
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		// @AuthUser가 안붙어있으면 return false
		if(authUser==null) {
			return false; 
		}
		// AuthUser어노테이션이 붙어있는 파라미터 타입 검사
		if(parameter.getParameterType().equals(UserVo.class)==false) { 
			return false;
		}
		return true;
	}
}
