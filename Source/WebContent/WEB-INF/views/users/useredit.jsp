<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<style>
	.error {
		color: #ff0000;
	}
	
	.errorblock {
		color: #000;
		background-color: #ffEEEE;
		border: 3px solid #ff0000;
		padding: 8px;
		margin: 16px;
	}
</style>
</head>

<body>
<div>
	<h2>Create/Edit user</h2>
	<sf:form method="POST" modelAttribute="user" enctype="multipart/form-data">	 
		<fieldset>
			<sf:hidden path="id"/>
			<sf:errors path="*" cssClass="errorblock" element="div"/>  <!-- Ообразит все сообщения об ошибках для всех полей вверху формы -->
			<table>
				<tr valign="top">
					<th><label for="first_name">First name:</label></th>
					<td><sf:input path="firstName" id="first_name"/>
						<small id="firstname_msg">No spaces, please.</small><br/>
						<sf:errors path="firstName" cssClass="error"/>   <!-- Сообщить об ошибке в поле firstName -->
					</td>
				</tr>
				<tr valign="top">
					<th><label for="last_name">Last name:</label></th>
					<td><sf:input path="lastName" id="last_name"/>
						<small id="lastname_msg">No spaces, please.</small><br/>
						<sf:errors path="lastName" cssClass="error"/>   <!-- Сообщить об ошибке в поле lastName -->
					</td>
				</tr>
				<tr valign="top">
					<th><label for="email">Email:</label></th>
					<td><sf:input path="email" id="email"/>
						<small id="email_msg">Enter correct email, please.</small><br/>
						<sf:errors path="email" cssClass="error"/>   <!-- Сообщить об ошибке в поле email -->
					</td>
				</tr>
				<tr valign="top">
					<th><label for="group_id">Group:</label></th>
					<td><select name="groupselect" id="group_id" size="1" required="required">
							<option value="NONE">---Select One---</option>
							<c:forEach var="group" items="${groups}">
								<option value="${group.name}"
									<c:if test="${group.name eq groupname}"> selected="selected" </c:if> 
									>${group.name}</option>
							</c:forEach>
						</select><br/>
						
						<%-- <sf:select path="group" id="group_id" size="1">
							<sf:option value="0" label="---Select One---" />
							<sf:options items="${groups}" itemLabel="name"/>
						</sf:select> --%>
						<sf:errors path="group" cssClass="error"/>   <!-- Сообщить об ошибке в поле group -->
					</td>
				</tr>
				<tr valign="top">
					<th></th>
					<td><input name="commit" type="submit" value="Create/Edit user" /></td>
				</tr>
			</table>
		</fieldset>	
	</sf:form>
</div>
</body>
</html>