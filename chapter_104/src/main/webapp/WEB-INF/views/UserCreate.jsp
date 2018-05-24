<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<p>Create new user:</p>
<table>
    <tr>
        <td>
            <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/create" method="POST">
                <input type='text' placeholder='Enter name' name='name'>
                <input type='text' placeholder='Enter login' name='login'>
                <input type='text' placeholder='Enter email' name='email'>
                <input type='text' placeholder='Enter password' name='password'>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
                <input type='submit' value='Create'>
            </form>
        </td>
    </tr>
</table>
<td>
    <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/" method="GET">
        <input type='submit' value='To list'>
    </form>
</td>
</body>
</html>
