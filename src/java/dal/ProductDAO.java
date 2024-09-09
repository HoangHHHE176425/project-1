/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

public class ProductDAO extends DBContext {

    public Vector<Product> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<Product> products = new Vector<>();
        String sql = "select * from [product]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id"), quantity = rs.getInt("quantity"), brand_id = rs.getInt("brand_id");
                String name = rs.getString("name"), description = rs.getString("description"), image_url = rs.getString("image_url");
                double price = rs.getDouble("price");
                Date release_date = rs.getDate("release_date");
                double discount = rs.getDouble("discount");
    
                products.add(new Product(id, quantity, brand_id, name, description, image_url, price, release_date, discount));
}
            return products;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
     public Vector<Product> getProductsByPage(int pageNumber, int pageSize) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM [product] ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, (pageNumber - 1) * pageSize);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                int brand_id = rs.getInt("brand_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image_url = rs.getString("image_url");
                double price = rs.getDouble("price");
                Date release_date = rs.getDate("release_date");
                double discount = rs.getDouble("discount");
    
                products.add(new Product(id, quantity, brand_id, name, description, image_url, price, release_date, discount));
}
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }

    public int getTotalProducts() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total = 0;
        String sql = "SELECT COUNT(*) FROM [product]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }


    
    
    
    
    public int insertProduct(Product p) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int generatedId = -1;

