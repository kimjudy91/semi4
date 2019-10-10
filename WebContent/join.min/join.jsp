<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="../css.min/style.css"> 
</head>
<body>
<h1>회원가입</h1>
<hr>
<form method="post" action="${cp }/join/insert">
<div id="form">
<span>
	<label>이름</label><input type="text" name="name"><br>
	<label>아이디</label><input type="text" name="id" onkeyup="checkOk()">
	<span id="idcheck"></span><br>
	<label>비밀번호</label><input type="password" name="pwd1"><br>
	<label>비밀번호확인</label><input type="password" name="pwd2" onkeyup="checkOk()">
	<span id="pwdcheck"></span><br>
	<label>주민등록번호</label><input type="text" name="jumin"><br>
	<label>전화번호</label><input type="text" name="phone" onkeyup="checkOk()">
	<span id="phonecheck"></span><br>
	<label>주소</label><input type="text" name="address"><br>
	<label>이메일</label><input type="text" name="email" onkeyup="checkOk()">
	<span id="emailcheck"></span><br><br>
</span>

	<input type="submit" value="회원가입" id="a">
	<input type="reset" value="회원가입취소" id="a">
	</div>
</form>
<script type="text/javascript">
	var idxhr=null;
	function check(){
		idxhr=new XMLHttpRequest();
		var id=document.getElementById("id").value;
		if(id==""){
			document.getElementById("check").innerHTML="";
			return;
		}
		idxhr.onreadystatechange=idOk;
		idxhr.open('get','idOk.jsp?id=' + id, true);
		idxhr.send();
	}
	function checkOk(){
		if(idxhr.readyState==4 && idxhr.status==200){
			var data=idxhr.responseXML;
			var using=data.getElementsByTagName("using")[0].firstChild.nodeValue;
			var idspan=document.getElementById("idcheck");
			var pwdspan=document.getElementById("pwdcheck");
			var phonespan=document.getElementById("phonecheck");
			var emailspan=document.getElementById("emailcheck");
			if(using=='true'){
				idspan.innerHTML="사용중인 아이디입니다.";
			}else{
				idspan.innerHTML="사용가능한 아이디입니다.";
			}
			if(using=='true'){
				pwdspan.innerHTML="숫자,문자,특수문자(@,!)를 포함하여 입력해주세요.";
			}else if{
				pwdspan.innerHTML="비밀번호가 일치하지 않습니다.";
			}else{
				pwdspan.innerHTML="";
			}
			if(using=='true'){
				phonespan.innerHTML="사용중인 전화번호입니다.";
			}else{
				phonespan.innerHTML="사용가능한 전화번호입니다.";
			}
			if(using=='true'){
				emailspan.innerHTML="사용중인 이메일입니다.";
			}else{
				emailspan.innerHTML="사용가능한 이메일입니다.";
			}
		}	
	}
	

</script>
</body>
</html>