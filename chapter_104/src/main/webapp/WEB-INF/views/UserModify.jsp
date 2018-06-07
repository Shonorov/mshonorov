<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            $("select[name='newcountry']").bind("change",
                function () {
                    $("select[name='newcity']").empty();
                    $.ajax({
                        type: "GET",
                        url: "./country",
                        datatype: "JSON",
                        data: {country : $("select[name='newcountry']").val()},
                        success: function (response) {
                            var data = response["cities"];
                            console.log(data);
                            if(data.length == 0) {
                                $("select[name='newcity']").append("<option value=''>Country not set</option>");
                            }
                            for (var i in data) {
                                $("select[name='newcity']").append($("<option value='" + data[i].name+ "'>" + data[i].name + "</option>"));
                            }
                        }
                    })
                }
            );
        });

        function validate() {
            var text = "";
            var newname = document.forms["input"]["newname"].value;
            if (newname == "") {
                text += "Field 'name' must be filled out!\n";
            }
            var newlogin = document.forms["input"]["newlogin"].value;
            if (newlogin == "") {
                text += "Field 'login' must be filled out!\n";
            }
            var newemail = document.forms["input"]["newemail"].value;
            if (newemail == "") {
                text += "Field 'email' must be filled out!\n";
            }
            var newpassword = document.forms["input"]["newpassword"].value;
            if (newpassword == "") {
                text += "Field 'password' must be filled out!\n";
            }
            var country = document.forms["input"]["newcountry"].value;
            console.log(country);
            if (country == "") {
                text += "Field 'country' must be filled out!\n";
            }
            var city = document.forms["input"]["newcity"].value;
            if (city == "") {
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
<p>Modify user:</p>
<c:set var="current" value="${user}"/>
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
        <form name="input" style="margin-bottom:0;" action="${pageContext.servletContext.contextPath}/edit" method="POST" onsubmit="return validate();">
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
            <td>
                <select name="newcountry">
                    <option value="${current.country}">${current.country}</option>
                    <c:forEach var="country" items="${countries}">
                        <c:choose>
                            <c:when test="${country.name != current.country}">
                                <option value="${country.name}">${country.name}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="newcity">
                    <option value="${current.city}">${current.city}</option>
                    <c:forEach var="city" items="${cities}">
                        <c:choose>
                            <c:when test="${city.name != current.city}">
                                <option value="${city.name}">${city.name}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
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
