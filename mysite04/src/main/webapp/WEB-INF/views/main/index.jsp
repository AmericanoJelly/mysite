<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" style="width:120px" src="${pageContext.request.contextPath }${site.profile_url}">
	
					<h2>${site.welcome_message}</h2>
					<p>
						${fn:replace(site.discription, newline, "</br>")}
						<br><br>
						<br><a href="${pageContext.request.contextPath }/guestbook">방명록</a>에 글 남기기<br>
					</p>

					
					
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>