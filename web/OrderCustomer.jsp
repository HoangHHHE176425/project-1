<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EShopper</title>
    <link rel="stylesheet" href="css/simple-index.css">
</head>
<body>
    <!-- Header -->
    <%@include file="Panner.jsp" %>

    <!-- Body -->
    <div class="container">
        <c:if test="${empty billDetails}">
            <!-- Show Your Bills table only if there are no bill details -->
            <h2>Your Bills</h2>
            <table class="table">
            <thead>
                <tr>
                    <th>Bill ID</th>
                    <th>Created Date</th>
                    <th>Subtotal</th>
                    <th>Status</th>
                    <th>Show Detail</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterate over userBills using JSTL foreach -->
                <c:forEach var="bill" items="${userBills}">
                    <tr>
                        <td>${bill.id}</td>
                        <td>${bill.created_date}</td>
                        <td>${bill.subTotal}</td>
                        <td>${bill.status}</td>
                        <td><a href="OrderController?service=showDetailBill&billId=${bill.id}&status=${bill.status}" class="btn btn-info btn-sm">Show Detail</a></td>
                    </tr>
                </c:forEach>
            </tbody>
            </table>
        </c:if>

        <!-- Display bill details if available -->
        <c:if test="${not empty billDetails}">
            <h2>Bill Details</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Bill ID</th>
                        <th>Customer Name</th>
                        <th>Created Date</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>SubTotal</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0" />
                    <c:forEach var="billDetail" items="${billDetails}">
                        <tr>
                            <td>${billDetail.id}</td>
                            <td>${billDetail.customerName}</td>
                            <td>${billDetail.created_date}</td>
                            <td>${billDetail.productName}</td>
                            <td>${billDetail.productQuantity}</td>
                            <td>$${Math.round(billDetail.subTotal)*1.0}</td>
                        </tr>
                        <c:set var="subtotal" value="${billDetail.subTotal}" />
                        <c:set var="total" value="${total + subtotal}" />
                         </c:forEach>
                    </tbody>
                </table>
        </c:if>
    </div>

    <!-- Footer -->
    <%@include file="Footer.jsp" %>
</body>
</html>
