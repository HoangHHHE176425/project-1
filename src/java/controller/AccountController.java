package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import dal.UserDAO;
import jakarta.servlet.http.Cookie;

public class AccountController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy userId và roleId từ cookie
        Integer userId = null;
        Integer roleId = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = Integer.parseInt(cookie.getValue());
                }
                if ("roleId".equals(cookie.getName())) {
                    roleId = Integer.parseInt(cookie.getValue());
                }
            }
        }

        // Chuyển hướng đến trang đăng nhập nếu userId không có
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }

        // Tham số từ request
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        // Cập nhật thông tin người dùng nếu các tham số được cung cấp
        if (fullname != null && !fullname.isEmpty() &&
            password != null && !password.isEmpty() &&
            email != null && !email.isEmpty() &&
            phone != null && !phone.isEmpty() &&
            address != null && !address.isEmpty()) {

            // Cập nhật người dùng trong cơ sở dữ liệu
            UserDAO userDAO = new UserDAO();
            userDAO.updateUserById(userId, fullname, password, email, phone, address);

            // Chuyển hướng đến trang đăng nhập sau khi cập nhật thành công
            resp.sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }

        // Chuyển tiếp đến phương thức doGet để hiển thị thông tin người dùng đã cập nhật
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Lấy userId và roleId từ cookie
        Integer userId = null;
        Integer roleId = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = Integer.parseInt(cookie.getValue());
                }
                if ("roleId".equals(cookie.getName())) {
                    roleId = Integer.parseInt(cookie.getValue());
                }
            }
        }

        // Chuyển hướng đến trang đăng nhập nếu userId không có sẵn
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }

        // Lấy thông tin tài khoản người dùng
        UserDAO userDAO = new UserDAO();
        List<User> account = userDAO.getAccountByUserId(userId);

        // Đặt thuộc tính và chuyển tiếp đến AccountCustomer.jsp
        req.setAttribute("userAccount", account);
        req.getRequestDispatcher("AccountCustomer.jsp").forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Account Controller Servlet";
    }
}
