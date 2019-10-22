<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JoinOk.jsp</title>
</head>
<body>
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<c:choose>
	<c:when test="${requestScope.code=='success' }">
		<h1>회원가입을 축하드립니다!!!</h1>
	</c:when>
	<c:otherwise>
		<h1>오류로 인해 가입에 실패했습니다ㅠ_ㅜ</h1>
	</c:otherwise>
</c:choose>
</div>
</div>
<a href="${cp}/index">돌아가기</a>
</body>
</html>