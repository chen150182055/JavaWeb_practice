package com.gec.entity;

public class Category {
	//分类id
	private String cid;
	//分类名称
	private String cname;

	//构造方法
	public Category(){}

	/**
	 * 获取分类id
	 * @return
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * 设置分类id
	 * @param cid
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * 获取分类名称
	 * @return
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * 设置分类名称
	 * @param cname
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	

}
