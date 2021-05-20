<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
nav#main {
	padding: 20px;
}
/*
	ul tag는 본문에서 보편적으로 list 등을 표현할 때 많이 사용하는 tag 이다
	ul tag만을 selector 하여 style 을 지정하면 혹시 nav 이 아닌 영역에서 작성된
	ul tag 에도 같은 속성이 설정되는 현상이 발생한다
	
	이러한 불편함을 제거하기 위하여 css selector 지정을 좀 더 세분화 하기로 한다
	
	nav#main ul {  }
	id 가 main 인 nav Box 내부에 있는 ul tag에만 제한적으로 적용하라 하는 의미로 사용
	본문의 다른 곳에 있는 ul에는 속성을 적용하지 말라
*/
nav#main ul {
	margin: 0px;
	background-color: salmon;
	text-align: center;
}

nav#main li {
	margin: 0px 10px;
	padding: 10px;
	color: white;
	display: inline-block;
	list-style: none;
}

nav#main li:hover {
	background-color: lightsalmon;
	color: white;
	cursor: pointer;
}

nav#main a {
	display: inline-block;	/* box style 로 변경 */
	color:inherit;	/* 글자색은 a tag를 감싼 tag에서 상속 */
	text-decoration: none;	/* text에 언더라인 제거 */
}
</style>

<h1>방명록 2021.05</h1>
<%-- 메뉴를 설정할 때 사용하는 tag --%>
<%-- div 라는 tag 사용하여 layout을 설정했는데 --%>
<%-- HTML 5에서는 Symatec(의미있는 이름으로) tag를 사용하는 것을 권장--%>
<nav id="main">
	<ul>
		<%--
		일반적으로 메뉴(내비게이션)등을 만들때 해당 메뉴를 클릭하면 
		다른 page로 점프하는 방식을 가장 쉽게 구현하기 위하여 a tag 를 사용한다.
		
		a tag 를 사용할 때 a tag 의 기본 속성을 제거하고 새로운 속성을 부여하는
		CSS 코드를 추가한다.
		
		이러한 방식이 번거로워서 아예 a tag를 사용하지 않고
		다른 종류의 tag를 사용하여 메뉴를 구성하기도 한다
		
		여기서는 ul>li tag를 조합하여 상단의 메뉴를 생성하였다
		
		a tag 를 사용하지 않고 메뉴등을 구성했을때는 스크립트를 사용하여
		"메뉴 클릭 점프" 기능을 구현해야 하는 문제가 발생한다
		
		최근에 유행하는 UI/UX는 그러한 스크립트를 사용해야함에도 불구하고
		a tag 사용을 하지않는 방식을 많이 사용한다
		
		스크립트를 사용하면 또 다른 이점이 있는데 메뉴와 관련된 부분을
		스크립트에서 모아서 사용할 수 있고
		상대적으로 html 코드에 직접 anchor를 설정하는 것보다 본문코드가 간편해진다
		 --%>
		<li><a href="#">Home</a></li>
		<li><a href="#">공지사항</a></li>
		<li><a href="#">회사소개</a></li>
		<li><a href="#">로그인</a></li>
		<li><a href="#">회원가입</a></li>
	</ul>
</nav>

<script>
	/*
	HTML 문서를 DOM(Document Object Model)이라고 한다
	Tag로 둘러싸인 모든 속성은 하나의 객체가 된다
	
	HTML 문서의 모든 객체의 시작점 객체는 document
	
	*/
	document.querySelector("nav#main").addEventListener("click",function(ev) {
		
		let text = ev.target.textContent;
		alert(text+" 클릭되었습니다")
	})

</script>