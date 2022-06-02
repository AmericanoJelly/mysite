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

					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>${vo.g_no }</td>
							<td style="text-align: left; padding-left: 0px"><a
								href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a></td>
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
					<%--<td style="text-align:left; padding-left:10px">
				<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
				<a href="${pageContext.request.contextPath }/board?a=view">나도..</a></td>
				 --%>
				</table>

					<!-- pager 추가 -->
				<div class="pager">
					<ul><c:if test="${page.page != 1}">
							<c:choose>
								<c:when test="${param.kwd!=null}">
									<li><a style="color:orange" href="${pageContext.servletContext.contextPath }/board?i=${page.page-1}&kwd=${param.kwd}">◀</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="color:orange" href="${pageContext.servletContext.contextPath }/board?i=${page.page-1}">◀</a></li>
								</c:otherwise>
							</c:choose>
						</c:if>												
						<c:forEach var='i' begin="${page.startPage }" end="${page.totalSize}">
						<c:choose>
							<c:when test="${i == page.page}">
								<li class="selected"><a href="${pageContext.servletContext.contextPath }/board?i=${page.page}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${page.lastPage < i}">
										<li >${i}</li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${param.kwd!=null}">
												<li ><a href="${pageContext.servletContext.contextPath }/board?i=${i}&kwd=${param.kwd}">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li ><a href="${pageContext.servletContext.contextPath }/board?i=${i}">${i}</a></li>
											</c:otherwise>
										</c:choose>										
									</c:otherwise>
								</c:choose>							
							</c:otherwise>
						</c:choose>
						</c:forEach>
						<c:if test="${page.page != page.lastPage}">
							<c:choose>
								<c:when test="${param.kwd!=null}">
									<li><a style="color:orange" href="${pageContext.servletContext.contextPath }/board?i=${page.page+1}&kwd=${param.kwd}">▶</a></li>
								</c:when>
								<c:otherwise>
									<li><a style="color:orange" href="${pageContext.servletContext.contextPath }/board?i=${page.page+1}">▶</a></li>
								</c:otherwise>
							</c:choose>							
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