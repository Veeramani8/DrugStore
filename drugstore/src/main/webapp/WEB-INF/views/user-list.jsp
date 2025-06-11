<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

<h2>All Users</h2>

<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>
<a href="<c:url value='/logout' />" class="btn btn-logout">Logout</a>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.username}" /></td>
                <td><c:out value="${user.role}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td>
                    <a href="<c:url value='/delete/${user.id}' />"
                       onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
