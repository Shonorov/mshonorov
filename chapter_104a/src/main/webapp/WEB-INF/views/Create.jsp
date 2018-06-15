<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./style/style.css"><meta charset="UTF-8">
    <title>Music store</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var text = "";
            var name = document.forms["input"]["name"].value;
            if (name == "") {
                text += "Field 'name' must be filled out!\n";
            }
            var login = document.forms["input"]["login"].value;
            if (login == "") {
                text += "Field 'login' must be filled out!\n";
            }
            var password = document.forms["input"]["password"].value;
            if (password == "") {
                text += "Field 'password' must be filled out!\n";
            }
            var address = document.forms["input"]["address"].value;
            if (address == "") {
                text += "Field 'address' must be filled out!\n";
            }
            if (text != "") {
                alert(text);
                return false;
            }
        }
    </script>
</head>
<body>
<form class="login" action="${pageContext.servletContext.contextPath}/signout" method="POST">
    <c:out value='${sessionScope.login}'/> : <c:out value='${sessionScope.role}'/>
    <input type='submit' value='Sign out'/>
</form>
<p>Create user:</p>
<table>
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Password</td>
        <td>Address</td>
        <td>Music styles</td>
        <td>Role</td>
        <td></td>
    </tr>
    <tr>
        <form name="input" style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/create" method="POST" onsubmit="return validate();">
            <td><input type='text' placeholder='Enter name' name='name'></td>
            <td><input type='text' placeholder='Enter login' name='login'></td>
            <td><input type='text' placeholder='Enter password' name='password'></td>
            <td><input type='text' placeholder='Enter address' name='address'></td>
            <td>
                <c:forEach var="style" items="${styles}">
                    <input type="checkbox" name="${style.style}"><label>${style.style}</label>
                </c:forEach>
            </td>
            <td>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.role}">${role.role}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type='submit' value='Create'></td>
        </form>
    </tr>
</table>
<div>
    <form style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/" method="GET">
        <input type='submit' value='To list'>
    </form>
</div>
</body>
</html>
