<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Place Order</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
</head>
<body>

<h2>Place New Order</h2>
<a href="<c:url value='/orders' />">← Back to Order List</a>
<br><br>

<form:form method="post" modelAttribute="order" action="${pageContext.request.contextPath}/orders/save">
    <label>Customer Name:</label>
    <form:input path="customerName" required="true" />
    <br/><br/>

    <c:forEach var="item" items="${order.items}" varStatus="i">
        <label>Drug:</label>
        <select name="items[${i.index}].drugId" required>
            <option value="">-- Select Drug --</option>
            <c:forEach var="drug" items="${drugs}">
                <option value="${drug.id}"
                        <c:if test="${item.drugId == drug.id}">selected</c:if>>
                    ${drug.name}
                </option>
            </c:forEach>
        </select><br/>

        <label>Quantity:</label>
        <input type="number" min="1" name="items[${i.index}].quantity" value="${item.quantity}" required/>
        <br/><br/>
    </c:forEach>

    <button type="submit">Place Order</button>
</form:form>

</body>
</html>
