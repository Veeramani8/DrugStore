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

        <div class="description">
            <p>
                Welcome to the Drug Store Management System – a comprehensive web-based platform designed to streamline and automate the end-to-end operations of a pharmacy or drugstore. This system offers powerful modules that ensure efficiency, accuracy, and real-time tracking across all core functions.
            </p>


        <div class="button-group">
            <a href="<c:url value='/login' />"><button class="btn">Login</button></a>
            <a href="<c:url value='/register' />"><button class="btn">Register</button></a>
        </div>
    </div>

</body>
</html>
