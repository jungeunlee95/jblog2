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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script> 
	$(document).ready( function(){
	    $(".btn_delete").click( function() {
	        if(confirm("카테고리를 삭제하면 ")) {
	            $(this).parent().click();
	        } else {
	            return false;
	        }
	    });
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"> </c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
			<c:import url="/WEB-INF/views/includes/admin-menu.jsp"> </c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<c:forEach items='${categoryList }' var='vo' varStatus='status'>
						<tr>
							<td>${vo.no }</td>
							<td>${vo.name }</td>
							<td>${vo.count }</td>
							<td>${vo.description }</td>
							<td>
							<c:if test="${vo.name != '미분류' }">
								<a href="${pageContext.request.contextPath}/${authUser.id }/admin/category/delete/${vo.no}" 
								class="btn_delete">
									<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
								</a> 
							</c:if>
							</td>
						</tr>  		
					</c:forEach>
					  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<form:form
				      modelAttribute="categoryVo"
					  class="category-form" 
					  id="category-form" 
					  method="post" 
					  action="${pageContext.servletContext.contextPath}/${authUser.id}/admin/category">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><form:input path="name" />
							<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
								<form:errors path="name"/>
							</p>		</td>	  
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><form:input path="description" />
							<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
								<form:errors path="description"/>
							</p>			  </td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
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
</html>