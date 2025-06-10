<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Mark Attendance</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
</head>
<body>
    <h2>Mark Attendance</h2>
    <a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>

    <form:form action="${pageContext.request.contextPath}/attendance/save" modelAttribute="attendanceRequestDTO" method="post">
        <label>User ID:</label>
        <form:input path="userId" type="number" required="true" /><br/><br/>

        <label>Date:</label>
        <form:input path="date" type="date" required="true" /><br/><br/>

        <label>Check In:</label>
        <form:input path="checkIn" type="time" /><br/><br/>

        <label>Check Out:</label>
        <form:input path="checkOut" type="time" /><br/><br/>

        <button type="submit">Submit</button>
    </form:form>

    <br/>
    <a href="${pageContext.request.contextPath}/attendance">View All Attendance</a>
</body>
</html>
