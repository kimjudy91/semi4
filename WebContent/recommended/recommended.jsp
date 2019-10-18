<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/recommended/recommended.jsp</title>
</head>

<h1 style="font-size: 1px;">추천장르TOP3(Rock)</h1>


<table border="1" width="200" style="text-align: center; font-size: 1px;" >
	<tr>
		<th>글번호</th><th>장르</th><th>좋아요</th>
	
	</tr>

<c:forEach var="vo" items="${recommended}">

	<tr>
		<td>${vo.write_num }</td>
		
			<td>
		<c:choose>
			<c:when test="${vo.genre_num=='1' }">
					Rock
			</c:when>
			<c:when test="${vo.genre_num=='2' }">
				    Folk
			</c:when>
			<c:when test="${vo.genre_num=='3' }">
				    RB
			</c:when>
		</c:choose>	
	
		</td>
		<td>${vo.likes }</td>
	</tr>
</c:forEach>
</table>

</html>