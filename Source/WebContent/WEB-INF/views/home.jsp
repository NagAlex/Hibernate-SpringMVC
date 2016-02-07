<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<h2>Here is the list of all users:</h2>
<%-- 	<form action="${pageContext.request.contextPath}/users" method="post">
		<input name="firstName" placeholder="First Name">
		<input name="lastName" placeholder="Last Name">
		<input name="email" type="email" placeholder="Email">

		<input type="submit" value="Submit">
	</form> --%>
	<table border="1">
		<tr>
			<td><b>ID</b></td>
			<td><b>FIRST NAME</b></td>
			<td><b>LAST NAME</b></td>
			<td><b>EMAIL</b></td>
			<td><b>GROUP</b></td>
		</tr>
		
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<td>${user.group.name}</td>
			</tr>
		</c:forEach>
	</table>
