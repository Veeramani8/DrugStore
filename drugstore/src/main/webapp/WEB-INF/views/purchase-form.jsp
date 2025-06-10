<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Purchase</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
</head>
<body>

    <h2>Add New Purchase</h2>

    <form:form action="${pageContext.request.contextPath}/purchases/save" modelAttribute="purchase" method="post">
        
        <label for="drug">Drug</label>
        <form:select path="drugId" required="required">
            <form:option value="" label="Select Drug" />
            <form:options items="${drugs}" itemValue="id" itemLabel="name" />
        </form:select>

        <label for="distributor">Distributor</label>
        <form:select path="distributorId" required="required">
            <form:option value="" label="Select Distributor" />
            <form:options items="${distributors}" itemValue="id" itemLabel="name" />
        </form:select>

        <label for="quantity">Quantity</label>
        <form:input path="quantity" type="number" required="required" />

        <label for="batch">Batch</label>
        <form:input path="batch" required="required" />

        <label for="purchaseDate">Purchase Date</label>
        <form:input path="purchaseDate" type="date" required="required" />

        <label for="expiryDate">Expiry Date</label>
        <form:input path="expiryDate" type="date" required="required" />

        <button type="submit" class="btn">Save Purchase</button>
        <a href="<c:url value='/purchases' />" class="back-link btn">Back to List</a>
        
    </form:form>

</body>
</html>
