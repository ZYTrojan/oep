package com.zr.dao;


/**
*@author VerSion
*@time 2017年9月22日上午9:51:52
*/
public interface UserDao_hwx {
	/**
	 * 注册到数据库
	 * @param u_name 用户名
	 * @param u_password 用户密码
	 * @param u_email 用户邮箱
	 * @param u_telephone 用户电话
	 * @return 是否注册成功
	 */
	public boolean UserRegister(String u_name,String u_password,String u_email,String u_telephone);
	
	/**
	 * 查看库里是否存在该用户
	 * @param u_uname
	 * @return
	 */
	public boolean UserExist(String u_uname);
	
	/**
	 * 根据用户名获取密码
	 * @param u_uname 用户名
	 * @return 密码
	 */
	public String Getpswbyuname(String u_uname);
	
	
}
