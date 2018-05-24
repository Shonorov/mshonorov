<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users management</title>
</head>
<p>Sign in:</p>
<body>
<c:if test="${error !=null}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<table>
    <form action="${pageContext.servletContext.contextPath}/signin" method="POST">
        <tr>
            <td>
                Login :<input type="text" placeholder="Enter login" name="login"><br/>
            </td>
        </tr>
        <tr>
            <td>
                Password :<input type="text" placeholder="Enter password" name="password"><br/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Login">
            </td>
        </tr>
    </form>
</table>
</body>
</html>
