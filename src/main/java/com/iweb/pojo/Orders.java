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
public class Orders {
    private int orderId;
    private int userId;
    private int addressId;
    private String orderCreateDate;
}
