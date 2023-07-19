package com.iweb.DAO.Impl;

import com.iweb.DAO.OrderDAO;
import com.iweb.pojo.Orders;
import com.iweb.util.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhao
 * @date
 */
public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Orders> getAllOrders() throws SQLException {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderID");
                int userId = resultSet.getInt("userID");
                int addressId = resultSet.getInt("addressID");
                String orderCreateDate = resultSet.getString("orderCreateDate");
                Orders order = new Orders(orderId, userId, addressId, orderCreateDate);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Orders getOrderById(int orderId) throws Exception {
        String sql = "SELECT * FROM orders WHERE orderID = ?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("userID");
                    int addressId = resultSet.getInt("addressID");
                    String orderCreateDate = resultSet.getString("orderCreateDate");
                    return new Orders(orderId, userId, addressId, orderCreateDate);
                }
            }
        }
        return null;
    }

    @Override
    public void addOrder(Orders order) throws SQLException {
        String sql = "INSERT INTO orders (userID, addressID, orderCreateDate) VALUES (?, ?, ?)";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getAddressId());
            statement.setString(3, order.getOrderCreateDate());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Orders order) throws SQLException {
        String sql = "UPDATE orders SET userID = ?, addressID = ?, orderCreateDate = ? WHERE orderID = ?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getAddressId());
            statement.setString(3, order.getOrderCreateDate());
            statement.setInt(4, order.getOrderId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) throws Exception {
        String sql = "DELETE FROM orders WHERE orderID = ?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }
}
