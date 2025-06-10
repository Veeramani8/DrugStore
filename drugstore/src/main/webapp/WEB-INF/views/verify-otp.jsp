<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP</title>
</head>
<body>

<h2>Verify OTP</h2>

<form action="${pageContext.request.contextPath}/verify-otp" method="post">
    <input type="text" name="otp" placeholder="Enter OTP" required />
    <button type="submit">Verify</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

</body>
</html>
