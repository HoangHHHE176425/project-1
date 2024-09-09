<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bill Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="css/BillManager.css">
</head>
<body>
    <div class="container">
        <h1 class="font-weight-bold text-uppercase mb-3 text-center">Bill Manager</h1>
        
        <div class="search-container input-group">
            <input type="text" class="form-control" placeholder="Search by customer name" name="keywords" value="${keywords}" />
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
                                
        <c:if test="${not empty billDetailForAdmins}">
            <div class="mb-4">
                <h5 class="font-weight-bold mb-3">Filter by Status</h5>
                <form>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" name="status" id="all" onchange="filterStatus(this.id)" ${param.filter == 'all' ? 'checked' : ''}>
                        <label class="form-check-label filter-label" for="all">All Status</label>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" name="status" id="wait" onchange="filterStatus(this.id)" ${param.filter == 'wait' ? 'checked' : ''}>
                        <label class="form-check-label filter-label" for="wait">Wait</label>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" name="status" id="process" onchange="filterStatus(this.id)" ${param.filter == 'process' ? 'checked' : ''}>
                        <label class="form-check-label filter-label" for="process">Process</label>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" name="status" id="done" onchange="filterStatus(this.id)" ${param.filter == 'done' ? 'checked' : ''}>
                        <label class="form-check-label filter-label" for="done">Done</label>
                    </div>
                </form>
            </div>
            
            <div class="table-responsive">
                <table class="table table-bordered text-center mb-5">
                    <thead class="bg-secondary text-white">
                        <tr>
                            <th>ID</th>
                            <th>Customer Name</th>
                            <th>Created Date</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th>Show Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${billDetailForAdmins}" var="b">
                            <tr>
                                <td>${b.id}</td>
                                <td>${b.customerName}</td>
                                <td>${b.created_date}</td>
                                <td>${b.address}</td>
                                <td>${b.email}</td>
                                <td>${b.phone}</td>
                                <td>$${Math.round(b.total)*1.0}</td>
                                <td>${b.status}</td>
                                <td><a href="manageBill?service=showDetailBill&billId=${b.id}&status=${b.status}" class="btn btn-info btn-sm">Show Detail</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        
        
        <c:if test="${not empty billDetails}">
            <div class="table-responsive">
                <table class="table table-bordered text-center mb-5">
                    <thead class="bg-secondary text-white">
                        <tr>
                            <th>BillID</th>
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
            </div>
        </c:if>
        
        <c:if test="${not empty changeStatus}">
            <div class="text-center">
                <h3 class="font-weight-bold">${changeStatus}</h3>
            </div>
        </c:if>
    </div>

    
    <script>
        function filterStatus(value) {
            const urlParams = new URLSearchParams(window.location.search);
            let service = urlParams.get('service');
            
            if (service === null) {
                service = "filterStatus";
            }
            
            window.location.href = "manageBill?service=" + service + "&filter=" + value;
        }
    </script>
</body>
</html>
