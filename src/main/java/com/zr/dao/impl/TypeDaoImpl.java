package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zr.dao.TypeDao;
import com.zr.model.Type;
import com.zr.utils.JDBCUtil;

public class TypeDaoImpl implements TypeDao{
	private Connection con;
	
	public TypeDaoImpl() {
		con = JDBCUtil.getConnection();
	}

	@Override
	public Type getTypeById(int typeId) {
		Type type = new Type();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t_id,t_name FROM type WHERE t_id = ?");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, typeId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				type = row2entity(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	/**
	 * 结果集转实体对象
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private static Type row2entity(ResultSet rs) throws SQLException {
		Type type = new Type();
		type.setT_id(rs.getInt("t_id"));
		type.setT_name(rs.getString("t_name"));
		return type;
	}
	

}
