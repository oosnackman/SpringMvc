<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var href = $(this).attr("href");
			$("#form1").attr("action", href).submit();
			return false;
		});
	})
</script>

</head>
<body>
	<h3>員工資訊</h3>
	<c:if test="${empty requestScope.employees }">
	  沒有任何員工資訊
	</c:if>
	<c:if test="${!empty requestScope.employees }">

		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<td>ID</td>
				<td>名字</td>
				<td>信箱</td>
				<td>性別</td>
				<td>生日</td>
				<td>薪資</td>

				<td>部門</td>
				<td colspan="3" align="middle">功能列表</td>

			</tr>

			<c:forEach items="${requestScope.employees}" var="emp">
				<tr>
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender == 0 ? '女生' : '男生' }</td>
					<td>${emp.birth }</td>
					<td>${emp.salary }</td>
					<td>${emp.department.departmentName }</td>
					<td><a href="emp">新增</a></td>
					<td><a href="emp/${emp.id}">修改</a></td>
					<td><a class="delete" href="emp/${emp.id}">刪除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


	<form action="" method="POST" id="form1">
		<input type="hidden" name="_method" value="DELETE" />
	</form>
	

</body>
</html>