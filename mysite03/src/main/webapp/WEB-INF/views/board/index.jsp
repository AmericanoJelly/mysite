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
				<form id="search_form" action='${pageContext.request.contextPath }/board' method="get">
					<input type="hidden" id="page" name="page" value="1"> 
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
							<td>${count - status.index }</td>
						<c:choose>
						<c:when test = "${vo.dept != 1}">
							<td style="text-align: left; padding-left:${(vo.dept-1)*10}px">
							<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
						</c:when>
						<c:otherwise>
							<td style="text-align: left; padding-left: 0px">
						</c:otherwise>
						</c:choose>
							<a href="${pageContext.request.contextPath }/board/view/${vo.no }">${vo.title }</a>
							</td>
							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							
							<c:choose>
							<c:when test="${authUser.no == vo.user_no}">
							<td>
							<a href="${pageContext.request.contextPath }/board/delete/${vo.no }"class="del"style="background-image:url(${pageContext.request.contextPath }/assets/images/recycle.png)">삭제</a>
							</td>
							</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${param.page == 1 }">
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath }board/page/${param.page-1 }">◀</a></li>
							</c:otherwise>
						</c:choose>

						<c:forEach begin="${startPage }" end="${endPage }" var="page"
							step="1">
							<c:choose>
								<c:when test="${param.page == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:when test="${page > lastPage }">
									<li>${page }</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath }/board/page/${page }">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${param.page != lastPage }">
								<li><a
									href="${pageContext.request.contextPath }/board/page/${param.page+1 }">▶</a></li>
							</c:when>
							<c:otherwise>
								<li>▶</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<c:choose>
						<c:when test="${empty authUser.no  }">
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/board/write/${vo.no }" id="new-book">글쓰기</a>
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