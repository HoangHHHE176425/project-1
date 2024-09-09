<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/styleLogin.css"/>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #333333;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: 500;
            color: #555555;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            font-size: 16px;
            outline: none;
        }
        .form-group input:focus {
            border-color: #007bff;
        }
        .btn-register {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #ffffff;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-register:hover {
            background-color: #0056b3;
        }
        .login-now {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
        }
        .login-now a {
            color: #007bff;
            text-decoration: none;
        }
        .login-now a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
            text-align: center;
        }
    </style>
    <script>
        function validateEmail() {
            const emailInput = document.querySelector('input[name="email"]');
            const email = emailInput.value;
            if (!email.endsWith("@gmail.com")) {
                alert("Vui lòng nhập địa chỉ email đúng định dạng @gmail.com");
                emailInput.focus();
                return false; // Ngăn không cho gửi form
            }
            return true; // Cho phép gửi form
        }

        function validatePasswords() {
            const password = document.querySelector('input[name="password"]').value;
            const confirmPassword = document.querySelector('input[name="confirmPassword"]').value;

            if (password !== confirmPassword) {
                alert("Mật khẩu không khớp! Vui lòng kiểm tra lại.");
                return false; // Ngăn không cho gửi form
            }
            return true; // Cho phép gửi form
        }

        function validateForm() {
            return validateEmail() && validatePasswords();
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <div class="form-container">
            <h2>Register</h2>
            <c:if test="${not empty requestScope.duplicateUsername}">
                <div class="error-message">${requestScope.duplicateUsername}</div>
            </c:if>
            <c:if test="${not empty requestScope.registerError}">
                <div class="error-message">${requestScope.registerError}</div>
            </c:if>
            <form action="register" method="POST" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" placeholder="Enter Username" required />
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" placeholder="Enter Password" required />
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" name="confirmPassword" placeholder="Re-enter Password" required />
                </div>
                <div class="form-group">
                    <label for="fullname">Fullname</label>
                    <input type="text" name="fullname" placeholder="Enter Fullname" required />
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" name="email" placeholder="Enter email" required />
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" name="phone" placeholder="Enter Phone" required />
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" name="address" placeholder="Enter Address" required />
                </div>
                <div class="form-group">
                    <button type="submit" class="btn-register">Register</button>
                </div>
            </form>
            <div class="login-now">
                <span>Already have an account?</span>
                <a href="login">Login Now</a>
            </div>
        </div>
    </div>
</body>
</html>
