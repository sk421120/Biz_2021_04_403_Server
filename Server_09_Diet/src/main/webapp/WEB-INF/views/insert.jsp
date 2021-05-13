<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		box-sizing: border-box;
	}
	h1, form {
		width: 80%;
		margin : 10px auto;
	}
	
	h1 {
		background-color: rgba(40,230,40,0.65);
		color: white;
		padding : 1.2rem;
		text-align :center;
	}
	
	form {
		border: 1px solid green;
		border-radius: 15px;
		padding: 10px;
	}
	
	label {
		display:inline-block;
		width:20%;
		text-align: right;
		padding : 8px;
		margin : 5px;
	}
	
	input {
		width:60%;
		padding : 8px;
		margin : 5px;
	}
	
	button {
		margin-left : 20px;
		background-color: salmon;
		color: white;
		padding : 8px 15px;
		outline: 0;
		border:0;
		border-radius: 10px;
		cursor: pointer;
	}
	
	button:hover {
		/* x-offset y-offset blur spread color */
		box-shadow: 3px 3px 5px rgba(50,50,50,0.5);
	}
</style>
</head>
<body>
	<h1>식품 섭취 내역 입력</h1>
	<form method="POST">
	<p> <label>식품코드</label>
		<input name="mf_code" value="${FOOD.fd_code }">
	<p> <label>식품이름</label>
		<input name="mf_name" value="${FOOD.fd_name} ">
	<p> <label>섭취일자</label>
		<input type="date" name="mf_date" value="${TODAY}">
	<p> <label>섭취량</label>
		<input type="number"name="mf_amt" value="1">
	<button>등록</button>
	</form>
</body>
</html>