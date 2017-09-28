package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.QueryScoreDao;
import com.zr.model.Scoreofuser;
import com.zr.utils.JDBCUtil;

public class QueryScoreDaoImpl implements QueryScoreDao {

	@Override
	public List<Scoreofuser> getmsg1(String uname, int page, int pageSize) {
		List<Scoreofuser> stus = new ArrayList<Scoreofuser>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e_id,u_id,score ");
		sql.append("FROM scoreofuser ");
		sql.append("WHERE u_id=? limit ?,?");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			int uid = this.getUidByUname(uname);
			pst.setInt(1, uid);
			pst.setInt(2, page);
			pst.setInt(3, pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Scoreofuser student = new Scoreofuser();
				student.setU_id(rs.getInt("u_id"));
				student.setE_id(rs.getInt("e_id"));
				student.setScore(rs.getInt("score"));
				stus.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}

		return stus;
	}

	@Override
	public List<Scoreofuser> getmsg2(String ename, int page, int pageSize) {
		List<Scoreofuser> stus = new ArrayList<Scoreofuser>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e_id,u_id,score ");
		sql.append("FROM scoreofuser ");
		sql.append("WHERE e_id=? limit ?,?");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			int eid = this.getEidByEname(ename);
			pst.setInt(1, eid);
			pst.setInt(2, page);
			pst.setInt(3, pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Scoreofuser student = new Scoreofuser();
				student.setU_id(rs.getInt("u_id"));
				student.setE_id(rs.getInt("e_id"));
				student.setScore(rs.getInt("score"));
				stus.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}

		return stus;
	}

	@Override
	public int getUidByUname(String uname) {
		int a = 0;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT u_id ");
		sql.append("FROM user ");
		sql.append("WHERE u_name = ? ");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, uname);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a = rs.getInt("u_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}
		return a;
	}

	@Override
	public int getEidByEname(String ename) {
		int a = 0;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e_id ");
		sql.append("FROM exam ");
		sql.append("WHERE e_name = ? ");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, ename);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a = rs.getInt("e_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}
		return a;
	}

	@Override
	public String getUnameByUid(int uid) {
		String a = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT u_name ");
		sql.append("FROM user ");
		sql.append("WHERE u_id = ? ");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a = rs.getString("u_name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}
		return a;
	}

	@Override
	public String getEnameByEid(int eid) {
		String a = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e_name ");
		sql.append("FROM exam ");
		sql.append("WHERE e_id = ? ");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, eid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a = rs.getString("e_name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}
		return a;
	}

	@Override
	public int getUserExam(String uname) {
		int a = 0;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(e_id) a ");
		sql.append("FROM scoreofuser ");
		sql.append("WHERE u_id= ? ");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			int ui = this.getUidByUname(uname);
			pst.setInt(1, ui);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a = rs.getInt("a");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}
		return a;
	}

	@Override
	public int getExamUser(String ename) {
		int a = 0;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(u_id) a ");
		sql.append("FROM scoreofuser ");
		sql.append("WHERE e_id= ? ");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			int ei = this.getEidByEname(ename);
			pst.setInt(1, ei);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				a = rs.getInt("a");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}
		return a;
	}

	@Override
	public List<Scoreofuser> getScoreByU(String uname) {
		List<Scoreofuser> stus = new ArrayList<Scoreofuser>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e_id,u_id,score ");
		sql.append("FROM scoreofuser ");
		sql.append("WHERE u_id=?");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			int uid = this.getUidByUname(uname);
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Scoreofuser student = new Scoreofuser();
				student.setU_id(rs.getInt("u_id"));
				student.setE_id(rs.getInt("e_id"));
				student.setScore(rs.getInt("score"));
				stus.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}

		return stus;
	}

	@Override
	public List<Scoreofuser> getScoreByE(String ename) {
		List<Scoreofuser> stus = new ArrayList<Scoreofuser>();
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT e_id,u_id,score ");
		sql.append("FROM scoreofuser ");
		sql.append("WHERE e_id=?");
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql.toString());
			int eid = this.getEidByEname(ename);
			pst.setInt(1, eid);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Scoreofuser student = new Scoreofuser();
				student.setU_id(rs.getInt("u_id"));
				student.setE_id(rs.getInt("e_id"));
				student.setScore(rs.getInt("score"));
				stus.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeJDBC(pst, con);
		}

		return stus;
	}

}
