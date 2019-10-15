<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${requestScope.code=='success' }">
		<h1>신고 완료</h1>
	</c:when>
	<c:otherwise>
		<h1>오류ㅠ_ㅜ</h1>
	</c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath }/board/insert">돌아가기</a>