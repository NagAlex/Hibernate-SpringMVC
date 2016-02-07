<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html>
	<head>
        <title>Spring+Hibernate+MVC Homework</title>
        <link href='<s:url value="/resources" />/css/nag.css' 
          rel="stylesheet" 
          type="text/css" />
        
    </head>

    <body>
        <div id="container">
             <a href="<s:url value="/home" />">
                <img src='<s:url value="/resources" />/images/logo_50.png' border="0"/></a>
            <div id="top">
                <t:insertAttribute name="top" /> 
            </div>
            <div id="side">
                <t:insertAttribute name="side" /> 
            </div>
            <div id="content">
                <t:insertAttribute name="content" />
            </div>
		</div>
	</body>
</html>