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
<script type="text/javascript">
	function clearId(){
		var id=document.getElementById("id");
		if(id.value=="아이디"){
			id.style.color="black";
			id.value="";
			id.focus();
		}
	}
	function setId(){
		var id=document.getElementById("id");
		if(id.value==""){
			id.value="아이디";
			id.style.color="silver";
		}
	}
	function clearEmail(){
		var email=document.getElementById("email");
		if(email.value=="이메일"){
			email.style.color="black";
			email.value="";
			email.focus();
		}
	}
	function setEmail(){
		var email=document.getElementById("email");
		if(email.value==""){
			email.value="이메일";
			email.style.color="silver";
		}
	}

</script>
</div>
<div id="menus">
<form action="${cp }/findPwd" method="post" id="login"  >
<label>아이디</label><input type="text" id="id" name="id" onfocus="clearId()" onblur="setId()"><br>
<label>이메일</label><input type="text" id="email" name="email" onfocus="clearEmail()" onblur="setEmail()"><br>
<input type="submit" value="임시비밀번호 받기" id="pwdbtn">
</form>
</div>
</div>
</body>
</html>