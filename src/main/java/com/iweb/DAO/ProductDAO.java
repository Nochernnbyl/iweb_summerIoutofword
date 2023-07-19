package com.iweb.DAO;

import com.iweb.pojo.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zhao
 * @date
 */
public interface ProductDAO {
    List<Product> getAllProducts() throws SQLException;

    Product getProductById(int productId) throws SQLException;

    void addProduct(Product product) throws SQLException;

    void updateProduct(Product product) throws SQLException;

    void deleteProduct(int productId) throws SQLException;


    /**  导入商品
     * @param list 从excel中获得的Product集合
     */
    void insertAll(List<Product> list) throws Exception;
}
