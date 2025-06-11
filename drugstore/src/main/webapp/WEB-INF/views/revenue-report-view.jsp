<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Revenue Report</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

<h2>Revenue Report</h2>

<!-- Revenue Table -->
<table border="1">
    <tr>
        <th>Date</th>
        <th>Total Sales Amount</th>
        <th>Number of Orders</th>
        <th>Average Order Value</th>
    </tr>
    <c:forEach var="row" items="${report}">
        <tr>
            <td>${row.date}</td>
            <td>${row.totalSalesAmount}</td>
            <td>${row.numberOfOrders}</td>
            <td>${row.averageOrderValue}</td>
        </tr>
    </c:forEach>
</table>

<br/>
<div style="margin-top: 20px;">
<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>
    <a href="<c:url value='/reports' />"><button>Back to Report</button></a>
     <c:url var="downloadUrl" value="/reports/download-revenue">
    <c:param name="startDate" value="${filter.startDate}" />
    <c:param name="endDate" value="${filter.endDate}" />
</c:url>
<a href="${downloadUrl}">
    <button>Download PDF</button>
</a>

    
</div>

</body>
</html>
