<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Distributor</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
</head>
<body>
<h2>Add New Distributor</h2>

<form:form action="${pageContext.request.contextPath}/distributors/save" method="post" modelAttribute="distributor">
    <label>Name:</label>
    <form:input path="name" required="true" /><br/>

    <label>Email:</label>
    <form:input path="email" type="email" required="true" /><br/>

    <label>Phone:</label>
    <form:input path="phone" required="true" /><br/>

    <label>Address:</label>
    <form:input path="address" required="true" /><br/>

    <label>License Number:</label>
    <form:input path="licenseNumber" required="true" /><br/>

    <button type="submit">Save Distributor</button>
</form:form>

<a href="<c:url value='/distributors' />"><button>Back to List</button></a>
</body>
</html>
