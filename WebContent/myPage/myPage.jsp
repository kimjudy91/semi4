<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPage.jsp</title>
</head>
<script type="text/javascript">
	function updateEmail1(){
		var updateEmail=document.getElementById("updateEmail");
		updateEmail.style.display="none";
		var okEmail=document.getElementById("okEmail");
		okEmail.style.display="inline";
		var email=document.getElementById("email");
		email.disabled=false;
	}
	function updateEmail2(){
		var updateEmail=document.getElementById("updateEmail");
		updateEmail.style.display="inline";
		var okEmail=document.getElementById("okEmail");
		okEmail.style.display="none";
		var email=document.getElementById("email");
		email.disabled=true;
	}
	function updatePhone1(){
		var updatePhone=document.getElementById("updatePhone");
		updatePhone.style.display="none";
		var okPhone=document.getElementById("okPhone");
		okPhone.style.display="inline";
		var phone=document.getElementById("phone");
		phone.disabled=false;
	}
	function updatePhone2(){
		var updatePhone=document.getElementById("updatePhone");
		updatePhone.style.display="inline";
		var okPhone=document.getElementById("okPhone");
		okPhone.style.display="none";
		var phone=document.getElementById("phone");
		phone.disabled=true;
	}
	function updateAddress1(){
		var updateAddress=document.getElementById("updateAddress");
		updateAddress.style.display="none";
		var okAddress=document.getElementById("okAddress");
		okAddress.style.display="inline";
		var address=document.getElementById("address");
		address.disabled=false;
	}
	function updateAddress2(){
		var updateAddress=document.getElementById("updateAddress");
		updateAddress.style.display="inline";
		var okAddress=document.getElementById("okAddress");
		okAddress.style.display="none";
		var address=document.getElementById("address");
		address.disabled=true;
	}
</script>
<body>
<h1>마이페이지</h1>
이메일   <input type="text" value="${vo.email }" disabled="disabled" id="email"><input type="button" value="저장" onclick="updateEmail2()" style="display: none;" id="okEmail"><input type="button" value="수정" onclick="updateEmail1()" id="updateEmail"><br>
전화번호   <input type="text" value="${vo.phone }" disabled="disabled" id="phone"><input type="button" value="저장" onclick="updatePhone2()" style="display: none;" id="okPhone"><input type="button" value="수정" onclick="updatePhone1()" id="updatePhone"><br>
주소   <input type="text" value="${vo.address }" disabled="disabled" id="address"><input type="button" value="저장" onclick="updateAddress2()" style="display: none;" id="okAddress"><input type="button" value="수정" onclick="updateAddress1()" id="updateAddress"><br>
등급
<c:choose>
	<c:when test="${vo.grade=='1' }">
	<img  src="${cp }/images/bronze.png">
	</c:when>
	<c:when test="${vo.grade=='2' }">
	<img  src="${cp }/images/silver.png">
	</c:when>
	<c:when test="${vo.grade=='3' }">
	<img  src="${cp }/images/gold.png">
	</c:when>
</c:choose>
<br>
</body>
</html>