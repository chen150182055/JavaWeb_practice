package com.gec.entity;

public class CartItem {
	private Product product;
	private int buyNum;
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubTotal() {
		return product.getShop_price()*buyNum;
	}
	
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", buyNum=" + buyNum + ", subTotal=" + subTotal + "]";
	}
	
	

}
