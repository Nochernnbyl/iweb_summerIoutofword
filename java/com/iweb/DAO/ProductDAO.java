package com.iweb.DAO;

import com.iweb.pojo.Product;

import java.util.List;

/**
 * @author ASUS
 * @Date 2023/7/18 10:41
 * @Version 1.8
 */
public interface ProductDAO {

    /**  导入商品
     * @param list 从excel中获得的Product集合
     */
    void insertAll(List<Product> list) throws Exception;


}
