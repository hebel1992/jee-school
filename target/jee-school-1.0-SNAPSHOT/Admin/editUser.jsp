<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>EditUser</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<h3>Edycja uzytkownika</h3>

<form method="post" action="/editUser">
    <input type="hidden" value="${user.id}" name="userId">
    Username: <input type="text" name="newName"><br/>
    Email: <input type="text" name="newEmail"><br/>
    Password: <input type="password" name="newPassword"><br/>
    Grupa: <select name="newGroupId">
        <option value="0">Bez grupy</option>
    <c:forEach var="item" items="${groups}">
        <option value="${item.id}">${item.name}</option>
    </c:forEach>
    <br/>
  <input type="submit" value="Zapisz zmiany">
</form>

<jsp:include page="../footer.jsp"/>
</body>
</html>
