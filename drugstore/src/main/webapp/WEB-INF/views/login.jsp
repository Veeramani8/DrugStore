<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login - Drug Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    <h2>Login</h2>
    <div>
        <label>Username:</label>
        <input type="text" name="username" required />
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password" required />
    </div>
    <div>
        <button type="submit">Login</button>
    </div>
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>

    <c:if test="${param.error != null}">
        <p style="color:red;">Invalid username or password.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p style="color:green;">You have been logged out.</p>
    </c:if>
</form>

</body>
</html>
