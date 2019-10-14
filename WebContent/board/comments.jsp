<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/comments</title>
</head>

<body>
<h1>댓글 달기</h1>
<c:set var="cp" value="${pageContext.request.contextPath }"/>

<form method="post" action="${cp }/board/community">
	글번호 <br>
	<input type="text" name="write_num" value="${vo.write_num}" readonly="readonly">
	<br>작성자<br>
	<input type="text" name="id" value="${vo.id}" readonly="readonly"><br>
	제목<br>
	<input type="text" name="p_title" value="${vo.p_title}" readonly="readonly"><br>
	내용<br>
	<textarea rows="5" cols="60" name="contents" >${vo.contents}</textarea><Br>
	<input type="submit" value="등록">	
</form>
</body>
</html>-->