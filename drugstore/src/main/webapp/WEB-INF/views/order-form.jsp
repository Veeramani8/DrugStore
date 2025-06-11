<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Place Order</title>
    <link rel="stylesheet" href="<c:url value='/css/form.css' />" />
    <script>
        let drugIndex = ${fn:length(order.items)};

        function addDrugRow() {
            const container = document.getElementById('drugRows');

            const row = document.createElement('div');
            row.className = 'drug-row';
            row.innerHTML = `
                <label>Drug:</label>
                <select name="items[\${drugIndex}].drugId" required>
                    <option value="">-- Select Drug --</option>
                    <c:forEach var="drug" items="${drugs}">
                        <option value="${drug.id}">${drug.name}</option>
                    </c:forEach>
                </select>
                <label>Quantity:</label>
                <input type="number" min="1" name="items[\${drugIndex}].quantity" required />
                <button type="button" onclick="removeDrugRow(this)">Remove</button>
                <br><br>
            `;
            container.appendChild(row);
            drugIndex++;
        }

        function removeDrugRow(button) {
            const row = button.parentElement;
            row.remove();
        }
    </script>
</head>
<body>

<h2>Place New Order</h2>
<br><br>

<form:form method="post" modelAttribute="order" action="${pageContext.request.contextPath}/orders/save">

    <label>Customer Name:</label>
    <form:input path="customerName" required="true" />
    <br/><br/>

    <div id="drugRows">
        <c:forEach var="item" items="${order.items}" varStatus="i">
            <div class="drug-row">
                <label>Drug:</label>
                <select name="items[${i.index}].drugId" required>
                    <option value="">-- Select Drug --</option>
                    <c:forEach var="drug" items="${drugs}">
                        <option value="${drug.id}" <c:if test="${item.drugId == drug.id}">selected</c:if>>
                            ${drug.name}
                        </option>
                    </c:forEach>
                </select>

                <label>Quantity:</label>
                <input type="number" min="1" name="items[${i.index}].quantity" value="${item.quantity}" required />

                <button type="button" onclick="removeDrugRow(this)">Remove</button>
                <a href="<c:url value='/orders' />">Back to Order List</a>
                
                <br><br>
            </div>
        </c:forEach>
    </div>

    <button type="button" onclick="addDrugRow()">Add Another Drug</button>
    <br/><br/>

    <button type="submit">Place Order</button>

</form:form>

</body>
</html>
