<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



</head>
<body>

	<%
		Map<String, String> genders = new HashMap();
		genders.put("1", "男生");
		genders.put("0", "女生");
		request.setAttribute("genders", genders);
	%>

	<!-- modelAttribute 會自動綁定JavaBean -->
	<form:form action="${pageContext.request.contextPath }/emp"
		method="POST" modelAttribute="employee">

		<c:if test="${employee.id == null }">
			姓名: <form:input path="lastName" />
		</c:if>
		<c:if test="${employee.id != null }">
		姓名:<form:input path="lastName" disabled="true" />
			<form:hidden path="id" />
			<!-- _method 不能使用 form:hidden標籤 , 因為modelAttribute對應的JavaBean沒有_method這個屬性 -->
			<input type="hidden" name="_method" value="PUT" />

		</c:if>
		<br>
		信箱:<form:input path="email" />
		<br>
		性別:<form:radiobuttons path="gender" items="${genders}" />
		<br>
	    生日:<form:input path="birth"/>
		<br>
		薪資:<form:input path="salary"/>
		<br>
		部門:<form:select path="department.id" items="${departments}"
			itemLabel="departmentName" itemValue="id" />
		<br>
		<input type="submit" value="送出">
		<br>
	</form:form>


</body>
</html>