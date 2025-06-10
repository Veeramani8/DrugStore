<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/css/dashboard.css' />" />
</head>
<body>

<a href="<c:url value='/dashboard' />">← Back to Dashboard</a>

<h2>Reports Dashboard</h2>

<div class="button-group">
    <a href="<c:url value='/reports/sales' />" class="btn">Sales Report</a>
    <a href="<c:url value='/reports/purchases' />" class="btn">Purchase Report</a>
    <a href="<c:url value='/reports/expiry' />" class="btn">Expiry Report</a>
    <a href="<c:url value='/reports/revenue' />" class="btn">Revenue Report</a>
</div>

<h1>Last 7 day Revenue</h1>
<canvas id="revenueChart" height="100"></canvas>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    fetch('<c:url value="/dashboard-data/revenue" />')
        .then(res => res.json())
        .then(data => {
            const ctx = document.getElementById('revenueChart');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: 'Daily Revenue (₹)',
                        data: data.revenues,
                        borderColor: 'green',
                        fill: false,
                        tension: 0.3
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: { beginAtZero: true }
                    }
                }
            });
        });
</script>

<h1>Stock Distribution</h1>
<canvas id="stockPieChart" height="100"></canvas>

<script>
    function renderStockPieChart() {
        fetch('<c:url value="/reports/stock-distribution" />')
            .then(res => res.json())
            .then(data => {
                const ctx3 = document.getElementById('stockPieChart').getContext('2d');
                new Chart(ctx3, {
                    type: 'pie',
                    data: {
                        labels: data.labels,
                        datasets: [{
                            label: 'Stock Distribution',
                            data: data.values,
                            backgroundColor: [
                                '#3498db', '#2ecc71', '#f39c12', '#e74c3c', '#9b59b6'
                            ]
                        }]
                    }
                });
            });
    }

    window.onload = function () {
        renderStockPieChart();
    };
</script>

</body>
</html>
