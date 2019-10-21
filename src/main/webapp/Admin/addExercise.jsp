<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddExercise</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Uzupelnij dane nowego zadania</h3>
<form action="/addExercise" method="post">
    Nazwa zadania<input type="text" name="name">
    Opis zadania<input type="text" name="description">
    <input type="submit" value="Dodaj">
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>
