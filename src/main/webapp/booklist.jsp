<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>도서 기증 대여 서비스</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.white-box {
	max-width: 800px;
	margin-top: 40px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	/* height: 400px; */
}

.white-box-nav {
	max-width: 800px;
	margin-top: 0px;
	padding: 20px;
	background: #fff;
	/* -webkit-border-radius: 10px; */
	/* -moz-border-radius: 10px; */
	/* border-radius: 10px; */
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	/* height: 400px; */
}
</style>
</head>


<body>
	<%-- 		<jsp:include page="header.jsp" />
 --%>
	<div class="container">

		<div class="row">
			<div class="white-box col-md-12 mx-auto">
				<div class="row" style="justify-content: space-between;">
					<h2 class="mb-4 mx-4 text-left">도서 목록</h2>
					<div class='text-right'>
						<nav class="navbar d-flex text-right">
							<form class="validation-form d-flex" novalidate method="get"
								action="/Servlet_Study/searchTitle">
								<input class="form-control mr-sm-2" type="search"
									name="searchKeyword" id="searchKeyword"
									placeholder="제목으로 검색하세요." aria-label="searchKeyword">
								<button class="btn btn-outline-success my-2 my-sm-0 d-flex"
									type="submit">Search</button>
							</form>
						</nav>
					</div>
				</div>
				<c:if test="${message.length() > 0}">
					<div class="m-2 text-center">${message}</div>
					<div class="m-2 text-center">
						<a href="/Servlet_Study/booklist">
							<button type="button" class="btn btn-outline-secondary">전체
								도서 보기</button>
						</a>
					</div>
				</c:if>
				<c:if test="${contributeMessage.length() > 0}">
					<div class="m-2 text-center">${contributeMessage}</div>
					<div class="m-2 text-center">
						<a href="/Servlet_Study/booklist">
							<button type="button" class="btn btn-outline-secondary">전체
								도서 보기</button>
						</a>

					</div>
				</c:if>

				<c:if test="${returnMessage.length() > 0}">
					<div class="m-2 text-center">${returnMessage}</div>
					<div class="m-2 text-center">
						<a href="/Servlet_Study/booklist">
							<button type="button" class="btn btn-outline-secondary">전체
								도서 보기</button>
						</a>

					</div>
				</c:if>

				<c:if test="${bookList.size() > 0}">
					<div class="col-md-12 mx-auto text-center mb-3 ">
						<a href="/Servlet_Study/booklist">
							<button type="button" class="btn btn-outline-dark">전체 도서</button>
						</a> <a href="/Servlet_Study/mybooklist">
							<button type="button" class="btn btn-outline-dark">내가
								대여한 도서</button>
						</a> <a href="/Servlet_Study/availbooklist">
							<button type="button" class="btn btn-outline-dark">대여 가능
								도서</button>
						</a> <a href="/Servlet_Study/popularbooklist">
							<button type="button" class="btn btn-outline-dark">인기 도서</button>
						</a> <a href="/Servlet_Study/recentbooklist">
							<button type="button" class="btn btn-outline-dark">최신 도서</button>
						</a> <a href="/Servlet_Study/bookSearch.jsp">
							<button type="button" class="btn btn-warning">도서 기증</button>
						</a> <a href="/Servlet_Study/organize">
							<button type="button" class="btn btn-danger">도서 정리</button>
						</a>
					</div>
				</c:if>

				<c:if test="${bookList.size() > 0}">
					<div class="col-md-12 mx-auto text-center mb-3 ">총
						${bookList.size()}권의 책이 있습니다.</div>
				</c:if>

				<c:forEach items="${bookList}" var="book">
					<div class="card mb-3" style="max-width: 800px;">
						<div class="row no-gutters">
							<div class="col-md-3">
								<img src="${book.image}" class="card-img" alt="...">
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<c:if test="${book.rentState == 0}">
										<a href="#" class="badge badge-success">대여 가능</a>
									</c:if>
									<c:if test="${book.rentState != 0}">
										<a href="#" class="badge badge-danger">대여 불가</a>
									</c:if>
									<c:if test="${book.bookCondition == 0}">
										<a href="#" class="badge badge-secondary">책 상태 하</a>
									</c:if>
									<c:if test="${book.bookCondition == 1}">
										<a href="#" class="badge badge-info">책 상태 중</a>
									</c:if>
									<c:if test="${book.bookCondition == 2}">
										<a href="#" class="badge badge-primary">책 상태 상</a>
									</c:if>
									<c:if test="${book.rentState != 0}">
										<p class="card-text mb-0">
											<small class="text-muted">예상 반납일: ${book.returnDate}
											</small>
										</p>
									</c:if>

									<h5 class="card-title">${book.title}</h5>
									<h6 class="card-text">${book.price}원</h6>
									<p class="card-text">
										<small class="text-muted">${book.author} | </small> <small
											class="text-muted">${book.publisher} | </small> <small
											class="text-muted">${book.publisherDate.split(" ")[0]}</small>
									</p>
									<p class="card-text">${book.description}</p>
									<!-- 추가 -->
									<p class="card-text">
										<small class="text-muted">현재까지 ${book.rentCount}명이 이
											책을 대여했습니다.</small>
									</p>
									<!-- 대여자가 없는 경우 -->
									<c:if test="${book.rentState == 0}">
										<form class="validation-form" novalidate method="get"
											action="/Servlet_Study/bookRent">
											<input type="hidden" class="form-control" id="bookId"
												name="bookId" value="${book.bookId}"> <input
												type="hidden" class="form-control" id="rentCount"
												name="rentCount" value="${book.rentCount}">
											<div class="text-right">
												<button class="btn btn-outline-secondary" type="submit">대여하기</button>
												<c:set var="loggedUserId"
													value="${applicationScope.loggedUserId}" />
												<a href="${book.link}" target="_blank">
													<button type="button" class="btn btn-secondary">상세보기</button>
												</a>
											</div>
										</form>
									</c:if>
									<!-- 대여자가 있는 경우 -->
									<c:if test="${book.rentState != 0}">
										<form class="validation-form" novalidate method="get"
											action="/Servlet_Study/bookReturn">
											<input type="hidden" class="form-control" id="bookId"
												name="bookId" value="${book.bookId}">
											<div class="text-right">
												<c:set var="loggedUserId"
													value="${applicationScope.loggedUserId}" />
												<!-- 대여자가 본인인 경우만 반납하기 버튼 보여주기 -->
												<c:if
													test="${book.rentState != 0 && book.rentUserId == loggedUserId}">
													<button class="btn btn-outline-danger" type="submit">반납하기</button>
												</c:if>
												<a href="${book.link}" target="_blank">
													<button type="button" class="btn btn-secondary">상세보기</button>
												</a>
											</div>
										</form>
									</c:if>

								</div>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>

		<footer class="my-3 text-center text-small">
			<p class="mb-1">&copy; PoscoDX Java 전문가과정 408기 지후양 민수킴조</p>
		</footer>
	</div>
</body>

<script>
	var descriptionElement = document.getElementById("description");
	var maxLength = 100; // 최대 글자 수 제한

	if (descriptionElement.textContent.length > maxLength) {
		var truncatedText = descriptionElement.textContent.slice(0, maxLength)
				+ "...";
		descriptionElement.textContent = truncatedText;
	}
</script>


</html>