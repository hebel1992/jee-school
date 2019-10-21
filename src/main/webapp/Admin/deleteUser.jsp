<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteUser</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<form method="post" action="/deleteUser">
    Czy na pewno chcesz usunac uzytkownika ${user.username}?
    <input type="submit" name="confirm" value="Tak">
    <input type="submit" name="decline" value="Nie">
    <input type="hidden" name="id" value="${user.id}">
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>
