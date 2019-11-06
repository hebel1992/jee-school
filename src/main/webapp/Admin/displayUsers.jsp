<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DisplayGroups</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h3>Zarzadanie uzytkownikami</h3>
<font color="red"> ${not empty param.error ? param.error : ""}</font>
<table border="1">
    <tr>
        <td>Nazwa uzytkownika</td>
        <td>Email</td>
        <td>Grupa</td>
        <td>Akcje</td>
    </tr>
    <p><a href="/addUser">Dodaj nowego uzytkownika</a></p>
    <c:forEach var="item" items="${users}">
        <tr>
            <td>${item.username}</td>
            <td>${item.email}</td>
            <td>${item.group_id!=0 ? item.group_name : "brak grupy"}</td>
            <td><a href="/editUser?id=${item.id}">Edytuj</a>/<a
                    href="/deleteUser?id=${item.id}">Usun</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../footer.jsp"/>
</body>
</html>