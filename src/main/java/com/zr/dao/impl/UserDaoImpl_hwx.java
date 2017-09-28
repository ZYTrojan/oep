package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zr.dao.UserDao_hwx;
import com.zr.utils.JDBCUtil;

/**
 * @author VerSion
 * @time 2017年9月22日上午9:53:22
 */
public class UserDaoImpl_hwx implements UserDao_hwx {

	@Override
	public boolean UserRegister(String u_name, String u_password, String u_email, String u_telephone) {

		boolean result = false;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO user (user.u_name,user.u_password,user.u_email,user.u_telephone) ")
				.append(" VALUES (?,?,?,?)");

		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst;

		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, u_name);
			pst.setString(2, u_password);
			pst.setString(3, u_email);
			pst.setString(4, u_telephone);
			pst.executeUpdate();
			result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean UserExist(String u_uname) {

		boolean exist = false;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("SELECT u_id FROM user WHERE u_name=? ");
		PreparedStatement pst;

		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, u_uname);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				exist = true;
			} else {
				exist = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;

	}
	
	

	@Override
	public String Getpswbyuname(String u_uname) {
		String psw = new String();
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("SELECT u_password FROM user WHERE u_name=? ");
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, u_uname);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				psw = rs.getString("u_password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return psw;
	}

}
