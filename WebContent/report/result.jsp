<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report/result.jsp</title>
</head>
<body>
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<c:choose>
	<c:when test="${code=='success' }">
		<h1>신고성공</h1>
		<a href ="${cp }/reportList">목록가기</a>
	</c:when>
	<c:otherwise>
		<h1>신고실패</h1>
		<a href ="${cp }/index">메인가기</a>
	</c:otherwise>
</c:choose>
</div>
</div>
</body>

</html>