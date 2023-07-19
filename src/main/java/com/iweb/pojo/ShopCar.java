package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @date
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopCar {
    private int ShopCarID;
    private int UserID;
    private int ProductID;
    private int ProductCount;
}
