<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sales Report</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

<h2>Sales Report</h2>
<a href="<c:url value='/dashboard' />">← Back to Dashboard</a>

<table border="1">
    <thead>
        <tr>
            <th>Drug Name</th>
            <th>Quantity Sold</th>
            <th>Total Amount</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${report}">
            <tr>
                <td>${item.drugName}</td>
                <td>${item.totalQuantitySold}</td>
                <td>${item.totalRevenue}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="chart-container">
    <h2>Top 5 Drugs by Sales</h2>
    <canvas id="salesPieChart"></canvas>
</div>

<!-- Optionally include Chart.js for pie chart rendering -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    // You can dynamically inject JS data for pie chart here using JSTL if needed
</script>

<a href="<c:url value='/reports/sales' />">← Back to Report</a>

</body>
</html>
