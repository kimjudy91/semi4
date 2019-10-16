<%@page import="java.util.ArrayList"%>
<%@page import="sessionListener.joo.SessionIdListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tset10_css.html</title>
<link rel="stylesheet" type="text/css" href="${cp }/index/index.css">
<style>
</style>
</head>
<script type="text/javascript">

</script>
<body>
<div id="main">
<div id="header">
<h1>세미 프로젝트</h1>
</div>
	<div id="menu">
		<div id="logins">
		<c:choose>
			<c:when test="${sessionScope.id==null }">
				<input type="button" value="로그인" onclick="location.href='${cp}/logins'">
				<input type="button" value="회원가입" onclick="location.href='${cp}/join/insert'">
			</c:when>
			<c:when test="${sessionScope.id eq 'admin' }">
				<c:choose>
				<c:when test="${report2Count==-1||report2Count==0||report2Count==null }">
					<input type="button" value="신고" onclick="location.href='${cp}/reportList'">
				</c:when>
				<c:otherwise>
					<input type="button" value="신고[${report2Count }]" onclick="location.href='${cp}/reportList'" size="10"><br>
				</c:otherwise>
				</c:choose>
				<input type="button" value="회원관리" onclick="location.href='${cp}/myPage'">
				<input type="button" value="로그아웃" onclick="location.href='${cp}/logout'">
			</c:when>
			<c:when test="${sessionScope.id!=null }">
				<input type="button" value="마이페이지" onclick="location.href='${cp}/myPage'">
				<input type="button" value="로그아웃" onclick="location.href='${cp}/logout'">
			</c:when>
		</c:choose>
		</div>
		<ul id="menus">
			<li><a href="${cp }/board/community">커뮤니티게시판</a></li>
			<li><a href="${cp }/nquire/list">문의게시판</a></li>
			<li>메뉴3</li>
			<li>메뉴4</li>
		</ul>
		<div id="users">		
		접속자명단<br>
		<%
		ArrayList<String> userIds=SessionIdListener.getUserId();
		for(String a:userIds){
			%>
			<%=a %><br>
			<%
		}
		%>
		</div>
	</div>
	<div id="home">
		<jsp:include page="${page }"/>
	</div>	
</div>
<div id="footer">
풋터
</div>
</body>
</html>