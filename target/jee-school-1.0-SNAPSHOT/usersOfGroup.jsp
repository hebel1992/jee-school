<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>UserOfGroup</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h3>Lista uzytkownikow grupy: ${groupName}</h3>
<c:if test="${size==0}">
    <p>Grupa nie posiada jeszcze zadnych uzytkownikow!</p>
</c:if>
<c:if test="${size!=0}">
    <table border="1">
        <tr>
            <td>Nazwa</td>
            <td>Akcje</td>
        </tr>
        <c:forEach var="item" items="${users}">
            <tr>
                <td>${item.username}</td>
                <td><a href="/userDetails?param=${item.id}">Szczegoly</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<jsp:include page="footer.jsp"/>
</body>
</html>
