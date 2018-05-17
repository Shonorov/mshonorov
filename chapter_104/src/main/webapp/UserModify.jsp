<%@ page import="ru.job4j.users.ValidateService" %>
<%@ page import="ru.job4j.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%User current = ValidateService.getInstance().findById(request.getParameter("id")).get();%>
<table>
    <tr>
        <td>
            <form style="margin-bottom:0;" action="<%=request.getContextPath()%>/edit" method="POST">
                <input type="text" value="<%=current.getName()%>" name="newname">
                <input type="text" value="<%=current.getLogin()%>" name="newlogin">
                <input type="text" value="<%=current.getEmail()%>" name="newemail">
                <input type="submit" value="Apply">
                <input type="hidden" name="id" value="<%=current.getId()%>">
            </form>
        </td>

    </tr>
</table>
<td>
    <form style="margin-bottom:0;" action="<%=request.getContextPath()%>/UsersList.jsp" method="GET">
        <input type='submit' value='To list'>
    </form>
</td>
</body>
</html>
