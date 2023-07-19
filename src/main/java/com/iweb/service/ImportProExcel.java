package com.iweb.service;

import java.io.File;

/**
 * @author ASUS
 * @Date 2023/7/19 18:50
 * @Version 1.8
 */
public interface ImportProExcel {


    boolean productImport(File excel, Class<?> type);


}
