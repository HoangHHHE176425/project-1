<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customers Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .search-button {
            border-top-left-radius: 0;
            border-bottom-left-radius: 0;
        }
        .table th, .table td {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="font-weight-semi-bold text-uppercase mb-4 text-center">Customers Manager</h1>

    <!-- Search customer by name -->
    <form action="manageCustomer" id="searchByName">
        <input type="hidden" name="service" value="searchByKeywords"/>
        <div class="input-group mb-4">
            <input type="text" class="form-control" placeholder="Search by name" name="keywords" value="${keywords}">
            <div class="input-group-append">
                <button class="search-button btn btn-primary" type="submit"><i class="fa fa-search"></i></button>
            </div>
        </div>
    </form>

    <!-- Display not found message -->
    <c:if test="${notFoundCustomer ne null}">
        <div class="alert alert-danger text-center" role="alert">${notFoundCustomer}</div>
    </c:if>

    <!-- Customer table -->
    <div class="table-responsive">
        <table class="table table-bordered text-center">
            <thead class="bg-secondary text-white">
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Banned</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allCustomers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.username}</td>
                    <td>${customer.password}</td>
                    <td>${customer.fullname}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.address}</td>
                    <td>${customer.banned == 1 ? 'Yes' : 'No'}</td>
                    <td>
                        <a href="manageCustomer?service=ban&id=${customer.id}" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> Ban</a>
                        <a href="manageCustomer?service=delete&id=${customer.id}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.5.4/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
