package com.gec.entity;

public class User {
	//用户id
	private String uid;
	//用户登录名
	private String username;
	//用户登录密码
	private String password;
	//用户姓名
	private String name;
	//用户邮箱
	private String email;
	//用户手机号码
	private String telephone;
	//用户出生日期
	private String birthday;
	//用户性别
	private String sex;
	//用户状态
	private int state;
	//激活码
	private String code;
	//用户常用收货地址或者住址
	private String address;

	public User(){}

	/**
	 * 获取用户id
	 * @return
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * 设置用户id
	 * @param uid
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 设置用户登录名
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户登录名
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取用户登录密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置用户登录密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	//获取用户姓名
	public String getName() {
		return name;
	}

	/**
	 * 设置用户姓名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取用户邮箱
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置用户邮箱
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取用户手机号
	 * @return
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置用户手机号
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取用户生日
	 * @return
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 设置用户生日
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取用户性别
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置用户性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取用户状态
	 * @return
	 */
	public int getState() {
		return state;
	}

	/**
	 * 设置用户状态
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * 获取激活码
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置激活码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取用户收货地址
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置用户收货地址
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex + ", state=" + state
				+ ", code=" + code + ", address=" + address + "]";
	}
	
	

}
