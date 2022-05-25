<%@page import="com.douzone.mysite.repository.GuestbookRepository"%>
<%@page import="com.douzone.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<GuestBookVo> list = (List<GuestBookVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath()%>/guestbook?a=add" method="post">
				<input type='hidden' name='a' value='insert'>
					<table border=1 width=500>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" value=""></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" value=""></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" value="" cols=60 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE="등록"></td>
						</tr>
					</table>
				</form>
				<br>

				<%
				int count = list.size();
				for (GuestBookVo vo : list) {
				%>
				<table width=510 border=1>
					<tr>
						<td><%=count%></td>
						<td><%=vo.getName()%></td>
						<td><%=vo.getReg_date()%></td>
						<td><a href="<%=request.getContextPath() %>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
					</tr>
					<tr>
						<td colspan=4><%=vo.getMessage().replaceAll("\n", "<br/>")%></td>
					</tr>
				</table>
				<br>
				<%
				count = count - 1;
				}
				%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>