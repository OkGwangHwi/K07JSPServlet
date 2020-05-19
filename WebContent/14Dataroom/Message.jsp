<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- Message.jsp --%>
<c:choose>
	<c:when test="${WHEREIS=='UPDATE' }">
		<c:set var="sucmsg" value="수정성공함" />
		<c:set var="failmsg" value="수정실패함" />
		<c:set var="sucurl" 
		value="/DataRoom/DataView?idx=${idx}&nowPage=${nowPage}" />
	</c:when>
	<c:otherwise>
		<c:set var="sucmsg" value="삭제성공함" />
		<c:set var="failmsg" value="삭제실패함" />
		<c:set var="sucurl" 
		value="/DataRoom/DataList?nowPage=${nowPage }" />	
	</c:otherwise>
</c:choose>


<script>
<c:choose>
	<c:when test="${SUC_FAIL==1 }">
		alert("${sucmsg }");
		location.href="<c:url value='${sucurl }' />";
	</c:when>
	<c:when test="${SUC_FAIL==0 }">
		alert("${failmsg }");
		history.back();
	</c:when>
	<c:otherwise>
		alert('파일업로드를 실패하였습니다.');
		history.back();
	</c:otherwise>
</c:choose>
</script>
