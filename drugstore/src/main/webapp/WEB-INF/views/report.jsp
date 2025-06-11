<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/css/dashboard.css' />" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .button-group {
            margin-bottom: 20px;
        }
        .btn {
            padding: 8px 15px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-right: 10px;
            display: inline-block;
            font-weight: 600;
        }
        .btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>


<h2>Reports Dashboard</h2>

<div class="button-group">
    <a href="<c:url value='/reports/sales' />" class="btn">Sales Report</a>
    <a href="<c:url value='/reports/purchases' />" class="btn">Purchase Report</a>
    <a href="<c:url value='/reports/expiry' />" class="btn">Expiry Report</a>
    <a href="<c:url value='/reports/revenue' />" class="btn">Revenue Report</a>
</div>

<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>


</body>
</html>
