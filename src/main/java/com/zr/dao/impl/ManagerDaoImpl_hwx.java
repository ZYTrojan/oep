package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zr.dao.ManagerDao_hwx;
import com.zr.utils.JDBCUtil;

/**
*@author VerSion
*@time 2017年9月25日上午11:04:43
*/
public class ManagerDaoImpl_hwx implements ManagerDao_hwx{

	@Override
	public boolean ManagerExist(String m_name) {
		boolean exist = false;
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("SELECT m_id FROM manager WHERE m_name=? ");
		PreparedStatement pst;

		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, m_name);
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
	public String Getpswbymname(String mname) {
		String psw = new String();
		StringBuffer sql = new StringBuffer();
		Connection con = JDBCUtil.getConnection();
		sql.append("SELECT m_password FROM manager WHERE m_name=? ");
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, mname);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				psw = rs.getString("m_password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return psw;
	}

	
}
