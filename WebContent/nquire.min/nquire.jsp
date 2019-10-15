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
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<table border='1' width=500px>
	<tr><th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th></tr>
	<c:forEach var="vo" items="${list}">
	<tr>
	<td>${vo.nquire_num}</td>
	<td>${vo.id}</td>
	<td><a href="${cp}/nquire/insert?id=${vo.id}">${vo.title}</a></td>
	<td>${vo.r_date }</td>
	</tr>
	</c:forEach>
</table>
	<form method="get" action="${cp }/nquire/insert">
		<input type="submit" value="문의하기">
	</form>
</body>
</html>