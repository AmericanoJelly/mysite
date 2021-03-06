<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<% pageContext.setAttribute("newLine", "\n"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook?a=add" method="post">
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

		
				<c:set var='count' value='${fn:length(list) }' />
				<c:forEach items='${list }' var='vo' varStatus='status'>
				<table width=510 border=1>
					<tr>
						<td>[${count-status.index }]</td>
						<td>${vo.name }</td>
						<td>${vo.reg_date }</td>
						<td><a href="${pageContext.request.contextPath }/guestbook?a=deleteform&no=${vo.no }">삭제</a></td>
					</tr>
					<tr>
						<td colspan=4>
						${fn:replace(vo.message, newLine, "<br/>") }	
						</td>
					</tr>
				</table>
				</c:forEach>
				<br>

			</div>
			
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>