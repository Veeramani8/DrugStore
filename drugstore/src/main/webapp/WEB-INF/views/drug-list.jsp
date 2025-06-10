<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Drug List</title>
    <link rel="stylesheet" href="<c:url value='/css/list.css' />" />
</head>
<body>

<h2>Drug List</h2>

<a href="<c:url value='/dashboard' />"><button>Back to Dashboard</button></a>
<a href="<c:url value='/drugs/add' />"><button>Add New Drug</button></a>
<a href="<c:url value='/drugs/download-pdf' />"><button>Download PDF</button></a>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Composition</th>
            <th>Dosage</th>
            <th>Manufacturer</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="drug" items="${drugs}">
            <tr>
                <td><c:out value="${drug.id}" /></td>
                <td><c:out value="${drug.name}" /></td>
                <td><c:out value="${drug.composition}" /></td>
                <td><c:out value="${drug.dosage}" /></td>
                <td><c:out value="${drug.manufacturer}" /></td>
                <td><c:out value="${drug.price}" /></td>
                <td>
                    <a href="<c:url value='/admin/editdrug/${drug.id}' />">Edit</a> |
                    <a href="<c:url value='/admin/deletedrug/${drug.id}' />"
                       onclick="return confirm('Are you sure you want to delete this drug?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
