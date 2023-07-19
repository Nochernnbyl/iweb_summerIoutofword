package com.iweb.DAO.Impl;

import com.iweb.DAO.ProductDAO;
import com.iweb.pojo.Product;
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
public class ProductDAOImpl implements ProductDAO {


    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                int reviews = resultSet.getInt("Reviews");
                int statsId = resultSet.getInt("statsID");
                double productPrice = resultSet.getDouble("productPrice");
                Product product = new Product(productId, productName, reviews, statsId, productPrice);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String productName = resultSet.getString("ProductName");
                    int reviews = resultSet.getInt("Reviews");
                    int statsId = resultSet.getInt("statsID");
                    double productPrice = resultSet.getDouble("productPrice");
                    return new Product(productId, productName, reviews, statsId, productPrice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (ProductName, Reviews, statsID, productPrice) VALUES (?, ?, ?, ?)";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getReviews());
            statement.setInt(3, product.getStatsId());
            statement.setDouble(4, product.getProductPrice());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET ProductName = ?, Reviews = ?, statsID = ?, productPrice = ? WHERE ProductID = ?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getReviews());
            statement.setInt(3, product.getStatsId());
            statement.setDouble(4, product.getProductPrice());
            statement.setInt(5, product.getProductId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入商品
     *
     * @param list 从excel中获得的Product集合
     */
    @Override
    public void insertAll(List<Product> list) throws Exception {
        String sql = "INSERT INTO product (productName,reviews,statsID,productPrice) VALUES (?,?,?,?)";
        for (Product p : list) {
            try (
                    Connection c = DBPool.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql);
            ) {
                ps.setString(1,p.getProductName() );
                ps.setInt(2,p.getReviews());
                ps.setInt(3,p.getStatsId());
                ps.setDouble(4,p.getProductPrice());
                ps.execute();
            } catch (Exception e) {
                throw new Exception();
            }

        }
    }
}
