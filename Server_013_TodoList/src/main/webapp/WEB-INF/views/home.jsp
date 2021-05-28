<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>MY TODO List</title>
<style>
/* style 지정을 위하여 전체 초기화 */
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

h1, form.doit, table.todoList {
	width: 50%;
	margin: 10px auto;
	border-radius: 5px;
}

h1 {
	background-color: rgba(0, 255, 0, 0.3);
	color: white;
	padding: 1rem;
	text-align: center;
	'
}

form.doit {
	border: 1px solid red;
	padding: 10px;
	text-align: center;
}

table.todoList {
	border-collapse: collapse;
	border-spacing: 0;
}

table.todoList tr {
	padding: 7px;
	border: 1px dashed green;
	cursor: pointer
}

/* table의 마지막 줄과 첫번째 줄 */
table.todoList tr:last-child {
	border-bottom: 2px solid green;
}

table.todoList tr:first-child {
	border-top: 2px solid green;
}

table.todoList td.count {
	font-size: 20px;
	text-align: right;
	width: 5%;
}

table.todoList td.sdate, table.todoList td.edate {
	font-size: 10px;
	text-align: center;
	width: 20%;
}

table.todoList td.doit {
	font-size: 30px;
	text-align: left;
	/*
		두줄 이상의 본문을 1줄로 줄이고 말줄임표 표현
		table이 아닌 box 형 tag의 경우
		max-width 대신 width 값을 설정해야한다
		아래 4가지 속성을 동시에 적용해야만 한다
		*/
	max-width: 0;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	
	/*
	실험적인 css 적용하기
	user-select:none은
	선택박스가 나타나지 않도록 적용
	 그냥 user-select 기능이 적용되는 브라우저용
	*/
	user-select:none;
	-webkit-user-select:none;
	-moz-user-select:none;
	-ms-user-select:none;
	-o-user-select:none;
}

.through-text {
	text-decoration: line-through greenyellow wavy;
}

/* 화면폭이 480px 이상 일때 적용할 style */
@media screen and (min-width:480px) {
	h1, form.doit, table.todoList{
		width:95%;
		margin:0 auto;
	}
}

/* 화면폭이 800px 이하 일때 적용할 style */
@media screen and (max-width:800px) {
	h1, form.doit, table.todoList{
		width:70%;
		margin:0 auto;
	}
}


</style>
<script>
	document.addEventListener("DOMContentLoaded", ()=>{
		document.querySelector("table.todoList").addEventListener("dblclick",(ev)=>{
			
			ev.preventDefault()
			
			let tagName = ev.target.tagName;
			if(tagName =="TD") {
				
				// 클릭된 TD tag를 감싸고 있는 TR객체(누구냐)
				let tr = ev.target.closest("TR").dataset
				// let seq = ev.target.closest("TR").dataset.seq
				let td_seq = tr.seq;
				let td_edate = tr.edate;
				
				let confirm_msg = td_edate ? "완료 취소" : "TODO를 완료 했나요?";
				
				if( confirm(confirm_msg) ) {
					location.href = "${rootPath}/expire?td_seq=" + td_seq;
					//alert(confirm_msg);
				}
			}
		})
	})
</script>
</head>
<body>
	<h1>TO DO List</h1>

	<%--
		form tag의 action 속성
		form tag의 action 속성은 form에 담긴 데이터를 submit할때(서버로 전송할때)
		어떤 uri path를 통해서 서버에 보낼것인가를 지정하는 것
		
		만약 이 action 속성을 지정하지 않으면
		현재 파일(home.jsp)을 보여줄때 사용한 uri 주소가 자동으로 설정이 된다
		
		${rootPath}/ 처럼 주소를 지정하는 것 form, a tag등에 URL, URI를 지정할때
		
		주소의 지정방식에 따라 상대주소, 절대주소 방법이 있는데
		지정하는 방법에 따라 연결이 잘 안되는 경우가 많다
		
		우리 프로젝트는 모두 절대주소 지정방식으로 통일하고
		항상 주소(URI, URL)과 관련된 모든 항목은
		${rootPath} 시작하는 주소로 사용한다
		rootPath = http://localhost:8080/todo
		 --%>
	<form class="doit" method="POST" action="${rootPath}/insert">
		<input name="td_doit" placeholder="할일을 입력한 후 Enter">
	</form>

	<div class="msg">${MSG}</div>

	<table class="todoList">
		<c:forEach items="${TDLIST }" var="TD" varStatus="ST">
			<tr data-seq="${TD.td_seq }" data-edate="${TD.td_edate }">
				<td class="count">${ST.count }</td>
				<td class="sdate">${TD.td_sdate }<br />${TD.td_stime }</td>
				<td class="doit ${empty TD.td_edate ? '' : 'through-text' }">${TD.td_doit }</td>
				<td class="edate">${TD.td_edate }<br />${TD.td_etime }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>