String sql = "INSERT INTO [dbo].[product]\n"
             + "           ([name]\n"
             + "           ,[price]\n"
             + "           ,[quantity]\n"
             + "           ,[description]\n"
             + "           ,[image_url]\n"
             + "           ,[brand_id]\n"
             + "           ,[release_date]\n"
             + "           ,[discount])\n" 
             + "     VALUES\n"
             + "           (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, p.getName());
            stm.setDouble(2, p.getPrice());
            stm.setInt(3, p.getQuantity());
            stm.setString(4, p.getDescription());
            stm.setString(5, p.getImage_url());
            stm.setInt(6, p.getBrand_id());
            stm.setDate(7, p.getRelease_date());
            stm.setDouble(8, p.getDiscount()); 
            stm.executeUpdate();

            //get generatedId
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return generatedId;
    }
    
    
 
    
    
    
    
    
    public void updateProduct(Product p, int pid) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[product]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[release_date] = ?\n"
                + "      ,[discount] = ?\n"
                + " WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setDouble(2, p.getPrice());
            stm.setInt(3, p.getQuantity());
            stm.setDate(4, p.getRelease_date());
            stm.setDouble(5, p.getDiscount());
            stm.setInt(6, pid);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int deletetProduct(int id) {
        int n = 0;
        PreparedStatement stm = null;
        ResultSet rs = getData("select * from [dbo].[order_detail] where product_id = " + id);

        String sql = "DELETE FROM [dbo].[product]\n"
                + "      WHERE id = ?";
        try {
            if (!rs.next()) {
                stm = connection.prepareStatement(sql);
                stm.setInt(1, id);
                
                n = stm.executeUpdate();
            }
            
        } catch (SQLException ex) {
            n = -1;
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;
    }

public Product getProductsById(int productId) {
    PreparedStatement stm = null;
    ResultSet rs = null;
    Product product = null;
    String sql = "SELECT * FROM [product] WHERE id = ?";
    try {
        stm = connection.prepareStatement(sql);
        stm.setInt(1, productId);
        rs = stm.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            int quantity = rs.getInt("quantity");
            int brand_id = rs.getInt("brand_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String image_url = rs.getString("image_url");
            double price = rs.getDouble("price");
            Date release_date = rs.getDate("release_date");
            double discount = rs.getDouble("discount");

            product = new Product(id, quantity, brand_id, name, description, image_url, price, release_date, discount);
        }
        return product;

    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        // Handle exception or log error
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            // Do not close connection here; handle it appropriately elsewhere
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return null;
}


    //search by name
    public Vector<Product> getProductsByKeywords(String s) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<Product> products = new Vector<>();
        String sql = "select * from [product] where name like ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + s + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id"), quantity = rs.getInt("quantity"), brand_id = rs.getInt("brand_id");
                String name = rs.getString("name"), description = rs.getString("description"), image_url = rs.getString("image_url");
                double price = rs.getDouble("price");
                Date release_date = rs.getDate("release_date");
                double discount = rs.getDouble("discount");
                
                products.add(new Product(id, quantity, brand_id, name, description, image_url, price, release_date, discount));
            }
            return products;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                rs.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
      public int getTotalProductsByKeywords(String keywords, String filterByPrice, String filterByBrand) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total = 0;
        String sql = "SELECT COUNT(*) FROM [product] WHERE [name] LIKE ?";
        
        if (!filterByPrice.equals("price-all")) {
            sql += " AND [price] ";
            if (filterByPrice.equals("lower50")) {
                sql += "< 50";
            } else if (filterByPrice.equals("50to100")) {
                sql += "BETWEEN 50 AND 100";
            } else if (filterByPrice.equals("100to200")) {
                sql += "BETWEEN 100 AND 200";
            } else if (filterByPrice.equals("over200")) {
                sql += "> 200";
            }
        }

        if (!filterByBrand.equals("brand-all")) {
            sql += " AND [brand_id] = ?";
        }

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keywords + "%");

            if (!filterByBrand.equals("brand-all")) {
                stm.setInt(2, Integer.parseInt(filterByBrand));
            }

            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }

    public Vector<Product> getProductsByKeywords(String keywords, String sortBy, String filterByPrice, String filterByBrand, int pageNumber, int pageSize) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM [product] WHERE [name] LIKE ?";

        if (!filterByPrice.equals("price-all")) {
            sql += " AND [price] ";
            if (filterByPrice.equals("lower50")) {
                sql += "< 50";
            } else if (filterByPrice.equals("50to100")) {
                sql += "BETWEEN 50 AND 100";
            } else if (filterByPrice.equals("100to200")) {
                sql += "BETWEEN 100 AND 200";
            } else if (filterByPrice.equals("over200")) {
                sql += "> 200";
            }
        }

        if (!filterByBrand.equals("brand-all")) {
            sql += " AND [brand_id] = ?";
        }

        if (sortBy != null) {
            if (sortBy.equals("priceLowHigh")) {
                sql += " ORDER BY [price] ASC";
            } else if (sortBy.equals("priceHighLow")) {
                sql += " ORDER BY [price] DESC";
            } else if (sortBy.equals("latest")) {
                sql += " ORDER BY [release_date] DESC";
            }
        } else {
            sql += " ORDER BY [id]";
        }

        sql += " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keywords + "%");

            int index = 2;
            if (!filterByBrand.equals("brand-all")) {
                stm.setInt(index++, Integer.parseInt(filterByBrand));
            }
            stm.setInt(index++, (pageNumber - 1) * pageSize);
            stm.setInt(index, pageSize);

            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                int brand_id = rs.getInt("brand_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image_url = rs.getString("image_url");
                double price = rs.getDouble("price");
                Date release_date = rs.getDate("release_date");
                double discount = rs.getDouble("discount");
                
                products.add(new Product(id, quantity, brand_id, name, description, image_url, price, release_date, discount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }


    public Vector<Product> sortProducts(Vector<Product> products, String sortBy) {
        if (sortBy.equals("priceLowHigh")) {
            products.sort(Comparator.comparing(Product::getPrice));
        }

        if (sortBy.equals("priceHighLow")) {
            products.sort(Comparator.comparing(Product::getPrice, Comparator.reverseOrder()));
        }

        if (sortBy.equals("latest")) {
            products.sort(Comparator.comparing(Product::getRelease_date, Comparator.reverseOrder()));
        }
        return products;
    }

    public Vector<Product> filterByPrice(String filter, Vector<Product> products) {
        Vector<Product> productsAfterFilter = new Vector<>();

        switch (filter) {
            case "price-500-750":
                productsAfterFilter = filterPrice(500, 750, products);
                break;
            case "price-750-1000":
                productsAfterFilter = filterPrice(750, 1000, products);
                break;
            case "price-1000-1500":
                productsAfterFilter = filterPrice(1000, 1500, products);
                break;
            case "price-1500up":
                productsAfterFilter = filterPrice(1500, Double.MAX_VALUE, products);
                break;
            default:
                return products;
        }

        return productsAfterFilter;
    }

    public Vector<Product> filterByBrand(String filter, Vector<Product> products) {
        Vector<Product> productsAfterFilter = new Vector<>();

        if (filter.equals("brand-all")) {
            return products;
        }
        String[] filterSplits = filter.split("[-]");
        int brandId = Integer.parseInt(filterSplits[1]);
        productsAfterFilter = filterBrand(brandId, products);

        return productsAfterFilter;
    }

    private Vector<Product> filterPrice(double min, double max, Vector<Product> products) {
        Vector<Product> productsAfterFilter = new Vector<>();

        for (Product product : products) {
            if (product.getPrice() < max && product.getPrice() > min) {
                productsAfterFilter.add(product);
            }
        }
        return productsAfterFilter;
    }

    private Vector<Product> filterBrand(int id, Vector<Product> products) {

        Vector<Product> productsAfterFilter = new Vector<>();
        for (Product product : products) {
            if (product.getBrand_id() == id) {
                productsAfterFilter.add(product);
            }
        }
        return productsAfterFilter;
    }
    
    
    public int getProductQuantityById(int productId) {
    PreparedStatement stm = null;
    ResultSet rs = null;
    int quantity = 0;
    String sql = "SELECT quantity FROM [product] WHERE id = ?";
    try {
        stm = connection.prepareStatement(sql);
        stm.setInt(1, productId);
        rs = stm.executeQuery();
        if (rs.next()) {
            quantity = rs.getInt("quantity");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return quantity;
}


}
