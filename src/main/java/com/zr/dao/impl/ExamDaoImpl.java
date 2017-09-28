package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.zr.dao.ExamDao;
import com.zr.model.Exam;
import com.zr.model.Question;
import com.zr.utils.JDBCUtil;

public class ExamDaoImpl implements ExamDao {
	Connection con;

	public ExamDaoImpl() {
		this.con = JDBCUtil.getConnection();
	}

	@Override
	public int getExamByKey(String key, int start, int pageSize, List<Exam> examList) {
		int count = 0;// 该页考试总数
		StringBuffer sql = new StringBuffer();
		// 信息sql部分
		String selectInfo = "SELECT e_id,e_name,e_starttime,e_endtime,e_state,e_total ";
		// 总数sql部分
		String selectCount = "SELECT COUNT(e_id) AS count ";
		// 条件和分页sql部分
		String whereSql = "FROM exam WHERE e_name LIKE ? OR e_state LIKE ? ";
		String limit = "LIMIT " + start + "," + pageSize;
		// 关键字处理
		key = '%' + key + '%';
		try {
			sql.append(selectCount).append(whereSql);
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 1;
			ps.setString(i++, key);
			ps.setString(i++, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");// 总数
			}
			sql = new StringBuffer();
			sql.append(selectInfo).append(whereSql).append(limit);
			ps = con.prepareStatement(sql.toString());
			i = 1;
			ps.setString(i++, key);
			ps.setString(i++, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				examList.add(row2entity(rs));// exam实体

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 记录转化为实体对象
	 * 
	 * @param rs
	 *            一条记录
	 * @return 实体对象
	 */
	private static Exam row2entity(ResultSet rs) {
		Exam exam = new Exam();
		try {
			exam.setE_id(rs.getInt("e_id"));
			exam.setE_name(rs.getString("e_name"));
			exam.setE_starttime(rs.getString("e_starttime"));
			exam.setE_endtime(rs.getString("e_endtime"));
			exam.setE_state(rs.getString("e_state"));
			exam.setE_total(rs.getInt("e_total"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;
	}

	@Override
	public boolean deleteByIds(int[] examIds) {
		// 记录结果
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM exam WHERE e_id IN (");
		// 组装sql语句
		for (int i = 0; i < examIds.length; i++) {
			sql.append(examIds[i]);
			if (i == examIds.length - 1) {
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		// 信息sql部分
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			if (examIds.length != ps.executeUpdate()) {
				throw new SQLException();// 删除过程异常
			} else {
				result = true;// 删除成功
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(Exam exam) {
		// 记录结果
		int result = -1;
		// 组装sql语句
		StringBuffer sql = new StringBuffer();
		// 插入sql语句
		sql.append("INSERT INTO exam ")
				.append("(exam.e_name,exam.e_starttime,exam.e_endtime,exam.e_state,exam.e_total) ")
				.append("VALUES (?,?,?,?,?)");
		// 获得该考试的记录sql语句
		StringBuffer getCurrendExamIdSql = new StringBuffer();
		getCurrendExamIdSql.append("SELECT MAX(exam.e_id) AS currentExamId FROM exam");
		// 信息sql部分
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 1;
			ps.setString(i++, exam.getE_name());
			ps.setString(i++, exam.getE_starttime());
			ps.setString(i++, exam.getE_endtime());
			ps.setString(i++, exam.getE_state());
			ps.setInt(i++, exam.getE_total());
			// 不可重复读？？
			// 并发操作时防止读取到其他管理员插入的记录，确保获取的是本次插入考试的id
			con.setAutoCommit(false);
			if (ps.executeUpdate() < 0) {
				return result;
			}
			ps = con.prepareStatement(getCurrendExamIdSql.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt("currentExamId");
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Exam getExamById(int id) {
		Exam exam = new Exam();
		// sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM exam WHERE e_id = ?");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				exam = row2entity(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;
	}

	@Override
	public int[] getExistQuestionId(int examId) {
		List<Integer> ids = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT q_id FROM exam_question WHERE e_id = ? ORDER BY q_id");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, examId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt("q_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int[] questionIds = new int[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			questionIds[i] = ids.get(i);
		}
		return questionIds;
	}

	@Override
	public boolean insertExamQuestions(int examId, int[] questionIds, int score) {
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO exam_question (e_id,q_id,q_score) VALUES (?,?,?)");
		try {
			// 批量查询
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < questionIds.length; i++) {
				ps.setInt(1, examId);
				ps.setInt(2, questionIds[i]);
				ps.setInt(3, score);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Question> getQuestionOfExam(int examId) {
		List<Question> questionList = new LinkedList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT question.q_id,t_id,q_content,q_answer ")
			.append(" FROM question ")
			.append(" INNER JOIN exam_question ")
			.append(" ON question.q_id = exam_question.q_id ")
			.append(" WHERE exam_question.e_id = ? ");
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, examId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.setQ_id(rs.getInt("q_id"));
				question.setT_id(rs.getInt("t_id"));
				question.setQ_content(rs.getString("q_content"));
				question.setQ_answer(rs.getString("q_answer"));
				questionList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public boolean removeQuestionOfExam(int examId, int[] ids) {
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM exam_question WHERE e_id = ? AND q_id = ?");
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < ids.length; i++) {
				ps.setInt(1, examId);
				ps.setInt(2, ids[i]);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}