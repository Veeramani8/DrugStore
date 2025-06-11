<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div id="statsTab" class="tab-content">
    <h3>Statistics Overview</h3>
    <ul>
        <li>Total Sales: ₹${totalSales}</li>
        <li>Stock Level: ${stockLevel} items</li>
        <li>Recent Orders: ${recentOrdersCount}</li>
        <li>Top Selling Drug: ${topDrugName}</li>
    </ul>
</div>

<!-- Dashboard Charts -->
<h2>Top Selling Drugs</h2>
<canvas id="topDrugsChart" width="600" height="300"></canvas>

<h2>Sales Trends</h2>
<canvas id="salesChart" width="600" height="300"></canvas>


<!-- Reports Charts -->
<h2>Revenue Over Time</h2>
<canvas id="revenueChart" height="200"></canvas>

<h2>Stock Distribution</h2>
<canvas id="stockPieChart" height="200"></canvas>

<script>
    const CONTEXT_PATH = '${pageContext.request.contextPath}';

    // 1. Dashboard Charts
    fetch(CONTEXT_PATH + '/dashboard-data/revenue')
        .then(res => res.json())
        .then(data => {
            const ctx = document.getElementById('salesChart')?.getContext('2d');
            if (ctx) {
                new Chart(ctx, {
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
                    options: {
                        responsive: true,
                        scales: { y: { beginAtZero: true } }
                    }
                });
            }
        });

    fetch(CONTEXT_PATH + '/dashboard-data/top-drugs')
        .then(res => res.json())
        .then(data => {
            const ctx = document.getElementById('topDrugsChart')?.getContext('2d');
            if (ctx) {
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: data.drugs,
                        datasets: [{
                            label: 'Quantity Sold',
                            data: data.quantities,
                            backgroundColor: 'rgba(255, 99, 132, 0.6)',
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: { y: { beginAtZero: true } }
                    }
                });
            }
        });

    // 2. Reports Charts
    function renderReportsCharts() {
        // Revenue Line Chart
        fetch(CONTEXT_PATH + '/revenue')
            .then(res => res.json())
            .then(data => {
                const ctx = document.getElementById('revenueChart')?.getContext('2d');
                if (ctx) {
                    new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: data.labels,
                            datasets: [{
                                label: 'Revenue',
                                data: data.values,
                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 2,
                                fill: true,
                            }]
                        },
                        options: {
                            responsive: true,
                            scales: { y: { beginAtZero: true } }
                        }
                    });
                }
            });

        // Stock Pie Chart
        fetch(CONTEXT_PATH + '/stock-distribution')
            .then(res => res.json())
            .then(data => {
                const ctx = document.getElementById('stockPieChart')?.getContext('2d');
                if (ctx) {
                    new Chart(ctx, {
                        type: 'pie',
                        data: {
                            labels: data.labels,
                            datasets: [{
                                data: data.values,
                                backgroundColor: [
                                    '#FF6384', '#36A2EB', '#FFCE56', '#8e44ad', '#27ae60'
                                ],
                            }]
                        },
                        options: {
                            responsive: true,
                        }
                    });
                }
            });
    }

    document.addEventListener("DOMContentLoaded", renderReportsCharts);
</script>
</body>
</html>
