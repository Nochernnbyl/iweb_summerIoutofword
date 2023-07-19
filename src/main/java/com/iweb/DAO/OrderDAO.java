package com.iweb.DAO;

import com.iweb.pojo.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zhao
 * @date
 */
public interface OrderDAO {
    List<Orders> getAllOrders() throws SQLException;

    Orders getOrderById(int orderId) throws Exception;

    void addOrder(Orders order) throws SQLException;

    void updateOrder(Orders order) throws SQLException;

    void deleteOrder(int orderId) throws Exception;
}
