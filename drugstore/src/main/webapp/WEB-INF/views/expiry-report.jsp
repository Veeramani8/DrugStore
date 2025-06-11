<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Expiry Report</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

    <h2>Expiry Report</h2>
<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>
    <br/><br/>

    <table border="1">
        <thead>
            <tr>
                <th>Drug Name</th>
                <th>Batch</th>
                <th>Expiry Date</th>
                <th>Remaining Quantity</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${report}">
                <tr>
                    <td>${item.drugName}</td>
                    <td>${item.batch}</td>
                    <td>${item.expiryDate}</td>
                    <td>${item.quantityRemaining}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="<c:url value='/reports' />"> Back to Reports</a>
      <c:url var="downloadUrl" value="/reports/download-expiry">
    <c:param name="startDate" value="${filter.startDate}" />
    <c:param name="endDate" value="${filter.endDate}" />
</c:url>
<a href="${downloadUrl}">
    <button>Download PDF</button>
</a>

</body>
</html>
