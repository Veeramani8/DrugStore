<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sales Report Filter</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
</head>
<body>

<h2>Sales Report Filter</h2>

<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>
<br>
<a href="<c:url value='/reports' />">Back to Report</a>

<form:form action="${pageContext.request.contextPath}/reports/sales" method="post" modelAttribute="filter">
    <label>Start Date:</label>
    <form:input path="startDate" type="date" required="true"/><br/>
    
    <label>End Date:</label>
    <form:input path="endDate" type="date" required="true"/><br/>
    
    <button type="submit">Generate Report</button>
</form:form>

</body>
</html>
