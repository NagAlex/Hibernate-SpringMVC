<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

	<h2>Here is the list of all groups:</h2>
	<table border="1">
		<tr>
			<td><b>ID</b></td>
			<td><b>IMAGE</b></td>
			<td><b>GROUP NAME</b></td>
			<th><b>ACTION</b></th>
		</tr>
		
		<c:forEach var="group" items="${groups}">
			<tr>
				<td>${group.id}</td>
				<c:choose ><c:when test="" ></c:when><c:otherwise></c:otherwise></c:choose>
				<td><img width="30"  height="30" border="0" align="middle"  
						 src='<s:url value="/resources/images/group${group.id}.jpg" />' 
						 onError="this.src='<s:url value="/resources/images"/>/group.jpg';" />
				</td>
				<td><a href='<s:url value="nagmvc/groups?group=${group.name}"/>'>${group.name}</a></td>
				<td align="center"> 
					<a href='<s:url value="nagmvc?delete&group=${group.name}"/>'>Delete</a>, 
					<a href='<s:url value="nagmvc/edit?group=${group.name}"/>'>Edit</a> 
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	
	<table>
		<tr>
			<td>
				<form action="nagmvc/new" method="GET">
					<input type="submit" value="Create new Group">
				</form>
			</td>
			<td>	
				<form action="nagmvc/user/new" method="GET">
					<input type="submit" value="Create new User">
				</form>
			</td>
		</tr>
	</table>