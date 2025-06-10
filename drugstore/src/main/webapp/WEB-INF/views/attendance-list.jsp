<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><html>
<head>
    <title>Attendance List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" />
</head>
<body>
    <h2>Attendance Records</h2>
    <a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Date</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Total Hours</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${attendances}">
                <tr>
                    <td>${record.id}</td>
                    <td>${record.fullName}</td>
                    <td>${record.date}</td>
                    <td>${record.checkIn}</td>
                    <td>${record.checkOut}</td>
                    <td>${record.totalHours}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="${pageContext.request.contextPath}/attendance/mark">Mark New Attendance</a>
</body>
</html>
