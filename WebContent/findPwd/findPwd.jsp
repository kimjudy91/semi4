<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findPwd.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/findPwd/findPwd.css">
</head>
<body>
<div id="main">
<div id="header">
<h1>PASSWORD SEARCH</h1>
</div>
<div id="menus">
<form action="${cp }/findPwd" method="post" id="login">
<label>아이디</label><input type="text" id="id" name="id"><br>
<label>이메일</label><input type="text" id="email" name="email"><br>
<input type="submit" value="임시비밀번호 받기" id="pwdbtn">
</form>
</div>
</div>
</body>
</html>