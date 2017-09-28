package com.zr.service;

import net.sf.json.JSONArray;

public interface QueryScoreService {
	/**
	 * 通过学生名字返回考试成绩信息
	 * @param uname
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public JSONArray getExamMsg1(String uname,int page,int pageSize);
	
	/**
	 * 通过考试名称返回考试信息
	 * @param ename
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public JSONArray getExamMsg2(String ename,int page,int pageSize);
	
	/**
	 * 通过用户名查询考生的所有成绩
	 * @param uname
	 * @return
	 */
	public JSONArray getScoreByUname(String uname);
	
	/**
	 * 通过考试名查询参与者的所有成绩
	 * @param ename
	 * @return
	 */
	public JSONArray getScoreByEname(String ename);
	
	/**
	 * 获取用户参与的考试总数
	 * @param uname
	 * @return
	 */
	public int getUserExamNum(String uname);
	
	/**
	 * 获取参与某考试的学生总数
	 * @param ename
	 * @return
	 */
	public int getExamUserNum(String ename);
	
	/**
	 * 通过uid获取uname
	 * @param u_id
	 * @return
	 */
	public String getUserNameByUid(int u_id);
	
	/**
	 * 通过eid获取ename
	 * @param e_id
	 * @return
	 */
	public String getExamNameByEid(int e_id);
	
	/**
	 * 通过uname获取uid
	 * @param uname
	 * @return
	 */
	public int getUidByUname(String uname);
	
	/**
	 * 通过ename获取eid
	 * @param ename
	 * @return
	 */
	public int getEidByEname(String ename);
}
