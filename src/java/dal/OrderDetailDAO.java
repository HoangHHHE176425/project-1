package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Order;

public class OrderDetailDAO extends DBContext {

public void insert(Order order, CartItem cartItem) {
    PreparedStatement stm = null;
    PreparedStatement updateStm = null;

    String insertSql = "INSERT INTO [dbo].[order_detail] "
            + "([product_quantity], [product_id], [order_id], [price]) "
            + "VALUES (?, ?, ?, ?)";
    
    String updateSql = "UPDATE [dbo].[product] "
            + "SET quantity = quantity - ? "
            + "WHERE id = ?";

    try {
        // Chèn vào order_detail
        stm = connection.prepareStatement(insertSql);
        stm.setInt(1, cartItem.getQuantity());
        stm.setInt(2, cartItem.getProduct().getId());
        stm.setInt(3, order.getId());
        stm.setDouble(4, cartItem.getProduct().getPrice());
        stm.executeUpdate();

        // Cập nhật số lượng trong bảng product
        updateStm = connection.prepareStatement(updateSql);
        updateStm.setInt(1, cartItem.getQuantity());
        updateStm.setInt(2, cartItem.getProduct().getId());
        updateStm.executeUpdate();

        System.out.println("Insert and update OK");
    } catch (SQLException ex) {
        Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (updateStm != null) {
                updateStm.close();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

}
