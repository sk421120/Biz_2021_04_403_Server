<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>반갑습니다</h1>
	<c:forEach items ="${BRLIST }" var ="BR">
		<p>
			<a href ="rent/seq?id=${BR.br_seq}"> 
				${BR.br_seq} 
				${BR.br_bcode} 
				${BR.br_bname} 
				${BR.br_tel} 
			</a>
		${BR.br_isbn} 
		${BR.br_title} 
		${BR.br_edate} 
		${BR.br_price} 
		${BR.br_sdate} 
	</c:forEach>
	<h5>주문 번호를 입력하고 Enter를 눌러주세요.</h5>
	<form action="rent/seq">
		<input name="id">
		<button>전송</button>
	</form>
	<p>
		<a href="rent/order">도서대여신청</a>
</body>
</html>