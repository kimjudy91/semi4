<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nquire.jsp</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="../css.min/detailStyle.css">
<h1>문의게시판</h1>
<hr>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<table border='1' width=500px>
	<tr><th>글번호</th><th>작성자</th><th>제목</th><th>답변여부</th><th>작성일</th></tr>
	<c:forEach var="vo" items="${list}">
	<tr>
	<td>${vo.nquire_num}</td>
	<td>${vo.id}</td>
	<td><c:if test="${vo.id==vo.id }"><a href="${cp}/nquire/detail?id=${vo.id}">${vo.title}</a></c:if></td>
	<c:if test="${vo.comments!=null }"><td>O</td></c:if><c:if test="${vo.comments==null }"><td>X</td></c:if>
	<td>${vo.r_date }</td>
	</tr>
	</c:forEach>
</table>
	<form method="get" action="${cp }/nquire/insert">
		<input type="submit" value="문의하기">
	</form>
</body>
</html>