package com.iweb.service.realize;

/**
 * @author zhao
 * @date
 */
import com.iweb.pojo.ShopCar;
import com.iweb.service.ShopCarDAO;
import com.iweb.util.DBPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopcarDAOImpl implements ShopCarDAO {

    @Override
    public void addToCart(int userID, int productID, int productCount) throws Exception {
        String sql = "INSERT INTO shopcar (userID, productID, productCount) VALUES (?, ?, ?)";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setInt(2, productID);
            statement.setInt(3, productCount);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromCart(int userID, int productID) {
        String sql = "DELETE FROM shopcar WHERE userID=? AND productID=?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setInt(2, productID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCart(int userID, int productID, int newCount) {
        String sql = "UPDATE shopcar SET productCount=? WHERE userID=? AND productID=?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, newCount);
            statement.setInt(2, userID);
            statement.setInt(3, productID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ShopCar> getCartID(int userID) {
        List<ShopCar> items = new ArrayList<>();
        String sql = "SELECT * FROM shopcar WHERE userID=?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ShopCar item = new ShopCar();
                item.setUserID(resultSet.getInt("userID"));
                item.setProductID(resultSet.getInt("productID"));
                item.setProductCount(resultSet.getInt("productCount"));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}
