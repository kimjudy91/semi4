<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPage.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/myPage/myPage.css">
</head>
<script type="text/javascript">
	function updateEmail1(){
		var updateEmail=document.getElementById("updateEmail");
		updateEmail.style.display="none";
		var okEmail=document.getElementById("okEmail");
		okEmail.style.display="inline";
		var email=document.getElementById("email");
		email.readOnly=false;
	}
	function updateEmail2(){
		var updateEmail=document.getElementById("updateEmail");
		updateEmail.style.display="inline";
		var okEmail=document.getElementById("okEmail");
		okEmail.style.display="none";
		var email=document.getElementById("email");
		email.readOnly=true;
	}
	function updatePhone1(){
		var updatePhone=document.getElementById("updatePhone");
		updatePhone.style.display="none";
		var okPhone=document.getElementById("okPhone");
		okPhone.style.display="inline";
		var phone=document.getElementById("phone");
		phone.readOnly=false;
	}
	function updatePhone2(){
		var updatePhone=document.getElementById("updatePhone");
		updatePhone.style.display="inline";
		var okPhone=document.getElementById("okPhone");
		okPhone.style.display="none";
		var phone=document.getElementById("phone");
		phone.readOnly=true;
	}
	function updateAddress1(){
		var updateAddress=document.getElementById("updateAddress");
		updateAddress.style.display="none";
		var okAddress=document.getElementById("okAddress");
		okAddress.style.display="inline";
		var address=document.getElementById("address");
		address.readOnly=false;
	}
	function updateAddress2(){
		var updateAddress=document.getElementById("updateAddress");
		updateAddress.style.display="inline";
		var okAddress=document.getElementById("okAddress");
		okAddress.style.display="none";
		var address=document.getElementById("address");
		address.readOnly=true;
	}
</script>
<body>
<h1>마이페이지</h1>

<div id="all">
<!-- 정보수정 -->
<div id="set">
<h2>정보수정</h2>
<form action="${cp }/updateMembers" method="post">
<table>
<tr><th>이메일</th><td><input type="text" value="${vo.email }" readonly="readonly" id="email" name="email"><input type="button" value="저장" onclick="updateEmail2()" style="display: none;" id="okEmail"><input type="button" value="수정" onclick="updateEmail1()" id="updateEmail"></td></tr>
<tr><th>전화번호 </th><td><input type="text" value="${vo.phone }" readonly="readonly" id="phone" name="phone"><input type="button" value="저장" onclick="updatePhone2()" style="display: none;" id="okPhone"><input type="button" value="수정" onclick="updatePhone1()" id="updatePhone"></td></tr>
<tr><th>주소  </th><td><input type="text" value="${vo.address }"  readonly="readonly" id="address" name="address"><input type="button" value="저장" onclick="updateAddress2()" style="display: none;" id="okAddress"><input type="button" value="수정" onclick="updateAddress1()" id="updateAddress"></td></tr>
<tr><th>등급</th>
<td style="text-align: left;">
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
</td></tr>
<tr><th colspan="2" id="sav">
<input type="submit" value="저장하기">
</th></tr>
</table>
</form>
</div>

<!-- 신고내역 -->
<div id="rep2">
<c:if test="${rlist!=null }">
<h2>신고내역</h2>
<div id="aa">
<c:forEach var="aa" items="${rlist }">
	신고내용:${aa.report_content }<br>
	답변내용:<c:if test="${aa.comments==null }">X</c:if><c:if test="${aa.comments!=null }">${aa.comments }</c:if>
	<hr>
</c:forEach>
</div>
</c:if>
</div>
</div>
</body>
</html>