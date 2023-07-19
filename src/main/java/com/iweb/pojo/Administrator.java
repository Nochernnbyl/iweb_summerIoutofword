package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @author ASUS
 * @Date 2023/7/18 9:58
 * @Version 1.8
 */

public class Administrator {
    private int  id;
    private String name;
    private String password;
}
