<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/members_management/members_management.jsp</title>
<style type="text/css">

	table{width: 100%; text-align: center;}
	   #divPaging {clear:both;  margin:0 auto; width:220px; height:50px;}
	   #divPaging > div {float:left; width: 30px; margin:0 auto; margin-top: 30px;text-align:center;}
       #divSearchOption {clear:both; }
       #divSearchOption > div {float:left; margin:0 auto; margin-top: 30px; width:auto; height:100px; text-align:center;}
		.btn{ text-decoration: none; font-size: 1rem; color: #aaa; padding: 10px 10px 10px 10px; border-radius: 10px; }
		.btn1{ text-decoration: none; font-size: 1rem; color: #aaa; padding: 10px 10px 10px 10px; border-radius: 10px; }

</style>
</head>
<c:set var="cp" value="${cp }"/>
<script type="text/javascript">
	function showMsg1(id,g){
		var grade="";
		if(g==1){
			grade="브론즈";
		}else if(g==2){
			grade="실버";
		}else if(g==3){
			grade="골드";
		}else if(g==0){
			grade="정지회원으";
		}
		if (confirm(id+"님의 등급을 "+grade+"로 바꾸시겟습니까?") == true) { 
			location.href="${cp}/members_management?id="+id+"&selgrade="+grade+"&cmd=setgrade";
			} else { 
				return;
			}
		}
</script>
<body>
<table border="1" width="600" style="text-align: center; margin-top: 20px;" >

	<tr>
		<th>아이디</th><th>경고수</th><th>등급</th>
	</tr>	
	<c:forEach var="vo" items="${list}">
	<c:if test="${vo.id ne 'admin' }">
	
	<tr>
		<td><a href="${cp }/myPage?id=${vo.id}">	
		${vo.id }</a></td>
		
		<td >${vo.warning }&nbsp;<a class="btn1" href="${cp }/members_management?id=${vo.id }&cmd=down" style="text-decoration: none">↓</a><a class="btn" href="${cp }/members_management?id=${vo.id }&cmd=increase" style="text-decoration: none">↑</a></td>	
		<td>
		<c:choose>
			<c:when test="${vo.grade=='0' }">
					정지회원
			</c:when>
			<c:when test="${vo.grade=='1' }">
					브론즈<img  src="${cp }/images/bronze.png">
			</c:when>
			<c:when test="${vo.grade=='2' }">
				        실버<img  src="${cp }/images/silver.png">
			</c:when>
			<c:when test="${vo.grade=='3' }">
				        골드<img  src="${cp }/images/gold.png">
			</c:when>
			</c:choose>	
				<select name="selgrade" onchange="showMsg1('${vo.id }',this.value)">
						<option value="0" <c:if test='${vo.grade==0 }'>selected</c:if>>정지</option>				
						<option value="1" <c:if test='${vo.grade==1 }'>selected</c:if>>브론즈</option>
						<option value="2" <c:if test='${vo.grade==2 }'>selected</c:if>>실버</option>
						<option value="3" <c:if test='${vo.grade==3 }'>selected</c:if>>금</option>
				</select>
		</td>
	</tr>
	</c:if>
</c:forEach>
</table>

<!-- 게시판 페이징 -->
<div id="divPaging">
     <c:choose>
     	<c:when test="${startPageNum>10 }">
     		<a href="${cp }/members_management?pageNum=${startPageNum-1 }&field=${field }&keyword=${keyword}">◀</a> 
     	</c:when>
     	<c:otherwise>
     		    ◀
     	</c:otherwise>
     </c:choose>
     <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
     	<c:choose>
     		<c:when test="${pageNum==i }">
     			<span style="color:blue;">
     			<a href="${cp }/members_management?pageNum=${i}&field=${field}&keyword=${keyword}" style="text-decoration: none">
     				[${i}]
     			</a>
     			</span>	
     		</c:when>
     		<c:otherwise>
     			<span style="color:#555;">
     			<a href="${cp }/members_management?pageNum=${i}&field=${field}&keyword=${keyword}" style="text-decoration: none">
     				[${i}]
     			</a>
     			</span>
     		</c:otherwise>
     	</c:choose>
     </c:forEach>

  	<c:choose>
  		<c:when test="${endPageNum<pageCount }">
  			<a href="${cp }/members_management?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">▶</a>
  		</c:when>
  		<c:otherwise>
  			▶
  		</c:otherwise>
  	</c:choose>     
</div>       

      
<!-- 검색 폼 영역 --> 
<div id="divSearchOption">
  	 <form method="get" action="${cp }/members_management">
      <select name='field'>
         <option value='id' <c:if test="${field=='id'}">selected</c:if>>아이디</option>
         <option value='write_count' <c:if test="${field=='write_count'}">selected</c:if>>경고수</option>
         <option value='grade' <c:if test="${field=='grade'}">selected</c:if>>등급</option>
      </select>
         <input type="text" name="keyword" value="${keyword }" >
         <input type='submit' value='검색'>
        </form>  
</div>

</body>
</html>