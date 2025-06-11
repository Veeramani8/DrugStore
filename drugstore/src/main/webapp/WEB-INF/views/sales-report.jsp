<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sales Report</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

<h2>Sales Report</h2>
<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>

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


<a href="<c:url value='/reports/sales' />"> Back to Report</a>
  <c:url var="downloadUrl" value="/reports/download-sales">
    <c:param name="startDate" value="${filter.startDate}" />
    <c:param name="endDate" value="${filter.endDate}" />
</c:url>
<a href="${downloadUrl}">
    <button>Download PDF</button>
</a>

</body>
</html>
