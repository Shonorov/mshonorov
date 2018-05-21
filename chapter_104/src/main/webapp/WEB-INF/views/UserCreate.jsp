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
<p>Create new user:</p>
<table>
    <tr>
        <td>
            <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/create" method="POST">
                <input type='text' placeholder='Enter name' name='name'>
                <input type='text' placeholder='Enter login' name='login'>
                <input type='text' placeholder='Enter email' name='email'>
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
