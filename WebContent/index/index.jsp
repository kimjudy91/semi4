<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tset10_css.html</title>
<link rel="stylesheet" type="text/css" href="${cp }/index/indexCss.css">
<style>
</style>
</head>
<body>
<div id="main">
<div id="header">
<h1>세미 프로젝트</h1>
</div>
	<div id="menu">
		<div id="logins">
		<input type="button" value="로그인" onclick="location.href='${cp}/logins'">
		<input type="button" value="회원가입" onclick="location.href='${cp}/join/insert'">
		</div>
		<ul id="menus">
			<li><a href="${cp }/index?page=board1">메뉴1</a></li>
			<li>메뉴2</li>
			<li>메뉴3</li>
			<li>메뉴4</li>
		</ul>
		<div id="users">
		회원정보
		</div>
	</div>
	<div id="home">
	<c:choose>
		<c:when test="${page==board1 }">	
			<jsp:include page="/test/test2.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/test/test1.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
		
	</div>	
</div>
<div id="footer">
풋터
</div>
</body>
</html>