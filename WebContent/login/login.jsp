<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/login/loginCss.css">
</head>
<script type="text/javascript">
	function clearId(){
		var id=document.getElementById("id");
		if(id.value=="아이디"){
			id.style.color="black";
			id.value="";
			id.focus();
		}
	}
	function clearPwd(){
		var pwd=document.getElementById("pwd");
		pwd.type="password";
		if(pwd.value=="비밀번호"){
			pwd.style.color="black";
			pwd.value="";
			pwd.focus();	
		}
	}
	function setId(){
		var id=document.getElementById("id");
		if(id.value==""){
			id.value="아이디";
			id.style.color="silver";
		}
	}
	function setPwd(){
		var pwd=document.getElementById("pwd");
		if(pwd.value==""){
			pwd.type="text";
			pwd.value="비밀번호";
			pwd.style.color="silver";
		}
	}
</script>
<body>
<div id="main">
<div id="header">
<h1>LOGIN</h1>
</div>
<form method="post" action="${cp }/logins" id="login">
<input type="text" value="아이디" id="id" name="id" onfocus="clearId()" onblur="setId()" style="color:silver;"><br>
<input type="text" value="비밀번호" id="pwd" name="pwd" onfocus="clearPwd()" onblur="setPwd()" style="color:silver;"><br>
<c:if test="${errMsg!=null}">
<span style="color:red;font-size: 12px; " id="errMsg">아이디 또는 비밀번호가 잘못되었습니다.</span><br>
</c:if>
<input type="submit" value="로그인" id="loginbtn">
</form>
<div id="menus">
<a href="${cp }/findId" class="menu">아이디 찾기</a>
<a href="${cp }/findPwd" class="menu">비밀번호 찾기</a>
<a href="${cp}/join/insert" class="menu">회원가입</a>
</div>
</div>
</body>
</html>