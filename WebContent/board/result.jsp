<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${code=='success' }">
		<h1>요청작업을 수행완료했습니다.</h1>
	</c:when>
	<c:otherwise>
		<h1>오류로 인해 요청작업을 수행하지 못했습니다.</h1>
	</c:otherwise>
</c:choose>
<a href ="${cp }/index">메인가기</a>