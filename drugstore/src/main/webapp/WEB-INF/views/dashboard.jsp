<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css" />
    <style>
        /* CSS same as original */
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit" class="btn btn-logout">Logout</button>
</form>

<div class="user-profile">
    <h3>User Profile</h3>
    <p>Name: <span>${username}</span></p>
    <p>Role: <span>${role}</span></p>
</div>

<div class="tab-buttons">
    <c:if test="${role == 'ADMIN' || role == 'PHARMACIST'}">
        <button class="tab-btn" onclick="openTab('dashboardTab')">Dashboard</button>
        <button class="tab-btn" onclick="openTab('alertsTab')">Alerts</button>
        <button class="tab-btn" onclick="openTab('chartsTab')">Charts</button>
        <button class="tab-btn" onclick="openTab('statsTab')">Statistics</button>
    </c:if>
</div>

<!-- Admin Dashboard -->
<div id="dashboardTab" class="tab-content">
    <c:if test="${role == 'ADMIN'}">
        <h2>Admin Dashboard</h2>
        <div class="button-group">
            <a href="list" class="btn">Manage Users</a>
            <a href="drugs" class="btn">Manage Drugs</a>
            <a href="distributors" class="btn">Manage Distributors</a>
            <a href="stocks" class="btn">Manage Stocks</a>
            <a href="purchases" class="btn">Manage Purchases</a>
            <a href="orders" class="btn">Manage Orders</a>
            <a href="reports" class="btn">Analytics</a>
            <a href="attendance" class="btn">Attendance</a>
        </div>
    </c:if>

    <c:if test="${role == 'PHARMACIST'}">
        <h2>Pharmacist Dashboard</h2>
        <div class="button-group">
            <a href="drugs" class="btn">View Drugs</a>
            <a href="orders" class="btn">Handle Orders</a>
            <a href="purchases" class="btn">View Purchases</a>
            <a href="stocks" class="btn">View Stocks</a>
        </div>
    </c:if>
</div>

<!-- Alerts -->
<div id="alertsTab" class="tab-content">
    <h3>Alerts & Notifications</h3>
    <ul>
        <c:forEach var="alert" items="${lowStockAlerts}">
            <li>Low Stock: ${alert}</li>
        </c:forEach>
        <c:forEach var="expiry" items="${expiryAlerts}">
            <li>Near Expiry: ${expiry}</li>
        </c:forEach>
        <c:forEach var="order" items="${pendingOrders}">
            <li>Pending Order: ${order.id} - ${order.status}</li>
        </c:forEach>
    </ul>
</div>

<!-- Charts -->
<div id="chartsTab" class="tab-content">
    <h3>Sales Trends</h3>
    <canvas id="salesChart" width="600" height="300"></canvas>

    <h3>Top Selling Drugs</h3>
    <canvas id="topDrugsChart" width="600" height="300"></canvas>
</div>

<!-- Stats -->
<div id="statsTab" class="tab-content">
    <h3>Statistics Overview</h3>
    <ul>
        <li>Total Sales: ₹${totalSales}</li>
        <li>Stock Level: ${stockLevel} items</li>
        <li>Recent Orders: ${recentOrdersCount}</li>
        <li>Top Selling Drug: ${topDrugName}</li>
    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    const salesDates = ${salesDates};
    const salesValues = ${salesValues};

    function renderSalesChart() {
        const ctx = document.getElementById('salesChart').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: salesDates,
                datasets: [{
                    label: 'Sales',
                    data: salesValues,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'blue',
                    borderWidth: 2
                }]
            },
            options: { responsive: true }
        });
    }

    function renderTopDrugsChart() {
        fetch('dashboard-data/top-drugs')
            .then(res => res.json())
            .then(data => {
                const ctx2 = document.getElementById('topDrugsChart').getContext('2d');
                new Chart(ctx2, {
                    type: 'bar',
                    data: {
                        labels: data.drugs,
                        datasets: [{
                            label: 'Units Sold',
                            data: data.quantities,
                            backgroundColor: 'orange'
                        }]
                    }
                });
            });
    }

    function openTab(tabId) {
        const tabs = document.getElementsByClassName('tab-content');
        for (let i = 0; i < tabs.length; i++) {
            tabs[i].style.display = 'none';
        }

        const selectedTab = document.getElementById(tabId);
        selectedTab.style.display = 'block';

        if (tabId === 'chartsTab' && !window.salesChartRendered) {
            renderSalesChart();
            renderTopDrugsChart();
            window.salesChartRendered = true;
        }
    }

    window.onload = function () {
        openTab('dashboardTab');
    };
</script>
</body>
</html>
