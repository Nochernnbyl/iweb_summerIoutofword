package com.iweb.service.realize;

import com.iweb.pojo.OrderContent;
import com.iweb.pojo.Orders;
import com.iweb.pojo.Product;
import com.iweb.pojo.ShopCar;
import com.iweb.service.ShopCartOrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhao
 * @date
 */
public class ShopCartOrderDAOImpl implements ShopCartOrderDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/task";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // SQL语句
    private static final String GET_CART_ITEMS = "SELECT * FROM shopcar WHERE userID = ?";
    private static final String ADD_TO_ORDER_CONTENT = "INSERT INTO ordercontent (orderID, productID, productCount, productAllPrice) VALUES (?, ?, ?, ?)";
    private static final String DELETE_CART_ITEM = "DELETE FROM shopcar WHERE userID = ? AND productID = ?";
    private static final String GET_CART_ITEM_BY_PRODUCT = "SELECT * FROM shopcar WHERE userID = ? AND productID = ?";
    private static final String GENERATE_ORDER = "INSERT INTO orders (userID, addressID, orderCreateDate) VALUES (?, ?, ?)";
    private static final String UPDATE_ACCOUNT = "UPDATE user SET account = ? WHERE userID = ?";

    @Override
    public List<ShopCar> getCartItemsByUserID(int userID) {
        List<ShopCar> cartItems = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(GET_CART_ITEMS)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ShopCar cartItem = new ShopCar(
                        rs.getInt("shopcarID"),
                        rs.getInt("userID"),
                        rs.getInt("productID"),
                        rs.getInt("productCount")
                );
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public int addCartItemToOrderContent(ShopCar cartItem) {
        int orderItemID = 0;
        Orders order=new Orders();
        OrderContent oc=new OrderContent();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(ADD_TO_ORDER_CONTENT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1,order.getOrderId());
            stmt.setInt(2, cartItem.getProductID());
            stmt.setInt(3, cartItem.getProductCount());
            stmt.setDouble(4, oc.getProductAllPrice());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                orderItemID = rs.getInt(1); // 获取自动生成的订单项ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItemID;
    }

    @Override
    public void deleteCartItemByProductId(int userID, int productID) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEM)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, productID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShopCar getCartItemByProductId(int userID, int productID) {
        ShopCar cartItem = null;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(GET_CART_ITEM_BY_PRODUCT)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cartItem = new ShopCar(
                        rs.getInt("shopcarID"),
                        rs.getInt("userID"),
                        rs.getInt("productID"),
                        rs.getInt("productCount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItem;
    }


    @Override
    public int generateOrder(int userID, int addressID, double totalPrice) {
        int orderID = 0;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(GENERATE_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, addressID);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                orderID = rs.getInt(1); // 获取自动生成的订单ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateAccount(userID, totalPrice); // 更新账户余额

        return orderID;
    }

    @Override
    public void updateAccount(int userID, double totalPrice) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(UPDATE_ACCOUNT)) {
            stmt.setDouble(1, totalPrice);
            stmt.setInt(2, userID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
