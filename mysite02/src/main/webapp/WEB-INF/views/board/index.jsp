<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					
					<c:forEach items='${list }' var='vo' varStatus='status'>	
					<tr>
						<td>${vo.g_no }</td>
						<td style="text-align:left; padding-left:0px"><a href="${pageContext.request.contextPath }/board?a=view ">${vo.title }</a></td>
						<td>${vo.contents }</td>
						<td>${vo.user_name }</td>
						<td>${vo.hit }</td>
						<td>${vo.reg_date }</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					</c:forEach>
					
					<tr>
						<td>2</td>
						<td style="text-align:left; padding-left:10px">
							<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a href="${pageContext.request.contextPath }/board?a=view">나도..</a></td>
						<td>느어어</td>
						<td>3</td>
						<td>2015-10-02 12:04:12</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td style="text-align:left; padding-left:20px" ><a href="${pageContext.request.contextPath }/board?a=view">
							<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />느어어어</a></td>
						<td>ㅇㄹㄴㅇㄴ</td>
						<td>3</td>
						<td>2015-09-25 07:24:32</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li><a href="">4</a></li>
						<li><a href="">5</a></li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
				<a href="${pageContext.request.contextPath }/board?a=write " id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>