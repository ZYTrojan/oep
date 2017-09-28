package com.zr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	 //1.数据库地址  (根据不同的数据标准是不一样)
	  private  final  static String DBURL = "jdbc:mysql://localhost:3306/oep?useUnicode=true&characterEncoding=UTF8";
	  //2.设置用户和密码
	  private  final  static String  USERNAME = "root";
	  private  final  static String  PASSWORD = "123";
			
	   /**
	    * 获取数据库连接
	    * @return   链接
	    */
	  public  static  Connection    getConnection(){
		    Connection  con  = null;
		    try {
				Class.forName("com.mysql.jdbc.Driver");
				con  =  DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return  con;
	  }
	  
	  /**
	   * 关闭数据库连接
	   * @param pst  需要关闭预编译对象
	   * @param con   需要关闭的连接
	   */
	  public   static  void   closeJDBC(PreparedStatement  pst,Connection  con){
		    if(pst!=null){
		    	try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    if(con!=null){
		    	try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	  }
}
