<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"> </c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<p>
					<c:if test="${empty postList }">
						<h2>게시글이 존재하지 않습니다.</h2>
					</c:if>
					<c:choose>
						<c:when test="${empty currentPost }">
							<h1> ${postList[0].title }</h1><br><hr><br>
							${postList[0].content } 
						</c:when> 
						<c:otherwise>
							<h1>${currentPost.title }</h1><br><hr><br>
							${currentPost.content } 
						</c:otherwise>
					</c:choose>
					<p>
				</div>
				<ul class="blog-list">
				<c:forEach items='${postList }' var='vo' varStatus='status'>
					<li><a href="${pageContext.servletContext.contextPath}/${categoryList[0].blogId}/${vo.categoryNo}/${vo.no}">${vo.title }</a> <span>${vo.regDate }</span>	</li>
				</c:forEach>
					
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
			<c:choose>
			<c:when test="${blogVo.logo eq '기본로고' || empty blogVo.logo || blogVo.logo==null}">
				<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCJSUZCemWAJrmJQaoDiPY7I2KcKVsUJL69QymDDkkUO3Fds30kw" 
				style="width:100px; height: 100px;"><br>			
			</c:when>
			<c:otherwise>
				<img src="${pageContext.request.contextPath }/assets${blogVo.logo }" 
				style="width:100px; height: 100px;"><br>
			</c:otherwise>
			</c:choose>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리!</h2>
			<ul>		
			<c:forEach items='${categoryList }' var='vo' varStatus='status'>
				<li><a href="${pageContext.servletContext.contextPath}/${vo.blogId}/${vo.no}">${vo.name }</a></li>			
			</c:forEach>
			</ul>
		</div> 
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>