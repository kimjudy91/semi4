<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/update.jsp</title>
</head>
<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<div>
<h1>글수정하기</h1>
<form method="post" action="${cp }/fileboard/update" enctype="multipart/form-data">

	글번호 <input type="text" name="write_num" value="${vo.write_num}" readonly="readonly">

	조회수 <input type="text" name="views" value="${vo.views }" readonly="readonly">
	<br>
	작성자<br>
	<input type="text" name="id" value="${vo.id}" readonly="readonly"><br>
	제목<br>
	<input type="text" name="p_title" value="${vo.p_title}"><br>
	내용<br>
	<textarea rows="5" cols="60" name="contents" >${vo.contents}</textarea><Br>
	<input type="hidden" name="f_num" value="${vo.f_num }">
	<tr>
		<th>다운로드 파일</th>
		<td>
	<a href="${cp}/fileboard.download?f_num=${vo.f_num} ">다운로드</a>
		</td>
	</tr>

	수정할파일<input type="file" name="file"><br>
	<input type="submit" value="저장">
	<input type="reset" value="취소">
	
	
</form>
</div>
</body>
</html>