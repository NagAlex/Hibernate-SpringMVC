<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<h2>Create/Edit group</h2>
	<sf:form method="POST" modelAttribute="group" 
			enctype="multipart/form-data">	<!-- Связать форму с аттрибутом модели, multipart/form-data - Content-Type передаваемых данных для возможности передачи файла --> 
		<fieldset>
			<sf:errors path="*" cssClass="errorblock" element="div"/>  <!-- Ообразит все сообщения об ошибках для всех полей вверху формы -->
			<sf:hidden path="id" />
			<table>
				<tr valign="top">
					<th><label for="group_name">Group name:</label></th>
					<td><sf:input path="name" id="group_name"/>
						<small id="groupname_msg">No spaces, please.</small><br/>
						<sf:errors path="name" cssClass="error"/>   <!-- Сообщить об ошибке в поле name -->
						<!-- delimiter=", " - разделяет сообщения об ошибках зяпятой и пробелом. По умолчанию разделитель - <br/> -->
					</td>
				</tr>
				<tr valign="top">
					<th><label for="image">Group image:</label></th>
					<td><input name="image" type="file" /></td>
				</tr>
				<tr valign="top">
					<th></th>
					<td><input name="commit" type="submit" value="Create/Edit group" /></td>
				</tr>
			</table>
		</fieldset>	
	</sf:form>
</div>
</body>
</html>