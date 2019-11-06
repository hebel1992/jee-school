<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddGroup</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Uzupelnij dane nowej grupy</h3>
<form action="/addGroup" method="post">
    Nazwa grupy<input type="text" name="name" required>
    <input type="submit" value="Dodaj">
</form>
<jsp:include page="../footer.jsp"/>
</body>
</html>
