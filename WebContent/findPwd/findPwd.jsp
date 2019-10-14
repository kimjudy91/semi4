<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findPwd.jsp</title>
</head>
<body>
<form action="${cp }/findPwd" method="post">
아이디 <input type="text" name="id"><br>
이메일<input type="text" name="email"><br>
<input type="submit" value="임시비밀번호 받기">
</form>
</body>
</html>