<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	include 지시어 : 공통으로 사용할 jsp파일을 생성한 후 
	현재문서에 포함시킬때 사용한다. 각각의 jsp파일 상단에는
	반드시 page지시어(Directive)가 삽입되어야한다.
	※단 jsp파일에는 page지시어가 중복되어서는 안된다.
 --%>
<%@ include file = "IncludePage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IncludeMain.jsp</title>
<!-- CSS코드는 양이 많으므로 외부파일로 선언하여 현재문서에 링크시킨다. -->
<link rel="stylesheet" href="./css/div_layout.css" />
</head>
<body>
<div class="AllWrap">
	<div class="header">
		<!-- GNB(Global Navigation Bar)영역 - 전체공통메뉴 -->
		<%@ include file="../common/Top.jsp" %>
	</div>
	<div class="body">
		<div class="left_menu">
			<!-- LNB(Local Navigation Bar)영역 - 로컬메뉴 -->
			<%@ include file="../common/Left.jsp" %>
		</div>
		<div class="contents">
			<!-- Contents 영역 -->
			<%--
			해당 변수는 외부파일에 선언하여 본 문서에 include처리되었다.
			include는 문서자체를 포함시키는 개념이므로 에러가 발생하지
			않는다.
			 --%>
			<h2>오늘의날짜 : <%=todayStr %></h2>
			<br /><br />
			
			건강 이상설이 돌고 있는 김정은 북한 국무위원장이 4ㆍ27 판문점 공동선언 2주년인 27일에도 북한 관영 매체에 등장하지 않았다. 지난 12일 노동신문과 조선중앙통신 등 북한 관영 매체에서 전날(11일) 정치국 회의와 서부 지역 공군부대 방문 소식을 전한 이후 보름째 깜깜이다.

올해 김 위원장이 열흘 이상 공개석상에서 사라진 건 이번이 다섯 번째인데, 기간으로는 1월 26일 이후 21일과 3월 22일 이후 19일에 이어 세 번째 긴 '잠행'이다.

북한은 이날 김 위원장이 “원산 갈마 해안관광지구 건설을 적극적으로 지원한 일군(일꾼)들과 근로자들에게 감사를 보냈다”고 전했다. 북한은 지난 20일을 전후해 시작된 김 위원장 신병 이상설이 제기될 때마다 서한 발송 소식을 전하는 방식으로 반응해 왔다. 시술(수술)설이 나온 20일 밤 쿠바 국가 수반에게 답전을 보낸 소식을, 미국 CNN 방송이 중태설을 보도한 직후엔 시리아 대통령에게 서한을 보냈다고 북한 매체들이 보도했다.

지난 주말엔 일부 외신이 중국 의료진이 방북해 김 위원장을 진료했다거나 나아가 사망설이 돌았다. 이날 원산 갈마 관광지구 건설 관계자들에게 서한을 보낸 것도 같은 맥락으로 풀이된다.

정부 당국자는 “북한은 외부의 관심과 상관없이 자신들의 일정과 계획에 따라 김 위원장의 공개활동 소식을 전할 가능성이 크다”며 “다만, 외신들이 제기한 김 위원장의 신변 이상설이 북한 주민에게 전파되는 것을 차단하기 위해 서한 발송 사실을 보도하는 것으로 보인다”고 분석했다.
			
			<br /><br />
		</div>
	</div>
	<div class="copyright">
	<!-- Copyright -->
		<%@ include file="../common/Copyright.jsp" %>
	</div>
</div>
</body>
</html>