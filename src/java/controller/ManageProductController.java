/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Vector;
import model.Brand;
import model.Product;


public class ManageProductController extends HttpServlet {

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
        req.setAttribute("manageProduct", "Yes");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("listAll")) {
            Vector<Product> products = (new ProductDAO()).getAll();

            req.setAttribute("showSearchProduct", "Yes");
            req.setAttribute("allProducts", products);
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

        if (service.equals("requestUpdate")) {
            int productId = Integer.parseInt(req.getParameter("productId"));

            Product product = (new ProductDAO()).getProductsById(productId);

            req.setAttribute("productUpdate", product);
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

if (service.equals("sendUpdateDetail")) {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Date release_date = Date.valueOf(req.getParameter("release_date"));
        double discount = Double.parseDouble(req.getParameter("discount"));
        
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductsById(id);

        // Set new values for the product
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setRelease_date(release_date);
        product.setDiscount(discount);
        
        productDAO.updateProduct(product, id);
        req.setAttribute("UpdateDone", "Update information for Product (ID = " + id + ") done! Click Product Manager to see all changes.");
        req.getRequestDispatcher("admin.index.jsp").forward(req, resp);

}


        if (service.equals("requestInsert")) {
            Vector<Brand> brands = (new BrandDAO()).getAll();

            req.setAttribute("insertProduct", "insertProduct");
            req.setAttribute("allBrands", brands);
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

        if (service.equals("sendInsertDetail")) {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String description = req.getParameter("description");
            String image_url = req.getParameter("image_url");
            int brandId = Integer.parseInt(req.getParameter("brand"));
            Date release_date = Date.valueOf(req.getParameter("release_date"));
            double discount = Double.parseDouble(req.getParameter("discount"));

            Product product = new Product(quantity, brandId, name, description, image_url, price, release_date, discount);
            int gerenatedProductId = (new ProductDAO()).insertProduct(product);
            req.setAttribute("InsertDone", "Insert a new Product (ID = " + gerenatedProductId + ")\nClick Product Manager to see all changes");
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }

        if (service.equals("searchByKeywords")) {
            String keywords = req.getParameter("keywords");

            Vector<Product> products = (new ProductDAO()).getProductsByKeywords(keywords);

            if (products == null || products.isEmpty()) {
                req.setAttribute("notFoundProduct", "Your keywords do not match with any Product Name");
                products = (new ProductDAO()).getAll();
            }

            req.setAttribute("allProducts", products);
            req.setAttribute("keywords", keywords);
            req.setAttribute("showSearchProduct", "Yes");
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }
        
        if (service.equals("requestDelete")) {
            String productId_raw = req.getParameter("productId");
            int productId = Integer.parseInt(productId_raw);
            
            int n = (new ProductDAO()).deletetProduct(productId);
            if (n == 1) {
                req.setAttribute("deleteDone", "Delete Product (Id = " + productId +") done!");
            } else {
                req.setAttribute("deleteDone", "Failed to delete Product (Id  = " + productId + ") because this product is asociated with an order.");
            }
            
            Vector<Product> products = (new ProductDAO()).getAll();

            req.setAttribute("showSearchProduct", "Yes");
            req.setAttribute("allProducts", products);
            req.getRequestDispatcher("admin.index.jsp").forward(req, resp);
        }
    }

}
