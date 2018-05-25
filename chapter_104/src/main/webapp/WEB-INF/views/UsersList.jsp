<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users management</title>
</head>
<form action="${pageContext.servletContext.contextPath}/signout" method="POST">
    <c:out value='${sessionScope.login}'/> : <c:out value='${sessionScope.role}'/>
    <input type='submit' value='Sign out'/>
</form>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<p>All users list:</p>
<c:if test="${error !=null}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<c:set var="error" value="${null}" scope="session"  />
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td>CreateDate</td>
        <td>Role</td>
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
        <td>${user.role}</td>
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
