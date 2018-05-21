<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users management</title>
</head>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<p>All users list:</p>
<table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>login</td>
        <td>email</td>
        <td>createDate</td>
        <td colspan='2'>
            <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/create" method="GET">
                <input type='submit' value='Create user'/>
            </form>
        </td>
    </tr>
    <c:forEach var="user" items="${users}">
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td>${user.createDate}</td>
        <td>
            <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/edit" method="GET">
                <input type="hidden" name="id" value="${user.id}">
                <input type='submit' value='Edit'>
            </form>
        </td>
        <td>
            <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/" method="POST">
                <input type="hidden" name="id" value="${user.id}">
                <input type='submit' value='Delete'>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
