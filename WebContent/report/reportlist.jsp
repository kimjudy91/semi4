<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report/reportlist.jsp</title>
</head>
<body>
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<table border="1" width="500" >
	<tr>
		<th>신고한 아이디</th><th>답글달기</th><th>답여부</th>
	</tr>
	<c:forEach var="report" items="${listReport2 }">
	<tr>
			<td>${report.id }</td><td><a href="${cp }/report/comments?write_num=${report.write_num}&rnum=${report.rnum}">답글달기</a></td><c:if test="${report.comments!=null }"><td>V</td></c:if><c:if test="${report.comments==null }"><td>X</td></c:if>
	</tr>
	</c:forEach>
</table>

<!-- 페이징 영역 -->
<c:choose>
	<c:when test="${startPage>10}">
		<a href="${cp }/reportList?pageNum=${startPage-1 }">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
	<c:forEach	var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${cp }/reportList?pageNum=${i}">
				<span style='color:blue'>[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/reportList?pageNum=${i}"><span style='color:#999'>[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="${cp }/reportList?pageNum=${endPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		다음
	</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>