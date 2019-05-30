<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"> </c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp"> </c:import>
				<form:form
				modelAttribute="blogVo"
				action="${pageContext.request.contextPath}/${authUser.id}/admin/basic/modify" 
				method="post" 
				enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td>
			      				<form:input type="text" size="40" path="title" value="${blogVo.title }"/>
			      				<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
									<form:errors path="title"/>
								</p>
			      			</td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td>
			      			<c:choose>
								<c:when
									test="${blogVo.logo eq '기본로고' || empty blogVo.logo || blogVo.logo==null}">
									<img id="image_section" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCJSUZCemWAJrmJQaoDiPY7I2KcKVsUJL69QymDDkkUO3Fds30kw"
										style="width: 100px; height: 100px;">
									<br>
								</c:when>
								<c:otherwise>
									<img id="image_section" src="${pageContext.request.contextPath }/assets${blogVo.logo }"
										style="width: 100px; height: 100px;">
									<br>
								</c:otherwise>
							</c:choose> 
			      			
			      			<c:if test="${blogVo.logo != '기본로고' || not empty blogVo.logo || blogVo.logo != null}">
								<form:hidden path="logo" value="${blogVo.logo }"/>			
							</c:if>
			      			</td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type='file' id="imgInput" name="file"/></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form:form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
<script>
	function readURL(input) {

		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#image_section').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#imgInput").change(function() {
		readURL(this);
	});
</script>
</html>