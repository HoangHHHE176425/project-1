package controller;

import dal.BrandDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;
import model.Brand;
import model.Product;


public class CustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        String service = req.getParameter("service");
        ProductDAO productDAO = new ProductDAO();
        BrandDAO brandDAO = new BrandDAO();
        
        // Lấy danh sách thương hiệu
        Vector<Brand> brands = brandDAO.getAll();
        req.setAttribute("allBrands", brands);
        
        // Thiết lập số lượng sản phẩm trong giỏ hàng mặc định là 0 nếu chưa có
        if (session.getAttribute("numberProductsInCart") == null) {
            session.setAttribute("numberProductsInCart", 0);
        }

        // Xử lý phân trang
        int pageNumber;
        int pageSize;
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        } catch (NumberFormatException e) {
            pageNumber = 1; // mặc định là trang đầu tiên
        }
        try {
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = 6; // kích thước trang mặc định
        }

        // Thiết lập dịch vụ mặc định là listAllProducts nếu chưa có
        if (service == null) {
            service = "listAllProducts";
        }

        if (service.equals("listAllProducts")) {
            // Liệt kê tất cả sản phẩm
            int totalProducts = productDAO.getTotalProducts();
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
            Vector<Product> products = productDAO.getProductsByPage(pageNumber, pageSize);

            req.setAttribute("allProducts", products);
            req.setAttribute("currentPage", pageNumber);
            req.setAttribute("pageSize", pageSize);
            req.setAttribute("totalPages", totalPages);

            req.getRequestDispatcher("customer.index.jsp").forward(req, resp);
        } else if (service.equals("searchByKeywords")) {
            // Tìm kiếm sản phẩm theo từ khóa
            String keywords = req.getParameter("keywords");
            String sortBy = req.getParameter("sortBy");
            String filterByPrice = req.getParameter("filterByPrice");
            String filterByBrand = req.getParameter("filterByBrand");

            if (filterByPrice == null) {
                filterByPrice = "price-all";
            }

            if (filterByBrand == null) {
                filterByBrand = "brand-all";
            }

            // Lọc sản phẩm theo từ khóa
            Vector<Product> productsAfterFilterByKeywords = productDAO.getProductsByKeywords(keywords);
            // Lọc sản phẩm theo giá
            Vector<Product> productsAfterFilterByPrice = productDAO.filterByPrice(filterByPrice, productsAfterFilterByKeywords);
            // Lọc sản phẩm theo thương hiệu
            Vector<Product> productsAfterFilterByBrand = productDAO.filterByBrand(filterByBrand, productsAfterFilterByPrice);

            if (sortBy == null || sortBy.equals("unsorted")) {
                sortBy = "unsorted";
            }
            // Sắp xếp sản phẩm
            Vector<Product> productsAfterFilter = productDAO.sortProducts(productsAfterFilterByBrand, sortBy);

            // Phân trang
            int totalProducts = productsAfterFilter.size();
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
            int start = (pageNumber - 1) * pageSize;
            int end = Math.min(start + pageSize, totalProducts);
            Vector<Product> productsToShow = new Vector<>(productsAfterFilter.subList(start, end));

            // Đặt các thuộc tính để truyền sang JSP
            req.setAttribute("allProducts", productsToShow);
            req.setAttribute("currentPage", pageNumber);
            req.setAttribute("pageSize", pageSize);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("keywords", keywords);
            req.setAttribute("filterByPrice", filterByPrice);
            req.setAttribute("filterByBrand", filterByBrand);
            req.setAttribute("sortBy", sortBy);

            req.getRequestDispatcher("customer.index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("customer.index.jsp");
    }
}
