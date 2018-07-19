<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car Shop</title>
    <script>
        function validate() {
            var text = "";
            var login = document.forms["input"]["login"].value;
            if (login == "") {
                text += "Field 'login' must be filled out!\n";
            }
            var password = document.forms["input"]["password"].value;
            if (password == "") {
                text += "Field 'password' must be filled out!\n";
            }

            if (text != "") {
                alert(text);
                return false;
            }
        }
    </script>
    <style>
        p {
            font-size: larger;
        }

        body {
            position: absolute;
            top: 10%;
            left: 50%;
            margin-right: -50%;
            transform: translate(-50%, -50%)
        }

        table {
            background-color: aliceblue;
        }
    </style>
</head>
<body>
<table>
    <form name="input" action="signin" method="POST" onsubmit="return validate();">
        <tr>
            <td>
                Login : <input style="float: right; width: 150px" type="text" placeholder="Enter login" name="login"><br/>
            </td>
        </tr>
        <tr>
            <td>
                Password : <input style="width: 150px" type="password" placeholder="Enter password" name="password"><br/>
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