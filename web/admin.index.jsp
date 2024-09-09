<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="css/simple-admin.css">
    
</head>
<body>
    <!-- Header -->
    <%@include file="Panner.jsp" %>
    
    <!-- Body -->
    <div class="container-fluid mb-5">
        <div class="row px-3">
            <!--Left Menu-->
            <div class="col-lg-3 d-none d-lg-block bg-light">
                <h6 class="py-3 mb-0 pl-3">Admin Manager</h6>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="manageCustomer">Customer Manager</a></li>
                    <li class="list-group-item"><a href="manageProduct">Product Manager</a></li>
                    <li class="list-group-item"><a href="manageBill">Bill Manager</a></li>
                </ul>
            </div>
            <!--End Left Menu-->

            <!--Right Content-->   
            <div class="col-lg-9">
                <!--Customer Manager-->
                <c:if test="${manageCustomer ne null}">
                    <c:if test="${not empty allCustomers}">
                        <%@include file="CustomerManager.jsp" %>
                    </c:if>
                </c:if>

                <!--Product Manager-->
                <c:if test="${manageProduct ne null}">
                    <%@include file="ProductManager.jsp" %>
                </c:if>

                <!--Bill Manager-->
                <c:if test="${manageBill ne null}">
                    <%@include file="BillManager.jsp" %>
                </c:if>
            </div>
            <!--End Right Content-->
        </div>
    </div>

    <!-- Footer -->
    <%@include file="Footer.jsp" %>
</body>
</html>