<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Drug Store</title>
       <link rel="stylesheet" href="<c:url value='/css/home.css' />" />

</head>
<body>

    <div class="home-container">
        <h1>Drug Store Management System</h1>

        <div class="button-group">
            <a href="<c:url value='/login' />"><button class="btn">Login</button></a>
            <a href="<c:url value='/register' />"><button class="btn">Register</button></a>
        </div>
    </div>

</body>
</html>
