package com.zr.dao.qktemp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.zr.model.Question;
import com.zr.model.Type;
import com.zr.utils.JDBCUtil;

public class QuestionDaoImpl implements QuestionDao {
	private Connection con;

	public QuestionDaoImpl() {
		con = JDBCUtil.getConnection();
	}

	@Override
	public List<Type> getAllQuestionType() {
		List<Type> typeList = new LinkedList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM type");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Type type = new Type();
				type.setT_id(rs.getInt("t_id"));
				type.setT_name(rs.getString("t_name"));
				typeList.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return typeList;
	}

	@Override
	public int getQustionByTypeIdAndKey(int start, int size, int typeId, String key, List<Question> questionList) {
		int count = 0;
		String selectSql = new String(" SELECT * ");
		String countSql = new String(" SELECT COUNT(q_id) AS count");
		// String whereSql = new String(" FROM question WHERE t_id = " + typeId
		// + " AND q_content LIKE '%"+ key +"%'");
		String whereSql = new String();
		if (typeId == -1)
			whereSql = " FROM question WHERE q_content LIKE '%" + key + "%'";
		else
			whereSql = " FROM question WHERE t_id = " + typeId + " AND q_content LIKE '%" + key + "%'";
		String limitSql = new String(" LIMIT " + start + "," + size);
		try {
			PreparedStatement ps = con.prepareStatement(countSql + whereSql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
			ps = con.prepareStatement(selectSql + whereSql + limitSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				questionList.add(row2entity(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	private static Question row2entity(ResultSet rs) throws SQLException {
		Question question = new Question();
		question.setQ_id(rs.getInt("q_id"));
		question.setT_id(rs.getInt("t_id"));
		question.setQ_content(rs.getString("q_content"));
		question.setT_id(rs.getInt("t_id"));
		question.setQ_answer(rs.getString("q_answer"));
		return question;
	}
}
