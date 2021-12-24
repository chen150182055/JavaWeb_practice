package com.gec.entity;

public class CartItem {
    private Product product;
    private int buyNum;
    private double subTotal;

    /**
     * 获取Product对象（商品）
     * @return
     */
    public Product getProduct() {
        return product;
    }

    /**
     * 设置Product对象（商品）
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 获取购买数量
     * @return
     */
    public int getBuyNum() {
        return buyNum;
    }

    /**
     * 设置购买数量
     * @param buyNum
     */
    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * 获取提交总数
     * @return
     */
    public double getSubTotal() {
        return product.getShop_price() * buyNum;
    }

    @Override
    public String toString() {
        return "CartItem [product=" + product + ", buyNum=" + buyNum + ", subTotal=" + subTotal + "]";
    }


}
