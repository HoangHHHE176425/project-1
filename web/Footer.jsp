<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        #footer {
            background-color: #f8f9fa;
            padding: 30px 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 15px;
            box-sizing: border-box;
        }
        .footer-content {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }
        .footer-section {
            flex-basis: 50%;
            padding: 0 15px;
        }
        .footer-section h2 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .footer-section p {
            font-size: 14px;
            line-height: 1.6;
            margin-bottom: 20px;
        }
        .footer-section ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .footer-section ul li {
            margin-bottom: 10px;
        }
        .footer-section ul li a {
            text-decoration: none;
            color: #333;
        }
        .newsletter-form {
            display: flex;
            flex-wrap: wrap;
        }
        .newsletter-form input[type="text"],
        .newsletter-form input[type="email"],
        .newsletter-form button {
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
        }
        .newsletter-form input[type="text"],
        .newsletter-form input[type="email"] {
            flex-basis: 100%;
            margin-right: 0;
        }
        .newsletter-form button {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .newsletter-form button:hover {
            background-color: #0056b3;
        }
        .copyright {
            text-align: center;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #ccc;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
    <!-- Footer Start -->
    <div id="footer">
        <div class="container">
            <div class="footer-content">
                <!-- About Us Section -->
                <div class="footer-section">
                    <h2>About Us</h2>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla
                        molestie convallis felis, non commodo libero convallis quis. Nam
                        auctor velit nec massa gravida, vel faucibus ipsum condimentum.
                    </p>
                    <p>
                        <i class="fa fa-map-marker-alt"></i> 123 Street, New York, USA
                    </p>
                    <p>
                        <i class="fa fa-envelope"></i> info@example.com
                    </p>
                    <p>
                        <i class="fa fa-phone-alt"></i> +012 345 67890
                    </p>
                </div>
                <!-- Quick Links Section -->
                <div class="footer-section">
                    <h2>Quick Links</h2>
                    <ul>
                        <li><a href="shop.html">Shop</a></li>
                        <li><a href="cart.html">Shopping Cart</a></li>
                        <li><a href="checkout.html">Checkout</a></li>
                        <li><a href="https://www.facebook.com/profile.php?id=100014803861866">Contact Us</a></li>
                    </ul>
                </div>
                <!-- Newsletter Section -->
                <div class="footer-section">
                    <h2>Newsletter</h2>
                    <form action="" class="newsletter-form">
                        <input type="text" placeholder="Your Name" required>
                        <input type="email" placeholder="Your Email" required>
                        <button type="submit">Subscribe Now</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End -->

    <!-- Copyright Section -->
    <div class="copyright">
        <div class="container">
            &copy; <a href="#">EShopper</a>. All Rights Reserved.
            Designed by <a href="https://htmlcodex.com">Enric Van</a>
        </div>
    </div>
    <!-- Back to Top Button -->
    <a href="#" class="back-to-top">^</a>
</body>
</html>