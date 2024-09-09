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
        <div class="row"> 
            <%@include file="Menu.jsp" %>
            <%@include file="Content.jsp" %>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="Footer.jsp" %>
</body>
</html>