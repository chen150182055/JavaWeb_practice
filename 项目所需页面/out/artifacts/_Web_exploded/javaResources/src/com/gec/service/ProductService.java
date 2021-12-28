package com.gec.service;

import com.gec.dao.ProductDao;
import com.gec.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {


    ProductDao productDao  =new ProductDao();
    //查询最新商品和最热商品的方法

    /**
     *
     * @return
     */
    public Map<String,List<Product>> findProduct(){
        try {
            //先查询热门商品和最新商品 然后将他们存放到Map容器
            List<Product> findHotProduct = productDao.getHotProductList();
            List<Product> findNewProduct = productDao.getNewProductList();
            Map<String,List<Product>> map = new HashMap<String, List<Product>>();
            map.put("hotProducts", findHotProduct);
            map.put("newProducts", findNewProduct);
            return map;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List<Product> getProductList() {
        // TODO Auto-generated method stub
        return productDao.getProductList();
    }

    /**
     *
     * @param cid
     * @return
     */
    public List<Product> getProductListByCid(String cid){
        return productDao.getProductListByCid(cid);

    }

    /**
     *
     * @param pid
     * @return
     */
    //根据id查询商品
    public Product getProductById(String pid) {
        try {
            Product findProductById =productDao.getProductByPid(pid);
            return findProductById;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param pid
     * @return
     */
    public int deleteProductByPid(String pid) {
        return productDao.deleteProductByPid(pid);
    }

    /**
     *
     * @param product
     * @return
     */
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    /**
     *
     * @param product
     * @return
     */
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }


}