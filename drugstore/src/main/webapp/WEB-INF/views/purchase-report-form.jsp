<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Purchase Report Filter</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/reports/purchases" method="post" modelAttribute="filter">
        <h2>Purchase Report - Filter</h2>
        <a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>

        <label>Start Date:</label>
        <form:input path="startDate" type="date" required="true" /><br/>
        <label>End Date:</label>
        <form:input path="endDate" type="date" required="true" /><br/>
        <button type="submit">Generate Report</button>
        <a href="${pageContext.request.contextPath}/reports">← Back to Report</a>
    </form:form>
</body>
</html>