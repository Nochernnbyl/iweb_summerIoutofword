package com.iweb.service;

import java.io.File;

/**
 * @author ASUS
 * @Date 2023/7/18 10:56
 * @Version 1.8
 */
public interface ProductImport {


    boolean productImport(File excel,Class<?> type);

}
