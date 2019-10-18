<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>board/community.jsp</title>
<style type="text/css">

	table{width: 100%; text-align: center;}
	   #divPaging {clear:both;  margin:0 auto; width:220px; height:50px;}
	   #divPaging > div {float:left; width: 30px; margin:0 auto; margin-top: 30px;text-align:center;}
       #divSearchOption {clear:both; }
       #divSearchOption > div {float:left; margin:0 auto; margin-top: 30px; width:auto; height:100px; text-align:center;}
</style>
</head>
<body>
<hr>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<table border="1" width="20">
</table>
	<c:set var="a" value="${genre }"/>
	<select id="viewSongList" onchange="aa(this.value)">
			<option value="0" <c:if test='${a==0 }'>selected</c:if> >전체선택</option>
			<option value="1" <c:if test='${a==1 }'>selected</c:if> >알앤비</option>
			<option value="2" <c:if test='${a==2 }'>selected</c:if> >팝</option>
			<option value="3" <c:if test='${a==3 }'>selected</c:if> >가요</option>
	</select>

<!-- 게시판 body 영역 -->


<table border="1" width="600">
	<tr>
		<th>글번호</th><th>제목</th><th>파일번호</th><th>작성자</th><th>작성일</th><th>조회</th>
	</tr>
    <c:forEach var="vo" items="${list}">
    <c:choose>
    	<c:when test="${vo.genre_num==a }">
    	<tr>
		<td>${vo.write_num }
		</td>
		
		<td>
		<c:choose>
			<c:when test="${vo.genre_num==1 }">
				[알앤비]
			</c:when>
			<c:when test="${vo.genre_num==2 }">
				[팝]
			</c:when>
			<c:when test="${vo.genre_num==3 }">
				[가요]
			</c:when>	
		</c:choose>
		<a href="${cp }/fileboard/detail?write_num=${vo.write_num}">
		
		${vo.p_title }</a></td> 
		<td>${vo.f_num}</td>
		<td>${vo.id }</td>
		<td>${vo.r_date }</td>
		<td>${vo.views }</td>
		</tr>
    	</c:when>
    	
    	<c:when test="${genre==null ||a==0}">
	    	<tr>
			<td>${vo.write_num }</td>
			<td>
			<c:choose>
				<c:when test="${vo.genre_num==1 }">
					[알앤비]
				</c:when>
				<c:when test="${vo.genre_num==2 }">
					[팝]
				</c:when>
				<c:when test="${vo.genre_num==3 }">
					[가요]
				</c:when>	
			</c:choose>
			<a href="${cp }/fileboard/detail?write_num=${vo.write_num}">
			${vo.p_title }</a></td> 
			<td>${vo.f_num}</td>
			<td>${vo.id }</td>
			<td>${vo.r_date }</td>
			<td>${vo.views }</td>
			</tr>
    	</c:when>
    </c:choose>
		
	</c:forEach>
	
	
</table>


<!-- 게시판 페이징 -->
<div id="divPaging">
     <c:choose>
     	<c:when test="${startPageNum>10 }">
     		<a href="${cp }/fileboared/community?pageNum=${startPageNum-1 }&field=${field }&keyword=${keyword}">◀</a> 
     	</c:when>
     	<c:otherwise>
     		    ◀
     	</c:otherwise>
     </c:choose>
     <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
     	<c:choose>
     		<c:when test="${pageNum==i }">
     			<span style="color:blue;">
     			<a href="${cp }/fileboard/community?pageNum=${i}&field=${field}&keyword=${keyword}" >
     				[${i}]
     			</a>
     			</span>	
     		</c:when>
     		<c:otherwise>
     			<span style="color:#555;">
     			<a href="${cp }/fileboard/community?pageNum=${i}&field=${field}&keyword=${keyword}">
     				[${i}]
     			</a>
     			</span>
     		</c:otherwise>
     	</c:choose>
     </c:forEach>

  	<c:choose>
  		<c:when test="${endPageNum<pageCount }">
  			<a href="${cp }/fileboard/community?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">▶</a>
  		</c:when>
  		<c:otherwise>
  			▶
  		</c:otherwise>
  	</c:choose>     
</div>       

      
<!-- 검색 폼 영역 --> 
<div id="divSearchOption">
  	 <form method="get" action="${cp }/fileboard/community">
      <select name='field'>
         <option value='TC' <c:if test="${field=='TC'}">selected</c:if>>제목+내용</option>
         <option value='p_title' <c:if test="${field=='p_title' }">selected</c:if>>제목</option>
         <option value='contents' <c:if test="${field=='contents' }">selected</c:if>>내용</option>
         <option value='id' <c:if test="${field=='id' }">selected</c:if>>작성자</option>
      </select>
         <input type="text" name="keyword" value="${keyword }" >
         <input type='submit' value='검색'>
        </form>
        <form method="get" action="${cp }/fileboard/insert">
         <input type='submit' value='글쓰기'>
       </form>  
       
</div>	

<script type="text/javascript">
function aa(n){
	location.href='${cp }/fileboard/community?genre='+n;
	

}

</script>
	