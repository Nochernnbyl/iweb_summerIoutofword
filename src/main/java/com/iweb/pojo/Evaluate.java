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
public class Evaluate {
    private int evaluateId;
    private  int userId;
    private  int productId;
    private String evaluate;
}
