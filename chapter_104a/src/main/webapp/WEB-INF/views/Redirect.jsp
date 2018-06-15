<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Music store</title>
</head>
<body>
<c:out value='${message}'/><br/>
<meta http-equiv='Refresh' content='1;${pageContext.servletContext.contextPath}/${redirect}'>
To users list : <a href='${pageContext.servletContext.contextPath}/'>link</a>
</body>
</html>
