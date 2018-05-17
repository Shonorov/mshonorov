<%@ page import="ru.job4j.users.User" %>
<%@ page import="ru.job4j.users.ValidateService" %>
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
<p>All users list:</p>
<table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>login</td>
        <td>email</td>
        <td>createDate</td>
        <td colspan='2'>
            <form style="margin-bottom:0;" action="<%=request.getContextPath()%>/UserCreate.jsp" method="GET">
                <input type='submit' value='Create user'/>
            </form>
        </td>
    </tr>
    <%for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form style="margin-bottom:0;" action="<%=request.getContextPath()%>/UserModify.jsp" method="GET">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type='submit' value='Edit'>
            </form>
        </td>
        <td>
            <form style="margin-bottom:0;" action="<%=request.getContextPath()%>/list" method="POST">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type='submit' value='Delete'>
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
