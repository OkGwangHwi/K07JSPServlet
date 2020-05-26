<%@page import="model.chatDAO"%>
<%@page import="model.chatDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("chat_id");

chatDTO dto = new chatDTO();
dto.setId(id);

chatDAO dao = new chatDAO(application);

int affected = dao.insertId(dto);
if(affected == 1){
	response.sendRedirect("chatting03.jsp");
}
else{
%>
	<script>
		alert("접속에 실패하였습니다.");
		history.go(-1);
	</script>
<%
}
%>