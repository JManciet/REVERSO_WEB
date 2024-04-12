<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@ include file="WEB-INF/menu.jsp" %>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="reverso/hello-servlet">Hello Servlet</a><p><c:out value="Bonjour !" /></p>
</body>
</html>
