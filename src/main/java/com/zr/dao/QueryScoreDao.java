package com.zr.dao;

import java.util.List;

import com.zr.model.Scoreofuser;

public interface QueryScoreDao {
	/**
	 * 通过用户名称查询考试信息
	 * @param uname
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Scoreofuser> getmsg1(String uname, int page, int pageSize);
	
	/**
	 * 通过考试名称查询考试信息
	 * @param ename
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Scoreofuser> getmsg2(String ename, int page, int pageSize);
	
	/**
	 * 查询用户的所有成绩
	 * @param uname
	 * @return
	 */
	public List<Scoreofuser> getScoreByU(String uname);
	
	/**
	 * 查询某考试的所有成绩
	 * @param ename
	 * @return
	 */
	public List<Scoreofuser> getScoreByE(String ename);
	
	/**
	 * 通过用户姓名获取用户ID
	 * @param uname
	 * @return
	 */
	public int getUidByUname(String uname);
	
	/**
	 * 通过考试名称获取考试ID
	 * @param ename
	 * @return
	 */
	public int getEidByEname(String ename);
	
	/**
	 * 通过用户ID获取用户名
	 * @param uid
	 * @return
	 */
	public String getUnameByUid(int uid);
	
	/**
	 * 通过考试ID获取考试名称
	 * @param eid
	 * @return
	 */
	public String getEnameByEid(int eid);
	
	/**
	 * 获取考生参与的考试数
	 * @param uname
	 * @return
	 */
	public int getUserExam(String uname);
	
	/**
	 * 获取参与某考试的学生数
	 * @param ename
	 * @return
	 */
	public int getExamUser(String ename);
}
