<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteExercise</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<form method="post" action="/deleteExercise">
    Czy na pewno chcesz usunac zadanie ${exercise.title}?
    Uwaga: wraz z usunieciem zadania, zostana tez usuniete wszystkie jego rozwiazania!
    <input type="submit" name="confirm" value="Tak">
    <input type="submit" name="decline" value="Nie">
    <input type="hidden" name="id" value="${exercise.id}">
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>
