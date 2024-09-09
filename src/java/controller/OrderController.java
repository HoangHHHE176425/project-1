package controller;

import dal.BillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.BillDetail;

public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        // Kiểm tra nếu không có roleId hoặc userId trong cookie, chuyển hướng đến trang login
        if (roleId == null || userId == null) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }

        String service = req.getParameter("service");
        if (service != null && service.equals("showDetailBill")) {
            int billId = Integer.parseInt(req.getParameter("billId"));
            String status = req.getParameter("status");

            Vector<BillDetail> billDetails = (new BillDAO()).showBillDetail(billId);

            req.setAttribute("status", status);
            req.setAttribute("billDetails", billDetails);

            req.getRequestDispatcher("OrderCustomer.jsp").forward(req, resp);
            return;
        }

        if (roleId == 1) { // Nếu là user
            // Hiển thị tất cả các hóa đơn cho userId này
            Vector<BillDetail> userBills = (new BillDAO()).getBillUserById(userId);
            req.setAttribute("userBills", userBills);
            req.getRequestDispatcher("OrderCustomer.jsp").forward(req, resp); // Điều hướng đến trang OrderCustomer.jsp
            return; // Kết thúc việc xử lý doGet()
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
