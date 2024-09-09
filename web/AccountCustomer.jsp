<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EShopper - Your Account</title>
    <link rel="stylesheet" href="css/AccountCustomer.css">

</head>
<body>
    <!-- Header -->
    <%@include file="Panner.jsp" %>

    <!-- Body -->
    <div class="container">
        <h2>Your Account</h2>
        <form id="accountForm" action="AccountController" method="POST">
            <input type="hidden" name="userId" value="${sessionScope.userId}">
            <input type="hidden" name="roleId" value="${sessionScope.roleId}">
            <table>
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Password</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="customer" items="${userAccount}">
                        <tr>
                            <td><input type="text" name="fullname" value="${customer.fullname}" readonly></td>
                            <td><input type="password" name="password" value="${customer.password}" readonly></td>
                            <td><input type="text" name="email" value="${customer.email}" readonly></td>
                            <td><input type="text" name="phone" value="${customer.phone}" readonly></td>
                            <td><input type="text" name="address" value="${customer.address}" readonly></td>
                            <td class="edit-button">
                                <button type="button" onclick="changeType(this)">Edit</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
<!-- End Body -->
    <!-- Footer -->
    <%@include file="Footer.jsp" %>

    <!-- Script nút Eidt > Save -->
    <script src="js/AccountCustomer.js"></script>
</body>
</html>
