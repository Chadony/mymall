<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/AddItemController" method="post">
		<table>
			<tr>
				<td>name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>price</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="제출"/></td>
			</tr>
		</table>
	</form>
</body>
</html>