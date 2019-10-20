<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findId</title>
<link rel="stylesheet" type="text/css" href="${cp }/findId/findIdCss.css">
<script type="text/javascript">
	function clearPhone(){
		var phone=document.getElementById("phone");
		if(phone.value=="전화번호"){
			phone.style.color="black";
			phone.value="";
			phone.focus();
		}
	}
	function setPhone(){
		var phone=document.getElementById("phone");
		if(phone.value==""){
			phone.value="전화번호";
			phone.style.color="silver";
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
</head>
<body>
<div id="main">
<div id="header">
<h1>ID SEARCH</h1>
</div>
<form method="post" action="${cp }/findId" id="findId">
<input type="text" value="전화번호" id="phone" name="phone" onclick="clearPhone()" onblur="setPhone()" style="color:silver;"><br>
<input type="text" value="이메일" id="email" name="email" onclick="clearEmail()" onblur="setEmail()" style="color:silver;"><br>
<c:if test="${errMsg!=null}">
<span style="color:red;font-size: 12px; " id="errMsg">아이디가 존재하지 않습니다.</span><br>
</c:if>
<input type="submit" value="아이디 찾기" id="findIdBtn">
</form>
</div>
</body>
</html>