<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- html에 삽입될꺼니까 다 없앰 -->
<div>
<h1>글등록하기</h1>
<form method="post" action="${cp }/fileboard/insert" enctype="multipart/form-data">
	작성자<br>
	<input type="text" name="id"><br>
	제목<br>
	<input type="text" name="p_title"><br>
	<label><input type="radio" name="trans" value="rnb">알앤비</label>
	<label><input type="radio" name="trans" value="pop">팝</label>
	<label><input type="radio" name="trans" value="oldsong">가요</label><br>
	내용<br>
	<textarea rows="5" cols="60" name="contents"></textarea><Br>
	첨부파일 <input type="file" name="file1"><br>
	<input type="submit" value="등록">	
</form>
</div>