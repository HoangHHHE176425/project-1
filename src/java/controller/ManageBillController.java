/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.BillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;
import model.BillDetail;
import model.BillDetailForAdmin;

public class ManageBillController extends HttpServlet {

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

        // Chuyển hướng đến trang đăng nhập nếu userId không có
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }
        
        String service = req.getParameter("service");
        // Đặt giá trị manageBill 'yes' để chỉ định đang ở admin.index
        req.setAttribute("manageBill", "Yes");

        // Xử lý các yêu cầu
        if (service == null) {
            service = "listAll";
        }
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("listAll")) {
            Vector<BillDetailForAdmin> billDetailForAdmins = (new BillDAO()).showBillDetailForAdmin();

            req.setAttribute("billDetailForAdmins", billDetailForAdmins);
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

        if (service.equals("showDetailBill")) {
            int billId = Integer.parseInt(req.getParameter("billId"));
            String status = req.getParameter("status");

            Vector<BillDetail> billDetails = (new BillDAO()).showBillDetail(billId);

            req.setAttribute("status", status);
            req.setAttribute("billDetails", billDetails);

            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

        if (service.startsWith("changeStatusTo")) {
            int billId = Integer.parseInt(req.getParameter("billId"));
            String statusInShowDetail = req.getParameter("statusInShowDetail");
            //check if status is done

            if (statusInShowDetail.equals("done")) {
                req.setAttribute("changeStatus", "Status of this bill is done, you can not change it!");
            } else {

                if (service.endsWith("Wait")) {
                    (new BillDAO()).updateStatus("wait", billId);
                    req.setAttribute("changeStatus", "Admin change status of Bill (ID = " + billId + ") to Wait");
                }

                if (service.endsWith("Process")) {
                    (new BillDAO()).updateStatus("process", billId);
                    req.setAttribute("changeStatus", "Admin change status of Bill (ID = " + billId + ") to Process");

                }

                if (service.endsWith("Done")) {
                    (new BillDAO()).updateStatus("done", billId);
                    req.setAttribute("changeStatus", "Admin change status of Bill (ID = " + billId + ") to Done");

                }

            }
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

        if (service.equals("filterStatus")) {
            String filter = req.getParameter("filter");
            Vector<BillDetailForAdmin> bdfas = new Vector<>();
            if (filter.equals("all")) {
                bdfas = (new BillDAO()).showBillDetailForAdmin();
            } else {
                bdfas = (new BillDAO()).showBillDetailForAdminFilterByStatus(filter);
            }
            req.setAttribute("billDetailForAdmins", bdfas);
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

    }

}
