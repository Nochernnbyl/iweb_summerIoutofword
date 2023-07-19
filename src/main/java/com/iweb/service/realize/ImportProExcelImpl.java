package com.iweb.service.realize;

import com.iweb.DAO.Impl.ProductDAOImpl;
import com.iweb.pojo.Product;
import com.iweb.service.ImportProExcel;

import java.io.File;
import java.util.List;

/**
 * @author ASUS
 * @Date 2023/7/19 18:56
 * @Version 1.8
 */
public class ImportProExcelImpl implements ImportProExcel {


    @Override
    public boolean productImport(File excel, Class<?> type) {
        List<Product> list= new GetExcel().getExcel(excel,type);
        return importProductDB(list);
    }

    private boolean importProductDB(List<Product> list){
        try{
            new ProductDAOImpl().insertAll(list);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
