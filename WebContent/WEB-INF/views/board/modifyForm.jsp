<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- el/jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="./assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header / nav -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">

			<jsp:include page="/WEB-INF/views/includes/aside.jsp"></jsp:include>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="modifyForm">
						<form action="./board" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span> 
								<span class="form-value">${ boardUpdate.name }</span>
							</div>

							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span> 
								<span class="form-value">${ boardUpdate.hit }</span>
							</div>

							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span> 
								<span class="form-value">${ boardUpdate.regDate }</span>
							</div>

							<!-- 제목 -->
							<div class="form-group">
								<label class="form-text" for="txt-title">제목</label> 
								<input type="text" id="txt-title" name="title" value="${ boardUpdate.title }">
							</div>



							<!-- 내용 -->
							<div class="form-group">
								<textarea id="txt-content" name="content">
									${ boardUpdate.content }
								</textarea>
							</div>

							<a id="btn_cancel" href="">취소</a>
							<button id="btn_modify" type="submit">수정</button>
							<input type="hidden" name="action" value="modify">
							<!-- 방도가 떠오르질않는다 -->
							<input type="hidden" name="boardNo" value="${ boardUpdate.no }">

						</form>
						<!-- //form -->
					</div>
					<!-- //modifyForm -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>