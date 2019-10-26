<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DisplayGroups</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Zarzadanie grupami uzytkownikow</h3>
<table border="1">
    <tr>
        <td>Nazwa grupy</td>
        <td>Akcje</td>
    </tr>
    <p><a href="/addGroup">Dodaj nowa</a></p>
    <c:forEach var="item" items="${groups}">
        <tr>
            <td>${item.name}</td>
            <td><a href="/editGroup?id=${item.id}">Edytuj</a>/<a
                    href="/deleteGroup?id=${item.id}">Usun</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../footer.jsp"/>
</body>
</html>
