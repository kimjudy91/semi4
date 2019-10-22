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
<link rel="stylesheet" type="text/css" href="../css.min/listStyles.css">

<div id="nql">
<h1>문의게시판</h1>
<hr>
</div>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<table >
	<tr id="tr1"><th>글번호</th><th>작성자</th><th>제목</th><th>답변여부</th><th>작성일</th></tr>

		<c:forEach var="vo" items="${list}">
			<tr>
			<td>${vo.nquire_num}</td>
			<td>${vo.id}</td>	
		<c:choose>
			<c:when test="${sessionScope.id eq vo.id || sessionScope.id eq 'admin'}">
				<td><a href="${cp}/nquire/detail?nquire_num=${vo.nquire_num}">${vo.title}</a></td>
			</c:when>
			<c:otherwise>
				<td>${vo.title}</td>
			</c:otherwise>
		</c:choose>
			<c:if test="${vo.comments!=null }"><td>O</td></c:if><c:if test="${vo.comments==null }"><td>X</td></c:if>
			<td>${vo.r_date }</td>
			</tr>
		</c:forEach>
</table>



<!-- form -->
<div id="ff">
	<form method="get" action="${cp }/nquire/insert">
	<c:choose>
			<c:when test="${sessionScope.id!=null }">
				<input type="submit" id="nbtn" value="문의하기" id="inp">	
			</c:when>
			<c:otherwise>
				<input type="button" value="문의하기" id="nbtn" onclick="btn_nquire_alert_click1()">
			</c:otherwise>
		</c:choose>
		
	</form>
	<form method="get" action="${cp }/nquire/list">
		<input type="hidden" name="id" value="${sessionScope.id }">
		<c:choose>
			<c:when test="${sessionScope.id!=null }">
				<input type="submit" value="내문의내역" id="nbtn">
			</c:when>
			<c:otherwise>
				<input type="button" value="내문의내역" id="nbtn" onclick="btn_nquire_alert_click1()">
			</c:otherwise>
		</c:choose>
		
	</form>
<br>

<!-- 페이징 -->
<div id="paging"> 
<c:choose>
		<c:when test="${startPageNum>10 }">
			<a href="${cp }/nquire/list?pageNum=${startPageNum-1}">◀◀</a>
		</c:when>
</c:choose>
<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
	<c:choose>
		<c:when test="${pageNum==i }">
			<a href="${cp }/nquire/list?pageNum=${i}"><span style="color:blue;">${i }</span></a>
		</c:when>
		<c:otherwise>
			<a href="${cp }/nquire/list?pageNum=${i}"><span style="color:#555;">${i }</span></a>
		</c:otherwise>	
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${endPageNum<pageCount }">
		<a href="${cp }/nquire/list?pageNum=${endPageNum+1}">▶▶</a>
	</c:when>
</c:choose>
</div>
</div>
</body>
<script type="text/javascript">
	function btn_nquire_alert_click1(){
		if (confirm("로그인후 이용하실수 있습니다.") == true) { 
			location.href="${cp}/logins";
			} else { 
				return;
			}
	}

</script>
</html>