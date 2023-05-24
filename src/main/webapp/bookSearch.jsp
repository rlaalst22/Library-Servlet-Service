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
a
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
	margin-top: 80px;
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
</style>
</head>

<body>
	<%--       <jsp:include page="header.jsp" />
 --%>
	<div class="container">
		<div class="row">
			<div class="white-box col-md-12 mx-auto">
				<div class="row" style="justify-content: space-between;">
					<h2 class="mb-4 mx-4 text-left">도서 목록</h2>
					<div class='text-right'>
						<nav class="navbar d-flex text-right">
							<form class="validation-form d-flex" novalidate method="get"
								action="/Servlet_Study/bookSearch">
								<input class="form-control mr-sm-2" type="search" name="keyword"
									id="keyword" placeholder="검색어를 입력하세요."
									aria-label="searchKeyword">
								<button class="btn btn-outline-success my-2 my-sm-0 d-flex"
									type="submit">Search</button>
							</form>
						</nav>
					</div>
				</div>
				<!--             <h4 class="mb-2 col-md-12 text-center">도서 목록 조회</h4>
            <form action="/Servlet_Study/bookSearch"  method="get">
               <input type="text" name="keyword" placeholder="검색어를 입력하세요">
               <button type="submit" class="btn btn-primary">검색</button>
            </form> -->
				<c:forEach items="${bookList}" var="book">

					<div class="card mb-3" style="max-width: 800px;">
						<div class="row no-gutters">
							<div class="col-md-3">
								<img src="${book.image}" class="card-img" alt="...">
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<h5 class="card-title">${book.title}</h5>
									<h6 class="card-text">${book.price}원</h6>
									<p class="card-text">
										<small class="text-muted">${book.author} | </small> <small
											class="text-muted">${book.publisher} | </small> <small
											class="text-muted">${book.publisherDate.split(" ")[0]}</small>
									</p>
									<p class="card-text">${book.description}</p>
									<div class="text-right">
										<c:if test="${book.rentState == 0}">
											<a href="/Servlet Study/bookRent">
												<button type="button" class="btn btn-outline-secondary">대여하기</button>
											</a>
										</c:if>
										<form action="/Servlet_Study/BookInsert" method="get">
											<!-- 	<p>책 상태 : </p> -->
											<label>책 상태 : <input type="radio"
												name="bookCondition" value="2" checked> 상
											</label> <label> <input type="radio" name="bookCondition"
												value="1"> 중
											</label> <label> <input type="radio" name="bookCondition"
												value="0"> 하
											</label> <input type="hidden" name="title" value="${book.title}">
											<input type="hidden" name="author" value="${book.author}">
											<input type="hidden" name="publisher"
												value="${book.publisher}"> <input type="hidden"
												name="publisherDate" value="${book.publisherDate}">
											<input type="hidden" name="price" value="${book.price}">
											<input type="hidden" name="image" value="${book.image}">
											<input type="hidden" name="isbn" value="${book.isbn}">
											<input type="hidden" name="description"
												value="${book.description}"> <input type="hidden"
												name="link" value="${book.link}">

											<button type="submit" class="btn btn-primary mx-3">기증</button>
										</form>
										<!-- 
                              <a href="${book.link}" target="_blank">
                                 <button type="button" class="btn btn-secondary">상세보기</button>
                              </a>
                               -->


									</div>

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


</html>