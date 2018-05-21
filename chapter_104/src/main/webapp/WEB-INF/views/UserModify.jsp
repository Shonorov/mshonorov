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
<p>Modify user:</p>
<c:set var="current" value="${user}"/>
<table>
    <tr>
        <td>
            <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/edit" method="POST">
                <input type="text" value="${current.name}" name="newname">
                <input type="text" value="${current.login}" name="newlogin">
                <input type="text" value="${current.email}" name="newemail">
                <input type="submit" value="Apply">
                <input type="hidden" name="id" value="${current.id}">
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
