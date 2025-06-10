<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>
    <h2>Customer Orders</h2>
    <a href="<c:url value='/dashboard' />">← Back to Dashboard</a>
    <br>
    <a href="<c:url value='/orders/new' />" class="btn">Place New Order ---></a>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>Total Amount</th>
                <th>Date</th>
                <th>Status</th>
                <th>Order Items</th>
                <th>Update Status</th>
                <th>Print Receipt</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>${order.totalAmount}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.status}</td>
                    <td>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Drug Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${order.items}">
                                    <tr>
                                        <td>${item.drug.name}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.drug.price}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/orders/${order.id}/status" method="post">
                            <select name="status">
                                <c:forEach var="status" items="${orderStatusList}">
                                    <option value="${status}" <c:if test="${order.status eq status}">selected</c:if>>
                                        ${status}
                                    </option>
                                </c:forEach>
                            </select>
                            <button type="submit">Update</button>
                        </form>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orders/receipt/${order.id}" target="_blank">
                            <button type="button">Download Receipt</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
