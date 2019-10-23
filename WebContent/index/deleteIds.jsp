<%@page import="org.json.JSONObject"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="loginIds.dao.joo.LoginIdsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id=(String)request.getParameter("id");
	int n=LoginIdsDao.getDao().deleteLogins(id);
	JSONObject json=new JSONObject();
	if(n>0){
		json.put("code", "success");
	}else{
		json.put("code", "fail");
	}
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.println(json.toString());
%>