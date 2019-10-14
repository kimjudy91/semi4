<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/findPwd/result.jsp</title>
</head>
<body>
<c:choose>
	<c:when test="${code=='success' }">
		<h1>${email }로 입시비밀번호를 보냈습니다.확인후 로그인해주세요</h1>
		<a href="${cp }/login/login.jsp">로그인 하러가기</a>
	</c:when>
	<c:otherwise>
		<h1>아이디 또는 이메일이 잘못되었습니다.</h1>
		<a href="${cp }/index/index.jsp">메인으로 가기</a>
	</c:otherwise>
</c:choose>


</body>
</html>