<%@page import="org.json.JSONArray"%>
<%@page import="board.vo.yun.BoardCommentsVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.dao.yun.BoardCommentsDao"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
<%
	int ref=Integer.parseInt(request.getParameter("ref"));
	int write_num=Integer.parseInt(request.getParameter("write_num"));
	ArrayList<BoardCommentsVo> list=BoardCommentsDao.getCommentsDao().getCommAndList(write_num, ref);
	JSONArray arr=new JSONArray();
	arr.put(list);
	
	response.setContentType("text/plain;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.println(arr.toString());
%>




