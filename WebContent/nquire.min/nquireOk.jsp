<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<c:choose>
	<c:when test="${requestScope.code=='success' }">
		<h1>문의 완료</h1>
	</c:when>
	<c:otherwise>
		<h1>오류ㅠ_ㅜ</h1>
	</c:otherwise>
</c:choose>
</div>
</div>
<a href ="${cp }/index">메인가기</a>