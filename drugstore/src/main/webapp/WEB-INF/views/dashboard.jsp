<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js" defer></script>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
            height: 100vh;
        }
        #sidebar {
            width: 220px;
            background-color: #2c3e50;
            color: white;
            display: flex;
            flex-direction: column;
            padding-top: 20px;
        }
        #sidebar h2 {
            text-align: center;
            margin-bottom: 20px;
            font-weight: normal;
        }
        #sidebar button {
            background: none;
            border: none;
            color: white;
            padding: 15px 20px;
            text-align: left;
            font-size: 16px;
            cursor: pointer;
            outline: none;
            transition: background 0.3s;
        }
        #sidebar button:hover, #sidebar button.active {
            background-color: #34495e;
        }
        #main-content {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
            background-color: #ecf0f1;
            margin-top: 60px;
        }
        #topbar {
            position: fixed;
            top: 0;
            right: 0;
            height: 60px;
            width: calc(100% - 220px);
            background-color: #fff;
            border-bottom: 1px solid #ddd;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding: 0 20px;
            box-sizing: border-box;
            font-weight: bold;
            color: #2c3e50;
            z-index: 1000;
        }
        .alert-list {
            list-style-type: none;
            padding-left: 0;
        }
        .alert-list li {
            margin-bottom: 8px;
            padding: 8px 12px;
            border-radius: 4px;
        }
        .alert-lowstock { background-color: #f9e79f; color: #7d6608; }
        .alert-expiry { background-color: #f5b7b1; color: #7b241c; }
        .alert-pendingorder { background-color: #aed6f1; color: #154360; }
    </style>
</head>
<body>

<script>
    const CONTEXT_PATH = '${pageContext.request.contextPath}';
</script>

<div id="sidebar">
    <h2>Modules</h2>
    <button class="module-btn" data-module="alerts">Alerts</button>
    <button class="module-btn" data-module="charts">Statistiics and Charts</button>
    <c:if test="${role == 'ADMIN'}">
        <button class="module-btn" data-module="user">Users</button>
        <button class="module-btn" data-module="drugs">Drugs</button>
        <button class="module-btn" data-module="distributors">Distributors</button>
        <button class="module-btn" data-module="stocks">Stocks</button>
        <button class="module-btn" data-module="orders">Orders</button>
        <button class="module-btn" data-module="purchases">Purchases</button>
        <button class="module-btn" data-module="reports">Reports</button>
        <button class="module-btn" data-module="attendance">Attendance</button>
    </c:if>
    <c:if test="${role == 'PHARMACIST'}">
        <button class="module-btn" data-module="drugs">Drugs</button>
        <button class="module-btn" data-module="stocks">Stocks</button>
        <button class="module-btn" data-module="orders">Orders</button>
    </c:if>
</div>

<div id="topbar">
    Welcome, <span>${username}</span> | Role: <span>${role}</span>
    <a href="<c:url value='/logout' />" style="margin-left:20px; color:#2c3e50; font-weight: normal; text-decoration: none; border: 1px solid #2c3e50; padding: 5px 10px; border-radius: 4px;">Log Out</a>
</div>

<div id="main-content">
    <h1>Welcome to Dashboard</h1>
    <p>Select a module from left to view its details here.</p>
</div>

<div id="alerts-template" style="display:none;">
    <h3>Alerts & Notifications</h3>
    <ul class="alert-list">
        <c:forEach var="alert" items="${lowStockAlerts}">
            <li class="alert-lowstock">Low Stock: ${alert}</li>
        </c:forEach>
        <c:forEach var="expiry" items="${expiryAlerts}">
            <li class="alert-expiry">Near Expiry: ${expiry}</li>
        </c:forEach>
        <c:forEach var="order" items="${pendingOrders}">
            <li class="alert-pendingorder">Pending Order: ${order.id} - ${order.status}</li>
        </c:forEach>
        <c:if test="${empty lowStockAlerts and empty expiryAlerts and empty pendingOrders}">
            <li style="color: gray;">No alerts to show.</li>
        </c:if>
    </ul>
</div>

<script>
let salesChartInstance = null;
let topDrugsChartInstance = null;

document.addEventListener("DOMContentLoaded", () => {
    const chartBtn = document.querySelector('.module-btn[data-module="charts"]');
    if(chartBtn) chartBtn.classList.add('active');
    loadModule('charts');
});

const buttons = document.querySelectorAll('.module-btn');
buttons.forEach(btn => {
    btn.addEventListener('click', () => {
        buttons.forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        loadModule(btn.getAttribute('data-module'));
    });
});

function loadModule(moduleName) {
    const contentDiv = document.getElementById('main-content');
    contentDiv.innerHTML = '<p>Loading...</p>';

    if(moduleName === 'alerts') {
        const alertsTemplate = document.getElementById('alerts-template');
        contentDiv.innerHTML = alertsTemplate ? alertsTemplate.innerHTML : '<p>Alerts content not available.</p>';
        return;
    }

    let url = '';
    switch(moduleName) {
        case 'user': url = CONTEXT_PATH + '/list'; break;
        case 'dashboard': url = CONTEXT_PATH + '/dashboard/summary'; break;
        case 'drugs': url = CONTEXT_PATH + '/drugs'; break;
        case 'distributors': url = CONTEXT_PATH + '/distributors'; break;
        case 'stocks': url = CONTEXT_PATH + '/stocks'; break;
        case 'orders': url = CONTEXT_PATH + '/orders'; break;
        case 'reports': url = CONTEXT_PATH + '/reports'; break;
        case 'purchases': url = CONTEXT_PATH + '/purchases'; break;
        case 'attendance': url = CONTEXT_PATH + '/attendance'; break;
        case 'charts': url = CONTEXT_PATH + '/charts'; break;
        default: url = '';
    }

    if (!url) {
        contentDiv.innerHTML = `<p>No content available for ${moduleName}</p>`;
        return;
    }

    fetch(url, { headers: {'X-Requested-With': 'XMLHttpRequest'} })
        .then(response => {
            if (!response.ok) throw new Error('Network error');
            return response.text();
        })
        .then(html => {
            contentDiv.innerHTML = html;
            if (moduleName === 'charts') renderCharts();
        })
        .catch(err => contentDiv.innerHTML = `<p>Error loading ${moduleName}: ${err.message}</p>`);
}

function renderCharts() {
    fetch(CONTEXT_PATH + '/dashboard-data/revenue')
        .then(res => res.json())
        .then(data => {
            const ctx = document.getElementById('salesChart').getContext('2d');
            if (salesChartInstance) salesChartInstance.destroy();
            salesChartInstance = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: 'Revenue',
                        data: data.revenues,
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 2,
                        fill: true,
                    }]
                },
                options: { responsive: true, scales: { y: { beginAtZero: true } } }
            });
        });

    fetch(CONTEXT_PATH + '/dashboard-data/top-drugs')
        .then(res => res.json())
        .then(data => {
            const ctx2 = document.getElementById('topDrugsChart').getContext('2d');
            if (topDrugsChartInstance) topDrugsChartInstance.destroy();
            topDrugsChartInstance = new Chart(ctx2, {
                type: 'bar',
                data: {
                    labels: data.drugs,
                    datasets: [{
                        label: 'Quantity Sold',
                        data: data.quantities,
                        backgroundColor: 'rgba(255, 99, 132, 0.6)',
                    }]
                },
                options: { responsive: true, scales: { y: { beginAtZero: true } } }
            });
        });
}
</script>

</body>
</html>