package com.iweb.DAO.Impl;

import com.iweb.DAO.ProductDAO;
import com.iweb.pojo.Product;
import com.iweb.util.DBPool.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author ASUS
 * @Date 2023/7/18 10:43
 * @Version 1.8
 */
public class ProductDAOImpl implements ProductDAO {
    /**
     * 导入商品
     *
     * @param list 从excel中获得的Product集合
     */
    @Override
    public void insertAll(List<Product> list) throws Exception {
        String sql = "INSERT INTO product (productName,salesVolume,evaluate,statsID,productPrice) VALUES (?,?,?,?,?)";
        for (Product p : list) {
            try (
                    Connection c = DBPool.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql);
            ) {
                ps.setString(1,p.getName() );
                ps.setInt(2,p.getSalesVolume());
                ps.setInt(3,p.getEvaluate());
                ps.setInt(4,p.getStatsId());
                ps.setInt(5,p.getProductPrice());
                ps.execute();
            } catch (Exception e) {
                throw new Exception();
            }

        }
    }
}
