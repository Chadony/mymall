<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item List</title>
</head>
<body>
	<h1>상품목록</h1>
	<table border="1">
		<tr>
			<th>No</th><th>Name</th><th>Price</th><th>Order</th>
		</tr>
		<c:forEach var="" items="${list}">
			<tr>
				<td>${item.no}</td><!--item.getNo()  -->
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td><a href="${pageContext.request.contextPath}/OrderController">주문하기</a></td>
			</tr>
		</c:forEach>
	</table>
	<!--페이징 링크 ${pageContext.request.contextPath}/itmeList.jsp?currentPage=-->
</body>
</html>