<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteGroup</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<form method="post" action="/deleteGroup">
    Czy na pewno chcesz usunac grupe ${group.name}?
    <input type="submit" name="confirm" value="Tak">
    <input type="submit" name="decline" value="Nie">
    <input type="hidden" name="id" value="${group.id}">
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>
