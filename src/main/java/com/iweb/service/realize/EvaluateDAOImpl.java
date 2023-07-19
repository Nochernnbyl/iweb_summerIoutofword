package com.iweb.service.realize;

import com.iweb.pojo.Evaluate;
import com.iweb.service.EvaluateDAO;
import com.iweb.util.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author zhao
 * @date
 */
//评价中评价
public class EvaluateDAOImpl implements EvaluateDAO {
    @Override
    public void addProductEvaluate(Evaluate productEvaluate) {
        String sql = "INSERT INTO productevaluate (userID, productID, evaluate) VALUES (?, ?, ?)";

        try (Connection c = DBPool.getConnection();
             PreparedStatement statement = c.prepareStatement(sql)) {
            statement.setInt(1, productEvaluate.getUserId());
            statement.setInt(2, productEvaluate.getProductId());
            statement.setString(3, productEvaluate.getEvaluate());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
