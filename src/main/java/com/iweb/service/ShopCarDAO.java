package com.iweb.service;

import com.iweb.pojo.ShopCar;

import java.util.List;

/**
 * @author zhao
 * @date
 */
//购物车增删改查
public interface ShopCarDAO {
    void addToCart(int userID, int productID, int productCount) throws Exception;

    void removeFromCart(int userID, int productID);

    void updateCart(int userID, int productID, int newCount);

    List<ShopCar> getCartID(int userID);
}
