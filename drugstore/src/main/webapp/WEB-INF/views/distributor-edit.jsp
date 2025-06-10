<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><html>
<head>
    <title>Edit Distributor</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h2>Edit Distributor</h2>

<form:form action="${pageContext.request.contextPath}/d/update/${distributor.id}" method="post" modelAttribute="distributor">
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

    <button type="submit">Update Distributor</button>
</form:form>

<a href="${pageContext.request.contextPath}/distributors"><button>Back to List</button></a>
</body>
</html>
