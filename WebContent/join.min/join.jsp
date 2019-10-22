<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/css.min/style.css">
</head>
<body>
</head>
<body>
<div id="join">
<h1>회원가입</h1>
<hr>
</div>

<form name="frm" id="jform" onsubmit="return checkForm();" method="post"   action="${pageContext.request.contextPath }/join/insert">
<table>
	<tr><th>이름</th><td><input type="text" name="name" id="name" onkeyup="namecheck()"></td></tr>
	<tr><th></th><td id="namespan"></td></tr>
	<tr><th>아이디</th><td><input type="text" name="id"  id="id" onkeyup="idcheck()" ></td></tr>
	<tr><th style="height: 25px;"></th><td id="idcheck"></td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="pwd" id="pwd1" onkeyup="pwdcheck()"></td></tr>
	<tr><th></th><td style="font-style: oblique;">3자이상,영문 대소문자,특수문자(@또는!)를 포함하여 입력해주세요.</td></tr>
	<tr><th>비밀번호확인</th><td><input type="password" name="pwd" id="pwd2" onkeyup="pwdcheck()"></td></tr>
	<tr><th style="height: 25px;"></th><td id="pwdcheck" colspan='2'></td></tr>
	<tr><th>주민등록번호</th><td><input type="text" name="jumin" id="j6" onkeyup="jumincheck()">
	 -&nbsp<input type="text" name="jumin1" id="j1" onkeyup="jumincheck()">xxxxxx
	 <tr><th></th><td style="font-style: oblique;">예) 123456-1xxxxxx</td></tr>
	<tr><th style="height: 25px;"></th><td id="jumincheck"></td></tr> 
	<tr><th>전화번호</th><td><input type="text" name="phone" id="phone" onkeyup="phonecheck()"></td></tr>
	<tr><th></th><td style="font-style: oblique;">예) 010-0000-0000</td></tr>
	<tr><th style="height: 25px;"></th><td id="phonecheck"></td></tr>
	<tr><th>주소</th><td><input type="text" name="address" id="addr" onkeyup="addrcheck()"></td></tr>
	<tr><th style="height: 25px;"></th><td id="addrcheck"></td></tr>
	<tr><th>이메일</th><td><input type="text" name="email" id="email" onkeyup="emailcheck()"></td></tr>
	<tr><th style="height: 25px;"></th><td id="emailcheck"></td></tr>
	<tr><th id="favjanre">좋아하는 음악장르</th>
	<td><select name="janre" id="janre" size="1" style="height: 25px; width: 60px;">
		<option selected>선택</option>
		<option value="1" id="Rock">Rock</option>
		<option value="2" id="folk">folk</option>
		<option value="3" id="RB">RB</option>
	</select></td></tr>
	<tr><th colspan='2' style="text-align: center;" id="jbtn"><input type="submit" id="subBtn" value="회원가입">
	<a href="${cp }/logins"><input type="button" id="canBtn" value="회원가입취소" ></a></th></tr>
</table>
</form>


