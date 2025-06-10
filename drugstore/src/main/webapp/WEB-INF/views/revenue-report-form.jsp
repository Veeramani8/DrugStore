<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Revenue Report Filter</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
</head>
<body>

    <h2>Revenue Report</h2>

    <form:form action="${pageContext.request.contextPath}/reports/revenue" modelAttribute="filter" method="post">
        <label>Start Date:</label>
        <form:input path="startDate" type="date" required="true"/>
        <br/><br/>
        <label>End Date:</label>
        <form:input path="endDate" type="date" required="true"/>
        <br/><br/>
        <button type="submit">Generate Report</button>
    </form:form>

    <br/><br/>

    <a href="<c:url value='/dashboard' />">← Back to Dashboard</a> |
    <a href="<c:url value='/reports' />">← Back to Reports</a>

</body>
</html>
