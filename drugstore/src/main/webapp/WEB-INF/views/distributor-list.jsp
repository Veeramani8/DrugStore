<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Distributor List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" />
</head>
<body>
    <h2>All Distributors</h2>

    <a href="${pageContext.request.contextPath}/dashboard"><button>← Back to Dashboard</button></a>
    <a href="${pageContext.request.contextPath}/distributors/new"><button>Add New Distributor</button></a>
    <a href="${pageContext.request.contextPath}/distributors/download-pdf"><button>Download PDF</button></a>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>License Number</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="distributor" items="${distributors}">
                <tr>
                    <td>${distributor.id}</td>
                    <td>${distributor.name}</td>
                    <td>${distributor.email}</td>
                    <td>${distributor.phone}</td>
                    <td>${distributor.address}</td>
                    <td>${distributor.licenseNumber}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/editdistributor/${distributor.id}">Edit</a> |
                        <a href="${pageContext.request.contextPath}/admin/deletedistributor/${distributor.id}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
