<%@page import="model.chatDAO"%>
<%@page import="model.chatDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String msg = request.getParameter("inputMessage");

chatDTO dto = new chatDTO();
dto.setMsg(msg);

chatDAO dao = new chatDAO(application);

dao.sendMsg(dto);

dao.close();
%>