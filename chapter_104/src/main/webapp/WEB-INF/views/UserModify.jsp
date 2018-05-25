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
<p>Modify user:</p>
<c:set var="current" value="${user}"/>
<table>
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td>Password</td>
        <td>Role</td>
        <td></td>
    </tr>
    <tr>
        <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/edit" method="POST">
            <td><input type="text" value="${current.name}" name="newname"></td>
            <td><input type="text" value="${current.login}" name="newlogin"></td>
            <td><input type="text" value="${current.email}" name="newemail"></td>
            <td><input type="text" placeholder='Enter new password' name="newpassword"></td>
            <td><select name="newrole">
                <option value="${current.role}">${current.role}</option>
                <c:forEach var="role" items="${roles}">
                    <c:choose>
                        <c:when test="${role != current.role}">
                            <option value="${role}">${role}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
            <td><input type="submit" value="Apply"></td>
            <input type="hidden" name="id" value="${current.id}">
        </form>
    </tr>
</table>
<td>
    <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/" method="GET">
        <input type='submit' value='To list'>
    </form>
</td>
</body>
</html>
