<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>EditExercise</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<form method="post" action="/editExercise">
    Nowy tytul zadania: <input type="text" name="newTitle">
    Nowy opis zadania: <input type="text", name="newDescription">
    <input type="submit" value="Zapisz zmiany">
    <input type="hidden" name="id" value="${exercise.id}">
</form>

<jsp:include page="../footer.jsp"/>
</body>
</html>
