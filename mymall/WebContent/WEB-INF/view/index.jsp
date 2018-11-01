<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index</h1>
	<c:if test="${loginMember != null}">
	${loginMember.id}님 반갑습니다.
	<a href = "${pageContext.request.contextPath}/LogoutController">로그아웃</a>
	</c:if>
	<c:if test="${loginMember == null}">
	<a href = "${pageContext.request.contextPath}/LoginController">로그인</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/AddMemberController">회원가입</a>
</body>
</html>