<script type="text/javascript">
	
	function checkForm(){
		form=document.frm;

		
		if(form.name.value == "" || form.id.value == "" || form.pwd.value == "" || form.jumin.value == "" 
				|| form.phone.value == "" || form.email.value == "" || form.janre.value == ""){
			if(form.name.value == ""){
				alert("이름 입력해주세요.");
				form.name.focus();
				return false;
			}else if(form.id.value == ""){
				alert("아이디 입력해주세요.");
				form.id.focus();
				return false;
			}else if(form.pwd1.value == "" || form.pwd2.value == ""){
				alert("비밀번호를 입력해주세요.");
				return false;
			}else if(form.j6.value == ""){
				alert("주민등록번호를 입력해주세요.");
				return false;
			}else if(form.j1.value == ""){
				alert("주민등록번호 뒷자리를 입력해주세요.");
				return false;
			}else if(form.phone.value == ""){
				alert("전화번호를 입력해주세요.");
				return false;
			}else if(form.email.value == ""){
				alert("email을 입력해주세요.");
				return false;			
			}else if(form.janre.selectedIndex<1){
				alert("장르를 선택해주세요.");
				return false;
			}
		}
	}
	
	
	function namecheck(){
		var name=document.getElementById("name");
		var nspan=document.getElementById("namespan");
		
		if(name.value.length != ""){
			nspan.innerHTML="";
		}else if(name.value.length == ""){
			nspan.style.color="red";
			nspan.innerHTML="필수 정보입니다";
		}
	}

	// 아이디유효성 검사
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
	
	//비밀번호 유효성검사
	function pwdcheck(){
		var pwd=document.frm.pwd;
		var pwd1=document.getElementById("pwd1");
		var pwd2=document.getElementById("pwd2");
		var pwdspan=document.getElementById("pwdcheck");	
		
		if(pwd1.value.length != ""){
			pwdspan.innerHTML="";
		}else if(pwd.value.length == ""){
			pwdspan.style.color="red";
			pwdspan.innerHTML="필수 정보입니다";
			pwd1.focus();
			return false;
		}
		//비밀번호 길이
		if(pwd1.value.length>=3){
			pwdspan.innerHTML="";
		}else if(pwd1.value.length<3){
			pwdspan.style.color="red";
			pwdspan.innerHTML="비밀번호를 3자이상 입력해주세요."
			pwd1.focus();
			return false;
		}	
		//소문자,대문자,특수문자(@또는!)하나 이상
		if (pwd1.value.length > 0) {
			var cnt =0;
			var cnt2 =0;
			for (var i = 0; i < pwd1.value.length; i++) {
				var ch = pwd1.value.charAt(i);			
			
				if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z')){
					cnt++;		
				}				
				if (ch == '@' || ch == '!') {
					cnt2++;					
				}			
			}

			if(cnt==0){
				pwdspan.style.color = "red";
				pwdspan.innerHTML ="영문 대소문자,특수문자(@또는!)를 포함하여 입력해주세요.";		
				pwd1.focus();
				return false;
			}
			if(cnt2 == 0){
				pwdspan.style.color = "red";
				pwdspan.innerHTML = "특수문자(@또는!)를 포함하여 입력해주세요.";	
				pwd1.focus();
				return false;	
			}		
		}	
		if(pwd2.value!=""){ 
			if (pwd1.value != pwd2.value) {
				pwdspan.style.color = "red";
				pwdspan.innerHTML = "비밀번호가 일치하지 않습니다.";
				return false;
			}else{
				pwdspan.style.color = "blue";
				pwdspan.innerHTML = "비밀번호가 일치합니다.";
				return true;
			}			
		}
	}
	
	// 주민유효성검사
	function jumincheck(){
		var jumin=document.frm.jumin;
		var j6=document.getElementById("j6");
		var j1=document.getElementById("j1");
		var jspan=document.getElementById("jumincheck");
		
		if(jumin.value.length != ""){
			jspan.innerHTML="";
		}else if(jumin.value.length == ""){
			jspan.style.color="red";
			document.getElementById("jumincheck").innerHTML="필수 정보입니다";
		}
			
		if(j6.value.length !=6){
			jspan.style.color="red";
			jspan.innerHTML="생년월일6자리와 뒷자리 하나만 입력해주세요.";
			j6.focus();
			return false;
		}
		if(j1.value.length !=1){
			jspan.style.color="red";
			jspan.innerHTML="뒷자리 하나만 입력해주세요.";
			j1.focus();
			return false;
		}else{
			jspan.innerHTML="";
			return true;
		}
	}

	
	//전화번호 검사
	var pxhr=null;
	function phonecheck(){
		var pspan=document.getElementById("phonecheck");
		pxhr=new XMLHttpRequest();
		var phone=document.getElementById("phone").value;
		var pspan=document.getElementById("phonecheck");
		
		if(phone==""){
			pspan.innerHTML="";
		}
		pxhr.onreadystatechange=phoneOk;
		pxhr.open('get','phoneOk.jsp?phone=' + phone, true);
		pxhr.send();		
		
		var cnt=0;
		if(phone.length == ""){
			pspan.style.color="red";
			pspan.innerHTML="필수 정보입니다";
			return false;
		}
		for(var i=0; i<phone.length;i++){
			var ph=phone.charAt(i);		
			if(ph=="-"){
				cnt++;
			}		
		}		
		if(!(cnt==2)){
			pspan.style.color="red";
			pspan.innerHTML="(-)포함해서 올바르게 입력해주세요.";
			return false;
		}else if(!(ph >= '0' && ph <= '9')){
			pspan.style.color="red";
			pspan.innerHTML="올바른 번호를 입력하세요.";
			return false;
		}else{
			pspan.innerHTML="";
			return true;
		}
		
		
		
	}

	function phoneOk(){
		if(pxhr.readyState==4 && pxhr.status==200){
			var data=pxhr.responseXML;
			var check=data.getElementsByTagName("check")[0].firstChild.nodeValue;
			var pspan=document.getElementById("phonecheck");
			if(check=='true'){
				pspan.style.color="red";
				pspan.innerHTML="사용중인 전화번호 입니다.";
			}
		}	
	}
	
	// 주소
	function addrcheck(){
		var addr=document.getElementById("addr");
		var aspan=document.getElementById("addrcheck");
		if(addr==""){
			aspan.innerHTML="";
		}else{
			aspan.style.color="green";
			aspan.innerHTML="선택사항입니다.";
			addr.focus();
			return false;
		}
	}
	// email중복검사
	var exhr=null;
	function emailcheck(){
		var espan=document.getElementById("emailcheck");
		exhr=new XMLHttpRequest();
		var email=document.getElementById("email").value;
		var espan=document.getElementById("emailcheck");
		
		if(email==""){
			document.getElementById("emailcheck").innerHTML="";
			return;
		}
		exhr.onreadystatechange=emailOk;
		exhr.open('get','emailOk.jsp?email=' + email, true);
		exhr.send();	
				
		var cnt=0;
		var cnt2=0;
		if(email.length == ""){
			espan.style.color="red";
			espan.innerHTML="필수 정보입니다";
			return false;
		}
		for(var i=0; i<email.length;i++){
			var e=email.charAt(i);
			if(e== '@' ){
				cnt++;		
			}	
			if(e == '.'){
				cnt2++;		
			}		
		}
		if(cnt == 0){
			espan.style.color = "red";
			espan.innerHTML= "이메일이 올바른 형태가 아닙니다.";
			return false;
		}else if(cnt2 == 0){
			espan.style.color = "red";
			espan.innerHTML= "이메일이 올바른 형태가 아닙니다.";
			return false;
		}else{
			espan.innerHTML= "";
			return true;
		}	
		
		
	}
	
	function emailOk(){
		if(exhr.readyState==4 && exhr.status==200){
			var data=exhr.responseXML;
			var check=data.getElementsByTagName("check")[0].firstChild.nodeValue;
			var espan=document.getElementById("emailcheck");
			if(check=='true'){
				espan.style.color="red";
				espan.innerHTML="사용중인 email 입니다.";
			}
		}		
	}
	
	
</script>
</body>
</html>