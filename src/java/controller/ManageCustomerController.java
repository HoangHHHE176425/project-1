package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;
import model.User;

public class ManageCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Lấy phiên hiện tại, không tạo mới nếu không có

        // Kiểm tra xem phiên có tồn tại và người dùng đã đăng nhập chưa
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp"); // Chuyển hướng đến trang đăng nhập nếu không có phiên hoặc chưa đăng nhập
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user.getRole_id() != 0) { // Kiểm tra nếu không phải admin
            resp.sendRedirect(req.getContextPath() + "/Login.jsp"); // Chuyển hướng đến trang đăng nhập nếu không phải admin
            return;
        }

        UserDAO userDAO = new UserDAO();
        Vector<User> customers = userDAO.getAllCustomer();
        String service = req.getParameter("service");

        if (service == null) {
            service = "listAllCustomers";
        }

        switch (service) {
            case "listAllCustomers":
                req.setAttribute("manageCustomer", "Yes");
                req.setAttribute("allCustomers", customers);
                req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
                break;
            case "ban":
                int idBan = Integer.parseInt(req.getParameter("id"));
                userDAO.banAnUser(idBan);
                customers = userDAO.getAllCustomer();
                req.setAttribute("manageCustomer", "Yes");
                req.setAttribute("allCustomers", customers);
                req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
                break;
            case "searchByKeywords":
                String keywords = req.getParameter("keywords");
                req.setAttribute("keywords", keywords);
                req.setAttribute("manageCustomer", "Yes");
                customers = userDAO.getCustomerByName(keywords);
                if (customers == null || customers.isEmpty()) {
                    req.setAttribute("notFoundCustomer", "Your keywords do not match with any Customer Name");
                    customers = userDAO.getAllCustomer();
                }
                req.setAttribute("allCustomers", customers);
                req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
                break;
            case "delete":
                int idDelete = Integer.parseInt(req.getParameter("id"));
                userDAO.deleteUserById(idDelete);
                customers = userDAO.getAllCustomer();
                req.setAttribute("manageCustomer", "Yes");
                req.setAttribute("allCustomers", customers);
                req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
                break;
        }
    }
}
