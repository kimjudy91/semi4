<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/comments</title>
</head>

<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<h1>댓글 달기</h1>
<form method="post" action="${cp }/board/community">

	<!-- 답글인 경우 부모글에 대한 정보 보내기 -->
	<input type="hidden" name="num" value="${vo.write_num }">
	<input type="hidden" name="ref" value="${vo.ref }">
	<input type="hidden" name="lev" value="${vo.lev }">
	<input type="hidden" name="step" value="${vo.step }">


	글번호 <br>
	<input type="text" name="write_num" value="${vo.write_num}" readonly="readonly">
	<br>작성자<br>
	<input type="text" name="id" value="${vo.id}" readonly="readonly"><br>
	제목<br>
	<input type="text" name="p_title" value="${vo.p_title}"><br>
	내용<br>
	<textarea rows="5" cols="60" name="contents" >${vo.contents}</textarea><Br>
	<input type="submit" value="등록">	
</form>
</body>
</html>