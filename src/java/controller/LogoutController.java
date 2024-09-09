package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import model.CartItem;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // Xóa các mục trong giỏ hàng
        clearCart(session);
        
        // Xóa các thuộc tính trong session
        session.removeAttribute("user");
        session.removeAttribute("fullname");
        session.removeAttribute("numberProductsInCart");
        session.invalidate();
        
        // Xóa các cookie
        clearCookies(req, resp);
        
        // Chuyển hướng đến trang customer
        resp.sendRedirect("customer");
    }
    
    private void clearCart(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            if (session.getAttribute(attributeName) instanceof CartItem) {
                session.removeAttribute(attributeName);
            }
        }
    }

    private void clearCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Kiểm tra tên cookie để xóa
                if ("userId".equals(cookie.getName()) || "roleId".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath(req.getContextPath()); // Đặt lại đường dẫn đúng
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }
}
