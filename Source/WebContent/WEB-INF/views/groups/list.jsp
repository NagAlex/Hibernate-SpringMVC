<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h2>Users of group &lt;${group}&gt; </h2> <!-- Вывод имени группы -->
	<table border="1" >
		<tr>
			<td><strong>Id</strong></td>
			<td><strong>First Name</strong></td>
			<td><strong>Last Name</strong></td>
			<th><b>E-mail</b></th>
			<th><b>Action</b></th>
		</tr>
		<c:forEach var="user" items="${userList}"> <!-- Вывод списка пользователей данной группы -->
			<tr>
<%-- 				<td>
				<img src="<s:url value="/resources/${group.id}.jpg"/>"
				width="48" height="48" /></td>
--%>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.firstName}" />
<%--  				<a href="<s:url value="/groups/${group.users.username}"/>">
 --%>				
				</td>
				<td><c:out value="${user.lastName}" /></td>
				<td><c:out value="${user.email}" /></td>
				<td align="center"> 
					<a href='<s:url value="user/delete?groupName=${group}&user=${user.id}" />'>Delete</a>, 
					<a href='<s:url value="user/edit?groupName=${group}&user=${user.id}"/>'>Edit</a> 
				</td>
			</tr>	
		</c:forEach>
	</table>
	<br/>
	<form action="user/new" method="GET">
		<input type="hidden" name="groupname" value="${group}">
		<input type="submit" value="Create new User">
	</form>
</div>
