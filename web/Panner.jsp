<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.CartItem, model.Product, java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Panner</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="Free HTML Templates" name="keywords" />
    <meta content="Free HTML Templates" name="description" />

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon" />

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet" />

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet" />
    <style>
        /* Add your custom styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 15px;
            box-sizing: border-box;
        }
        .topbar {
            background-color: #343a40;
            color: #fff;
            padding: 10px 0;
        }
        .topbar a {
            color: #fff;
            text-decoration: none;
            margin: 0 10px;
        }
        .navbar {
            background-color: #fff;
            padding: 10px 0;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .navbar-brand {
            font-size: 24px;
            color: #007bff;
            font-weight: bold;
        }
        .navbar-toggler {
            border: none;
            outline: none;
        }
        .navbar-toggler-icon {
            background-color: #007bff;
        }
        .navbar-nav .nav-item {
            margin-right: 10px;
        }
        .navbar-nav .nav-item:last-child {
            margin-right: 0;
        }
        .navbar-nav .nav-item a {
            color: #333;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        .navbar-nav .nav-item a:hover {
            color: #007bff;
        }
        .page-header {
            background-color: #007bff;
            color: #fff;
            padding: 50px 0;
            text-align: center;
        }
        .page-header h1 {
            font-size: 36px;
            font-weight: bold;
            margin: 0;
        }
        .page-header a {
            color: #fff;
            text-decoration: none;
        }
        .page-header a:hover {
            text-decoration: underline;
        }
        .nav-link.btn-link {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
            font-weight: bold;
        }
        .nav-link.btn-link:hover {
            color: #0056b3;
            text-decoration: none;
        }
        .nav-link.btn-link {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
            font-weight: bold;
        }
        .nav-link.btn-link:hover {
            color: #0056b3;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <!-- Topbar Start -->
    <div class="topbar">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <a href="#">HE176425</a>
                    <span>|</span>
                    <a href="#">Hà Huy Hoàng</a>
                </div>
                <div class="col-lg-6 text-right">
                    <c:if test="${sessionScope.fullname != null}">
                        <span>Welcome: ${sessionScope.fullname}</span>
                        <span>|</span>
                        <a href="logout">Logout</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar End -->

<!-- Navbar Start -->
<nav class="navbar navbar-expand-lg">
    <div class="container">
        <!-- Kiểm tra xem người dùng đã đăng nhập -->
        <c:choose>
            <c:when test="${sessionScope.user != null}"> 
                <c:choose>
                    <c:when test="${sessionScope.user.role_id == 0}">
                        <a class="navbar-brand" href="customer">Laptop World</a>
                    </c:when>
                    <c:otherwise>
                        <a class="navbar-brand" href="customer">Laptop World</a>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="login">Laptop World</a>
            </c:otherwise>
        </c:choose>
                
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav ml-auto">
                <c:if test="${sessionScope.user != null}">
                    <c:if test="${sessionScope.user.role_id == 1}">
                        <!-- Hiển thị số lượng sản phẩm trong giỏ hàng -->    
                        <li class="nav-item">
                            <a class="nav-link" href="cart?service=showCart">
                                <i class="fas fa-shopping-cart text-primary"></i>
                                <span class="badge badge-warning" id="cartItemCount">${sessionScope.numberProductsInCart}</span>
                            </a>
                        </li>
                    
                        <!-- Nút Order -->
                        <li class="nav-item">
                            <button onclick="window.location.href='OrderController'" class="nav-link btn btn-link">
                                Order
                            </button>
                        </li>

                    
                        <!-- Nút Account -->
                        <li class="nav-item">
                             <button onclick="window.location.href='AccountController'" class="nav-link btn btn-link">
                                Account
                            </button>
                        </li>
                        
                        
                    </c:if>
                    
                    <!-- Chỉ hiển thị "Manage" nếu role là admin -->
                    <c:if test="${sessionScope.user.role_id == 0}">
                        <li class="nav-item">
                            <a class="nav-link" href="admin.index.jsp">Manage</a>
                        </li>
                    </c:if>
                </c:if>
                
                <c:if test="${sessionScope.user eq null}">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register">Register</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="page-header">
        <div class="container">
            <h1>Welcome to EShopper</h1>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>
