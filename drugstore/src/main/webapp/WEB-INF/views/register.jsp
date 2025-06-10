<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
</head>
<body>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form:form method="post" action="${pageContext.request.contextPath}/register" modelAttribute="user">
    <h2>User Registration</h2>

    <label>Full Name:</label>
    <form:input path="fullName" required="true" /><br/>

    <label>Email:</label>
    <form:input path="email" type="email" required="true" /><br/>

    <label>Username:</label>
    <form:input path="username" required="true" /><br/>

    <label>Password:</label>
    <form:password path="password" required="true" /><br/>

    <label>Role:</label>
    <form:select path="role" required="true">
        <form:option value="">--Select Role--</form:option>
        <form:option value="PHARMACIST">Pharmacist</form:option>
        <form:option value="STAFF">Staff</form:option>
        <form:option value="ADMIN">Admin</form:option>
    </form:select><br/>

    <button type="submit">Send OTP</button>

    <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
</form:form>

</body>
</html>
