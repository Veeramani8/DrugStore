<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit Drug</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

<h2>Edit Drug</h2>
<a href="${pageContext.request.contextPath}/drugs">← Back to Drug List</a>

<form:form method="post" action="${pageContext.request.contextPath}/drugs/update/${drugId}" modelAttribute="drug">
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

    <button type="submit">Update</button>
</form:form>

</body>
</html>
