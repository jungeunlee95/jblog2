<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
var checkId = 0;
	$(function(){
		$('#id').change(function(){
			$('#btn-checkid').show(); 
			$('#img-checkid').hide();
		});
		
		$('#btn-checkid').click(function(){
			var id = $('#id').val();
			if(id == ''){
				alert('아이디는 3자리 이상부터 가능합니다.');
				return;
			}
			if(id.length<4){
				alert('아이디는 3자리 이상부터 가능합니다.');
				return;
			}
			 
			/* ajax 통신 */
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/user/api/checkid?id=" + id,
				type : "get",
				dataType : "json",
				data : "",
				success: function(response){
					if(response.result != "success"){
						console.log(response);
						console.error(response.message);
						return;
					}
					if(response.data == true){
						alert('이미 존재하는 id 입니다.');
						$("#id").focus();
						$("#id").val("");
						if(checkId==1){
							checkId=0;
						}
						return;
					} else{
						if(checkId==0){
							checkId=1;
						}
					}
					$('#btn-checkid').hide();
					$('#img-checkid').show();
				},
				error : function(xhr, error){
					console.error("error : " + error);
				}
			});
		});
	});
	
	function doWrite() {
		if (checkId == 0) {
			alert('이메일 중복체크를 확인해주세요!');
			$('#btn-checkid').focus();
			return false;
		}
	}
</script>
</head>
<body> 
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"> </c:import>	
		<form:form 
			  modelAttribute="userVo"
			  class="join-form" 
			  id="join-form" 
			  method="post" 
			  action="${pageContext.servletContext.contextPath}/user/join"
			  onsubmit="return doWrite()">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
				<form:errors path="name"/>
			</p>
			
			<label class="block-label" for="id">아이디</label>
			<form:input path="id"/> 
			<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
				<form:errors path="id"/>
			</p>
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:input path="password"/>
			<p style="font-weight: bold; color: red;text-align: left; padding: 0;">
				<form:errors path="password"/>
			</p>
 
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
