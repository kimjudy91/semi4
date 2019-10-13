<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
</head>
<body>
<!-- <link rel="stylesheet" type="text/css" href="../css.min/style.css">  -->
</head>
<body>
<h1>회원가입</h1>
<hr>
<form method="post" action="${pageContext.request.contextPath }/join/insert">
<div id="form">
<span>
	<label>이름</label><input type="text" name="name"><br>
	<label>아이디</label><input type="text" name="id"  id="id" onkeyup="idcheck()"><br>
	<span id="idcheck"></span><br> 
	<label>비밀번호</label><input type="password" name="pwd1" id="pwd1" onkeyup="pwdcheck()"><br>
	<label>비밀번호확인</label><input type="password" name="pwd2" id="pwd2" onkeyup="pwdcheck()"><br>
	<span id="pwdcheck"></span><br>
	<label>주민등록번호</label><input type="text" name="jumin"><br>
	<label>전화번호</label><input type="text" name="phone" onkeyup="phonecheck()">
	<span id="phonecheck"></span><br>
	<label>주소</label><input type="text" name="address"><br>
	<label>이메일</label><input type="text" name="email" onkeyup="emailcheck()">
	<span id="emailcheck"></span><br><br>
	<span id="fav">
	<label id="favjanre">좋아하는 음악장르</label>
	<input type="checkbox" id="box1" name="favm" value="1">Rock
	<input type="checkbox" id="box2" name="favm" value="2">folk
	<input type="checkbox" id="box3" name="favm" value="3">R&B<br>
	<input type="submit" value="회원가입">
	<input type="reset" value="회원가입취소">
	</span>
</span>
</div>
</form>
<script type="text/javascript">
	var idxhr=null;
	function idcheck(){
		idxhr=new XMLHttpRequest();
		var id=document.getElementById("id").value;
		if(id==""){
			document.getElementById("idcheck").innerHTML="";
			return;
		}
		idxhr.onreadystatechange=idOk;
		idxhr.open('get','idOk.jsp?id=' + id, true);
		idxhr.send();
	}
	function idOk(){
		if(idxhr.readyState==4 && idxhr.status==200){
			var data=idxhr.responseXML;
			var check=data.getElementsByTagName("check")[0].firstChild.nodeValue;
			var idspan=document.getElementById("idcheck");				
			if(check=='true'){
				idspan.style.color="red";
				idspan.innerHTML="사용중인 아이디입니다.";
			}else{
				idspan.style.color="blue";
				idspan.innerHTML="사용가능한 아이디입니다.";
			}				
		}	
	}
	function pwdcheck(){
		var pwd1=document.getElementById("pwd1").value;
		var pwd2=document.getElementById("pwd2").value;
		var pwdspan=document.getElementById("pwdcheck");	
			

		
		if (pwd1.length > 5) {
			var cnt = 0;
			for (var i = 0; i < pwd1.length; i++) {
				var pp = pwd1.charAt(i);
				if (pp == '@' || pp == '!')
					cnt++;
				}		
				if(cnt == 0){
					pwdspan.style.color = "red";
					pwdspan.innerHTML = pp + " 숫자(5자이상),문자,특수문자(@또는!)를 포함하여 입력해주세요.";
					}else{
						pwdspan.innerHTML = "";
					}
				
				}
			if(pwd2!=""){
				if (pwd1 != pwd2) {
						pwdspan.style.color = "red";
						pwdspan.innerHTML = "비밀번호가 일치하지 않습니다.";
					}else{
						pwdspan.style.color = "blue";
						pwdspan.innerHTML = "비밀번호가 일치합니다.";
				}			
			}
		}		

		if (pwd1 == "") {
			pwdspan.innerHTML = "";
		}
	

	var pxhr = null;
	function phonecheck() {
		pxhr = new XMLHttpRequest();
		var phone = document.getElementById("phone").value;
		pwdxhr.onreadystatechange = phoneOk;
		pwdxhr.open('get', 'phoneOk.jsp?phone=' + phone, true);
		pwdxhr.send();
	}

	function phoneOk() {
		if (pxhr.readyState == 4 && pxhr.status == 200) {
			var phonespan = document.getElementById("phonecheck");
			if (using == 'true') {
				phonespan.innerHTML = "사용중인 전화번호입니다.";
			} else {
				phonespan.innerHTML = "사용가능한 전화번호입니다.";
			}
		}
	}

	var exhr = null;
	function emailcheck() {
		exhr = new XMLHttpRequest();
		exhr.onreadystatechange = emailOk;
		exhr.open('get', 'emailOk.jsp?email=' + email, true);
		exhr.send();
	}
	function emailOk() {
		if (exhr.readyState == 4 && exhr.status == 200) {
			var emailspan = document.getElementById("emailcheck");
			if (using == 'true') {
				emailspan.innerHTML = "사용중인 이메일입니다.";
			} else {
				emailspan.innerHTML = "사용가능한 이메일입니다.";
			}
		}
	}
</script>
</body>
</html>