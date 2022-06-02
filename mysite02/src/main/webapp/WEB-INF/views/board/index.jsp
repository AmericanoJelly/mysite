<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="/board" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
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
					<c:set var='count' value='${fn:length(list) }' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
					
						<tr>
							<td>${count-status.index }</td>
								<c:choose>
								<c:when test = "${vo.dept != 1}">
									<td style="text-align: left; padding-left:${(vo.dept-1)*10}px">
									<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
								</c:when>
								<c:otherwise>
								<td style="text-align: left; padding-left: 0px">
								</c:otherwise>
								</c:choose>
							<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">
							${vo.title }
							</a>
							</td>
							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							
							<c:choose>
							<c:when test="${authUser.no == vo.user_no}">
							<td>
							<a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }"class="del">삭제</a>
							</td>
							</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					<c:if test="${pages.currentPage != 1 }">
						<li><a href="${pageContext.servletContext.contextPath }/board?p=${pages.prevPage }">◀</a></li>
					</c:if>
					<c:forEach var='page' begin='${pages.startPage }' end='${pages.lastPage }'>
						<c:if test="${page == pages.currentPage }">
							<li class ="selected">${page }</li>
						</c:if>
						<c:if test="${page <= pages.totalPage && page ne pages.currentPage }">
							<li><a href="${pageContext.servletContext.contextPath }/board?p=${page }">${page }</a></li>
						</c:if>
						<c:if test="${page > pages.totalPage && pages.totalPage < pages.lastPage }">
							${page }
						</c:if>
					</c:forEach>
					<c:if test="${pages.currentPage < pages.totalPage }">
						<li><a href="${pageContext.servletContext.contextPath }/board?p=${pages.nextPage }">▶</a></li>
					</c:if>
					
					</ul>
				</div>			
				<!-- pager 추가 -->

				<div class="bottom">
					<c:choose>
						<c:when test="${empty authUser.no  }">
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/board?a=write&no=${authUser.no} " id="new-book">글쓰기</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>