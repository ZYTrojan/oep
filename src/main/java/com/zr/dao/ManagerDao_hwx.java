package com.zr.dao;
/**
*@author VerSion
*@time 2017年9月25日上午11:02:29
*/
public interface ManagerDao_hwx {
	/**
	 * 判断管理员是否存在
	 * @param mname	管理员名
	 * @return 是否存在
	 */
	public boolean ManagerExist(String m_name);
	
	/**
	 * 根据管理员名获取管理员密码
	 * @param mname	管理员名
	 * @return 管理员密码
	 */
	public String Getpswbymname(String mname);
}
