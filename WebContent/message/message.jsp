<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>
<%@page import="message.dao.joo.MessageDao"%>
<%@page import="message.vo.joo.MessageVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sid=(String)request.getSession().getAttribute("id");
	String rid=request.getParameter("rid");
	ArrayList<MessageVo> list=MessageDao.getDao().msgDetailList(sid, rid);
	
	
	JSONArray arr=new JSONArray();
	arr.put(list);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.println(arr.toString());
%>