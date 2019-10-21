<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div>
	<div ><c:import url="/ad"/></div>
	<div style="float: left; padding-top: 50px;">
	<div style="width: 200px; height: 400px; padding-left:20px; "><c:import url="/likes"/></div>
	<div style="width: 200px; height: 400px; padding-left:20px; " ><c:import url="/recently"/></div>
	<div style="width: 200px; height: 400px; padding-left:20px; " ><c:import url="/recently_music"/></div>
	</div>
	<div style="width: 200px; height: 200px; padding-top: 50px; padding-right:100px;  float: right;"><c:import url="/recommended"/></div>
	<div style="width: 200px; height: 200px; padding-right:100px;  float: right;"><c:import url="/recommended_folk"/></div>
	<div style="width: 200px; height: 200px; padding-right:100px;  float: right;"><c:import url="/recommended_RB"/></div>
</div>