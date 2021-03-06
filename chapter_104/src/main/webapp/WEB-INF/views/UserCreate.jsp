<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./style/style.css"><meta charset="UTF-8">
    <title>Users management</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("select[name='country']").bind("change",
                function () {
                    $("select[name='city']").empty();
                    $.ajax({
                        type: "GET",
                        url: "./country",
                        datatype: "JSON",
                        data: {country : $("select[name='country']").val()},
                        success: function (response) {
                            var data = response["cities"];
                            if(data.length == 0) {
                                $("select[name='city']").append("<option value=''>Country not set</option>");
                            }
                            for (var i in data) {
                                $("select[name='city']").append($("<option value='" + data[i].name+ "'>" + data[i].name + "</option>"));
                            }
                        }
                    })
                }
            );
        });

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
            var email = document.forms["input"]["email"].value;
            if (email == "") {
                text += "Field 'email' must be filled out!\n";
            }
            var password = document.forms["input"]["password"].value;
            if (password == "") {
                text += "Field 'password' must be filled out!\n";
            }
            var country = document.forms["input"]["country"].value;
            if (country == "Select country") {
                text += "Field 'country' must be filled out!\n";
            }
            var city = document.forms["input"]["city"].value;
            if (city == "Country not set") {
                text += "Field 'city' must be filled out!\n";
            }
            if (text != "") {
                alert(text);
                return false;
            }
        }
    </script>
</head>
<form class="login" action="${pageContext.servletContext.contextPath}/signout" method="POST">
    <c:out value='${sessionScope.login}'/> : <c:out value='${sessionScope.role}'/>
    <input type='submit' value='Sign out'/>
</form>
<body>
<p>Create new user:</p>
<table>
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td>Password</td>
        <td>Role</td>
        <td>Country</td>
        <td>City</td>
        <td></td>
    </tr>
    <tr>
        <form name="input" style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/create" method="POST" onsubmit="return validate();">
            <td><input type='text' placeholder='Enter name' name='name'></td>
            <td><input type='text' placeholder='Enter login' name='login'></td>
            <td><input type='text' placeholder='Enter email' name='email'></td>
            <td><input type='text' placeholder='Enter password' name='password'></td>
            <td>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="country">
                    <option value="Select country">Select country</option>
                    <c:forEach var="country" items="${countries}">
                        <option value="${country.name}">${country.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="city">
                    <option value="Country not set">Country not set</option>
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
