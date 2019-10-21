<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>EditGroup</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<form method="post" action="/editGroup">
    Nowa nazwa grupy: <input type="text" name="newName">
    <input type="submit" value="OK">
    <input type="hidden" name="id" value="${group.id}">
</form>

<jsp:include page="../footer.jsp"/>
</body>
</html>
