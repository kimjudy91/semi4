<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
</head>
<body>
<!-- <link rel="stylesheet" type="text/css" href="../css.min/style.css"> -->
</head>
<body>
<h1>회원가입</h1>
<hr>
<form name="frm" method="post" action="${pageContext.request.contextPath }/join/insert">
<div id="form">
<span>
	<label>이름</label><input type="text" name="name"><br>
	<label>아이디</label><input type="text" name="id"  id="id" onkeyup="idcheck()"><br>
<<<<<<< HEAD
	<span id="idcheck"></span><br> 
	<label>비밀번호</label><input type="password" name="pwd1" id="pwd1" onkeyup="pwdcheck()"><br>
	<label>비밀번호확인</label><input type="password" name="pwd2" id="pwd2" onkeyup="pwdcheck()"><br>
=======
	<span id="idcheck"></span><br>
	<label>비밀번호</label><input type="password" name="pwd" id="pwd1" onclick="pwdcheck()"><br>
	<span id="c">숫자(5자이상),문자(하나이상),특수문자(@또는!)를 포함하여 입력해주세요.</span><br>
	<label>비밀번호확인</label><input type="password" name="pwd" id="pwd2" onclick="pwdcheck()"><br>
>>>>>>> branch 'master' of https://github.com/kimjudy91/semi4.git
	<span id="pwdcheck"></span><br>
<!--	<label>주민등록번호</label><input type="text" name="jumin" id="j6" onclick="jumincheck()">-<iput type="text" name="jumin" id="j7" onclick="jumincheck()"><br>
	<span id="jumincheck"></span><br>  -->
	<label>전화번호</label><input type="text" name="phone" id="phone" onclick="phonecheck()">
<!---<input type="text" name="phone" id="p2">
	-<input type="text" name="phone" id="p3" onclick="phonecheck()">  -->	
	<span id="phonecheck"></span><br>
	<label>주소</label><input type="text" name="address"><br>
	<label>이메일</label><input type="text" name="email" id="email" onclick="emailcheck()"><br>
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
	
	//비밀번호 유효성검사
	function pwdcheck(){
		var pwd=document.frm.pwd;
		var jumin=document.frm.jumin;
		var pwd1=document.getElementById("pwd1");
		var pwd2=document.getElementById("pwd2");
		var pwdspan=document.getElementById("pwdcheck");	
		
		if(pwd1.value.length != ""){
			pwdspan.innerHTML="필수 정보입니다";
		}else if(pwd.value.length == ""){
			pwdspan.style.color="red";
			pwdspan.innerHTML="필수 정보입니다";
			pwd1.focus();
			return false;
		}
		//비밀번호 길이
		if(pwd1.value.length>=3 && pwd1.value.length<=8){
			pwdspan.innerHTML="";
		}else if(pwd1.value.length<3 || pwd1.value.length>7){
			pwdspan.style.color="red";
			pwdspan.innerHTML="비밀번호를 3~7자까지 입력해주세요."
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
				pwdspan.innerHTML =cnt + "문자(하나이상)포함해서 입력해주세요.";		
				pwd1.focus();
				return false;
			}
			if(cnt2 == 0){
				pwdspan.style.color = "red";
				pwdspan.innerHTML = cnt2 + "특수문자(@또는!)를 포함하여 입력해주세요.";	
				pwd1.focus();
				return false;	
			}		
		}	
		if(pwd2.value!=""){ 
			if (pwd1.value != pwd2.value) {
				pwdspan.style.color = "red";
				pwdspan.innerHTML = "비밀번호가 일치하지 않습니다.";
			}else{
				pwdspan.style.color = "blue";
				pwdspan.innerHTML = "비밀번호가 일치합니다.";
			}			
		}
	}
	
/*	function jumincheck(){
		var jumin=document.frm.jumin;
		var phone=document.frm.phone;
		var j6=document.getElementById("j6");
		var j7=document.getElementById("j7");
		var jspan=document.getElementById("jumincheck");
		
		if(jumin.value.length != ""){
			jspan.innerHTML="";
		}else if(jumin.value.length == ""){
			jspan.style.color="red";
			jspan.innerHTML="필수 정보입니다";
			j6.focus();
			return false;
		}
		
		if(jumin.value.length!=13){
			jspan.style.color = "red";
			jspan.innerHTML = "주민등록번호를 올바르게 입력해주세요.";
			jumin.focus();
			return false;
		} 
	}
*/
	
	//전화번호 검사
	var pxhr=null;
	function phonecheck(){
		var pspan=document.getElementById("phonecheck");
		pxhr=new XMLHttpRequest();
		var phone=document.getElementById("phone").value;
		var pspan=document.getElementById("phonecheck");
		
		if(phone==""){
			document.getElementById("phonecheck").innerHTML="";
		}
		
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
			pspan.innerHTML="(-)포함해서 올바르게 입력해주세요 예)010-0000-0000";
			return false;
		}else if(!(ph >= '0' && ph <= '9')){
			pspan.style.color="red";
			pspan.innerHTML="올바른 번호를 입력하세요.";
			return false;
		}else{
			pspan.innerHTML="";
			return true;
		}
		
		pxhr.onreadystatechange=phoneOk;
		pxhr.open('get','phoneOk.jsp?phone=' + phone, true);
		pxhr.send();		
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
	
	
	
	// email중복검사
	var exhr=null;
	function emailcheck(){
		var espan=document.getElementById("emailcheck");
		exhr=new XMLHttpRequest();
		var email=document.getElementById("email").value;
		var espan=document.getElementById("emailcheck");
		
		if(email==""){
			document.getElementById("emailcheck").innerHTML="";
		}
				
		var cnt=0;
		if(email.length == ""){
			espan.style.color="red";
			espan.innerHTML="필수 정보입니다";
			return false;
		}
		for(var i=0; i<email.length;i++){
			var e=email.charAt(i);
			if(e== '@' || e == '!'){
				cnt++;		
			}				
		}
		if(cnt == 0){
			espan.style.color = "red";
			espan.innerHTML= "이메일이 올바른 형태가 아닙니다.";
			return false;
		}else{
			espan.innerHTML= "";
			return true;
		}
		
		
		
		exhr.onreadystatechange=emailOk;
		exhr.open('get','emailOk.jsp?email=' + email, true);
		exhr.send();	
		
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