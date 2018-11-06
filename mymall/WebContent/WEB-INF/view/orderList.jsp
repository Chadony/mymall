<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Order List</h1>
<table border = "1">
	<tr>		
		<th>OrderList</th>
		<th>ItemNO</th>
		<th>Name</th>
		<th>Price</th>
		<th>OrderDate</th>
	</tr>
	<c:forEach var = "list" items="${memberItemList}">
		<tr>		
			<td>${list.memberItemNo}</td>
			<td>${list.itemNO}</td>
			<td>${list.itemName}</td>
			<td>${list.itemPrice}</td>
			<td>${list.orderDate}</td>
		</tr>	
	</c:forEach>
	<tr>
	
	</tr>
</table>
</body>
</html>