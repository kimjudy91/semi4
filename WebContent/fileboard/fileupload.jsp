<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileupload.jsp</title>
</head>
<body>
<h1>파일업로드하기</h1>
<!-- 
    - 파일업로드할때는 반드시 
      method="post"
      enctype="multipart/form-data"
         설정해야 함
 -->
<form method="post" action="fileuploadOk.jsp" 
						enctype="multipart/form-data">
	작성자 <br>
	<input type="text" name="writer"><br>
	제목 <br>
	<input type="text" name="title"><br>
	내용 <br>
	<textarea rows="5" cols="50" name="content"></textarea><br>
	첨부파일 <input type="file" name="file1"><br>
	<input type="submit" value="전송">
	<input type="reset" value="취소">
</form>
</body>
</html>












