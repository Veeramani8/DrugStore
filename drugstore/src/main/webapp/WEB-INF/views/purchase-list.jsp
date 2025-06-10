<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Purchase List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" />
</head>
<body>
    <h2>Purchase List</h2>
    <div class="top-actions">
        <a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>
        <a href="${pageContext.request.contextPath}/purchases/add" class="btn">Add New Purchase</a>
        <a href="${pageContext.request.contextPath}/purchases/pdf" target="_blank">
            <button type="button">Download PDF</button>
        </a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Drug</th>
                <th>Distributor</th>
                <th>Quantity</th>
                <th>Batch</th>
                <th>Purchase Date</th>
                <th>Expiry Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="purchase" items="${purchases}">
                <tr>
                    <td>${purchase.id}</td>
                    <td>${purchase.drugName}</td>
                    <td>${purchase.distributorName}</td>
                    <td>${purchase.quantity}</td>
                    <td>${purchase.batch}</td>
                    <td>${purchase.purchaseDate}</td>
                    <td>${purchase.expiryDate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/purchases/delete/${purchase.id}" class="btn">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
