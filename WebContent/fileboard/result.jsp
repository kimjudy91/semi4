<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<c:choose>
	<c:when test="${code=='success' }">
		<h1>요청작업을 수행완료했습니다.</h1>
	</c:when>
	<c:otherwise>
		<h1>오류로 인해 요청작업을 수행하지 못했습니다.</h1>
	</c:otherwise>
</c:choose>
</div>
</div>