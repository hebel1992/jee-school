<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DisplayExercises</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Zarzadanie zadaniami</h3>
<table border="1">
    <tr>
        <td>Tytul zadania</td>
        <td>Opis</td>
        <td>Akcja</td>
    </tr>
    <p><a href="/addExercise">Dodaj nowe zadanie</a></p>
    <c:forEach var="item" items="${exercises}">
        <tr>
            <td>${item.title}</td>
            <td>${item.description}</td>
            <td><a href="/editExercise?id=${item.id}">Edytuj</a>/<a
                    href="/deleteExercise?id=${item.id}">Usun</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../footer.jsp"/>
</body>
</html>
