<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LandingPage</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<table border="1">
    <tr>
        <td>Tytul zadania</td>
        <td>Autor rozwiazania</td>
        <td>Data dodania</td>
        <td>Wiecej</td>
    </tr>

    <h2>Ostatnio dodane rozwiazania:</h2>

    <c:forEach var="solution" items="${recent}">
        <tr>
            <td>${solution.exerciseTitle}</td>
            <td>${solution.username}</td>
            <td>${solution.created}</td>
            <td><a href="/solutionDetails?solutionId=${solution.id}">Szczegoly</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<jsp:include page="footer.jsp"/>

</body>
</html>
