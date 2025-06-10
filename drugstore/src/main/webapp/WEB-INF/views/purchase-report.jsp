<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Purchase Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" />
</head>
<body>
    <h2>Purchase Report</h2>
    <a href="${pageContext.request.contextPath}/dashboard">← Back to Dashboard</a>

    <table border="1">
        <thead>
            <tr>
                <th>Drug Name</th>
                <th>Quantity Purchased</th>
                <th>Total Cost</th>
                <th>Purchase Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${report}">
                <tr>
                    <td>${item.drugName}</td>
                    <td>${item.quantityPurchased}</td>
                    <td>${item.totalCost}</td>
                    <td>${item.purchaseDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/reports/purchases">Back</a>
</body>
</html>
