<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/likes/likes.jsp</title>
<style type="text/css">
	table{
		text-align: center; 
		font-size: 20px;
		width: 500px;
		
	}
	#TTOP{
		font-size: 13px;
		padding-bottom: 15px;
    	border-bottom: inherit;
	}
 	a{
 		text-decoration: none;
 	
 	}

</style>
</head>
<body>
<div id="TTOP">
<h1>좋아요TOP10</h1>
</div>

<table>
	<tr>
		<th>글번호</th><th>제목</th><th>아이디</th><th>좋아요</th>

	</tr>	
<c:forEach var="vo" items="${likeslist }">
	<tr>
	<c:choose>
				<c:when test="${sessionScope.id!=null }">
					<c:choose>
						<c:when test="${sessionScope.grade<2  }">
							<td><a href="javascript:gradepl()">${vo.write_num }</a></td>
						</c:when>
						<c:otherwise>
						<td><a href="${cp }/likes?write_num=${vo.write_num}">${vo.write_num }</a></td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
						<td><a href="javascript:loginpl();">${vo.write_num }</a></td>
				</c:otherwise>
			</c:choose>
		<td>${vo.p_title }</td><td>${vo.id }</td><td>${vo.likes }</td>
	</tr>
</c:forEach>
</table>

</body>
</html>