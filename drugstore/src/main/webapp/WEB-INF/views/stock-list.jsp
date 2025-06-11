<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stock Inventory</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

<h2>Stock Inventory</h2>

<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>
<a href="<c:url value='/stocks/pdf' />" target="_blank">
    <button type="button">Download Stock PDF</button>
</a>

<table border="1">
    <thead>
        <tr>
            <th>Drug Name</th>
            <th>Batch</th>
            <th>Quantity</th>
            <th>Expiry Date</th>
            <th>Last Updated</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="stock" items="${stocks}">
            <tr>
                <td><c:out value="${stock.drugName}" /></td>
                <td><c:out value="${stock.batch}" /></td>
                <td><c:out value="${stock.quantity}" /></td>
                <td><c:out value="${stock.expiryDate}" /></td>
                <td><c:out value="${stock.lastUpdated}" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
