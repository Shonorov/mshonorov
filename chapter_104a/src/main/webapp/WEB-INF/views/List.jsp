<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./style/style.css"><meta charset="UTF-8">
    <title>Music store</title>
</head>
<body>
<form class="login" action="${pageContext.servletContext.contextPath}/signout" method="POST">
    <c:out value='${sessionScope.login}'/> : <c:out value='${sessionScope.role}'/>
    <input type='submit' value='Sign out'/>
</form>
<p>All users list:</p>
<c:if test="${error !=null}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form class="search" action="${pageContext.servletContext.contextPath}/" method="GET">
    <input type="text" name="search" placeholder="Search text...">
    <input type="submit" value="Find">
    <form action="${pageContext.servletContext.contextPath}/" method="GET">
        <input type='submit' value='Clear filter'>
    </form>
</form>

<c:set var="error" value="${null}" scope="session"/>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Login</td>
        <td>Address</td>
        <td>Music styles</td>
        <td>Role</td>
        <td colspan='2'>
            <form style="margin-bottom:0; height: 100%" action="${pageContext.servletContext.contextPath}/create" method="GET">
                <input style="width: 100%" type='submit' value='Create user'/>
            </form>
        </td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.address}</td>
            <td>${user.styles}</td>
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
