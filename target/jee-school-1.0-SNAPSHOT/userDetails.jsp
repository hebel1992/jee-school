<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserDetails</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h2>Szczegoly uzytkownika: ${user.username}</h2>

Nazwa: ${user.username}<br/>
Email: ${user.email}

<h3>Dodane rozwiazania zadan:</h3>
<c:if test="${size==0}">
    <p>Brak dodanych rozwiazan!</p>
</c:if>
<c:if test="${size!=0}">
    <table border="1">
        <tr>
            <td>Tytul zadania</td>
            <td>Data dodania</td>
            <td></td>
        </tr>
        <c:forEach var="item" items="${userSolutions}">
            <tr>
                <td>${item.exerciseTitle}</td>
                <td>${item.created}</td>
                <td><a href="/solutionDetails?solutionId=${item.id}">Szczegoly</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<br/>

<jsp:include page="footer.jsp"/>
</body>
</html>
