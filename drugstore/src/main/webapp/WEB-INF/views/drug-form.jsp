<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Drug</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
</head>
<body>

<h2>Add New Drug</h2>

<a href="<c:url value='/drugs' />">Back to Drug List</a>

<form:form action="${pageContext.request.contextPath}/drugs/add" method="post" modelAttribute="drug">
    <label>Name:</label>
    <form:input path="name" required="true" /><br/>

    <label>Composition:</label>
    <form:input path="composition" required="true" /><br/>

    <label>Dosage:</label>
    <form:input path="dosage" required="true" /><br/>

    <label>Manufacturer:</label>
    <form:input path="manufacturer" required="true" /><br/>

    <label>Price:</label>
    <form:input path="price" type="number" step="0.01" required="true" /><br/>

    <button type="submit">Save</button>
</form:form>

</body>
</html>
