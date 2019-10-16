<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ad/insert.jsp -->
<div>
	<form action="${cp }/ad/insert" method="post">
		회사명<input type="text" name="ad_com"><br>
		광고비<input type="text" name="ad_money"><br>
		이미지<input type="text" name="ad_image"><br>
		시작날짜<input type="text" name="ad_start_date">YYYY-MM-DD 입력<br>
		끝날짜<input type="text" name="ad_end_date">YYYY-MM-DD 입력
		<input type="submit" value="광고등록">
	</form>
</div>