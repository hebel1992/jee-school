<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AddUser</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Uzupelnij dane nowego uzytkownika</h3>
<form action="/addUser" method="post">
    Nazwa uzytkownika: <input type="text" name="name">
    Email: <input type="text" name="email">
    Haslo: <input type="password" name="password">
    Grupa: <select name="groupId" >
        <option value="0">Bez grupy</option>
    <c:forEach var="item" items="${groups}">
        <option value="${item.id}">${item.name}</option>
    </c:forEach>
    <br />
    <input type="submit" value="Dodaj">
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>
