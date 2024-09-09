<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Menu</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="Free HTML Templates" name="keywords" />
    <meta content="Free HTML Templates" name="description" />
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon" />
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet" />
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" />
    <!-- Custom Stylesheet -->
    <link href="css/colorful-menu.css" rel="stylesheet" />
</head>
<body>
    <div class="menu-container">
        <div class="price-filter">
            <h3>Sort by Price</h3>
            <ul>
                <li><input type="checkbox" id="price-all" name="price" checked /><label for="price-all">All Price</label></li>
                <li><input type="checkbox" id="price-500-750" name="price" /><label for="price-500-750">$500 - $750</label></li>
                <li><input type="checkbox" id="price-750-1000" name="price" /><label for="price-750-1000">$750 - $1000</label></li>
                <li><input type="checkbox" id="price-1000-1500" name="price" /><label for="price-1000-1500">$1000 - $1500</label></li>
                <li><input type="checkbox" id="price-1500up" name="price" /><label for="price-1500up">$1500+</label></li>
            </ul>
        </div>
        <div class="brand-filter">
            <h3>Sort by Brand</h3>
            <ul>
                <li><input type="checkbox" id="brand-all" name="brand" checked /><label for="brand-all">All</label></li>
                <c:forEach items="${allBrands}" var="brand">
                    <li><input type="checkbox" id="brand-${brand.id}" name="brand" /><label for="brand-${brand.id}">${brand.name}</label></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Custom JavaScript -->
    <script src="js/colorful-menu.js"></script>
</body>
</html>