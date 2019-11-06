<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<table border="1">
    <tr>
        <td><strong>Nazwa grupy</strong></td>
        <td><strong>Akcje</strong></td>
    </tr>

    <h2>Lista grup:</h2>
    <c:if test="${empty groups}">
        <p>Nie znaleziono grup!</p>
    </c:if>
    <c:forEach var="items" items="${groups}">
        <tr>
            <td>${items.name}</td>
            <td><a href="/usersOfGroup?param=${items.id}">Uzytkownicy</a></td>
        </tr>
    </c:forEach>
</table><br />
<jsp:include page="footer.jsp"/>
</body>
</html>
